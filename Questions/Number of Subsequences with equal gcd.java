import java.util.*;

class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1_000_000_007;
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        // dp[g1][g2] stores the number of pairs of disjoint subsequences
        // with GCDs g1 and g2. 0 represents an empty subsequence.
        long[][] dp = new long[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; 

        for (int x : nums) {
            long[][] nextDp = new long[maxVal + 1][maxVal + 1];
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    // Option 1: Don't include x in either subsequence
                    nextDp[g1][g2] = (nextDp[g1][g2] + dp[g1][g2]) % MOD;

                    // Option 2: Include x in seq1
                    int nextG1 = (g1 == 0) ? x : gcd(g1, x);
                    nextDp[nextG1][g2] = (nextDp[nextG1][g2] + dp[g1][g2]) % MOD;

                    // Option 3: Include x in seq2
                    int nextG2 = (g2 == 0) ? x : gcd(g2, x);
                    nextDp[g1][nextG2] = (nextDp[g1][nextG2] + dp[g1][g2]) % MOD;
                }
            }
            dp = nextDp;
        }

        long count = 0;
        for (int g = 1; g <= maxVal; g++) {
            count = (count + dp[g][g]) % MOD;
        }

        return (int) count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            int temp = a;
            a = b;
            b = temp;
        }
        return a;
    }
}
