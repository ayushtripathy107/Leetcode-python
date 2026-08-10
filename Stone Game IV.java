class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        // Iterate through all stone states from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try all possible perfect square moves
            for (int k = 1; k * k <= i; k++) {
                // If the opponent loses from the next state, the current player wins
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // Move found, no need to check other squares for this state
                }
            }
        }
        
        return dp[n];
    }
}
