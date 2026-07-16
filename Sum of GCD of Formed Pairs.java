import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        
        // Step 1 & 2: Build the prefixGcd array
        int maxSoFar = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxSoFar) {
                maxSoFar = nums[i];
            }
            prefixGcd[i] = gcd(nums[i], maxSoFar);
        }
        
        // Step 3: Sort the resulting array
        Arrays.sort(prefixGcd);
        
        // Step 4: Two-pointer approach to pair smallest and largest
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            totalSum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Helper method to compute Greatest Common Divisor (GCD)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
