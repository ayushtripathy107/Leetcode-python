import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // memo[i][M] stores the max stones Alice can get starting at index i with current M
        memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Precompute suffix sums to get total stones remaining from index i to n-1 in O(1)
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return helper(0, 1, n);
    }

    private int helper(int i, int M, int n) {
        // Base Case: If we processed all piles
        if (i >= n) return 0;
        
        // Shortcut: If we can take all remaining piles, take them all
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return cached result if already calculated
        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        int maxStones = 0;
        
        // Explore all choices of X where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M; X++) {
            // Next player's turn starts at index i + X with updated parameter max(M, X)
            int nextPlayerStones = helper(i + X, Math.max(M, X), n);
            
            // Current player gets total remaining stones minus whatever the next opponent optimally gets
            int currentPlayerStones = suffixSum[i] - nextPlayerStones;
            
            maxStones = Math.max(maxStones, currentPlayerStones);
        }

        return memo[i][M] = maxStones;
    }
}
