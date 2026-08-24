class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Compute prefix sums inline or in an array
        int[] pref = new int[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }
        
        // Base case: if forced to take all stones up to the last index
        int[] dp = new int[n];
        dp[n - 1] = pref[n - 1];
        
        // Fill DP array from right to left
        for (int i = n - 2; i >= 1; i--) {
            dp[i] = Math.max(dp[i + 1], pref[i] - dp[i + 1]);
        }
        
        // Alice must pick at least 2 stones, which corresponds to index >= 1
        return dp[1];
    }
}
