class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1_000_000_007L;
        
        long[] prefixSum = new long[n + 1];
        long[] prefixX = new long[n + 1];
        int[] nonZeroCount = new int[n + 1];
        long[] pow10 = new long[n + 1];
        
        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            prefixSum[i + 1] = prefixSum[i] + digit;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
            
            if (digit != 0) {
                prefixX[i + 1] = (prefixX[i] * 10 + digit) % MOD;
                nonZeroCount[i + 1] = nonZeroCount[i] + 1;
            } else {
                prefixX[i + 1] = prefixX[i];
                nonZeroCount[i + 1] = nonZeroCount[i];
            }
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            
            // 1. Calculate sum of digits
            long sumD = prefixSum[r + 1] - prefixSum[l];
            
            // 2. Calculate x (concatenated non-zero digits)
            int countBefore = nonZeroCount[l];
            int countTotal = nonZeroCount[r + 1];
            int numDigitsInRange = countTotal - countBefore;
            
            long x;
            if (numDigitsInRange == 0) {
                x = 0;
            } else {
                x = (prefixX[r + 1] - (prefixX[l] * pow10[numDigitsInRange] % MOD) + MOD) % MOD;
            }
            
            ans[i] = (int) ((x * (sumD % MOD)) % MOD);
        }
        
        return ans;
    }
}
