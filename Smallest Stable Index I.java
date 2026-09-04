class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Step 1: Precompute the minimums from the right side
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }

        // Step 2: Iterate from left to right, maintaining the running maximum
        int maxLeft = nums[0];
        for (int i = 0; i < n; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            
            // Calculate instability score
            int instabilityScore = maxLeft - minRight[i];
            
            // Return the first index that meets the condition
            if (instabilityScore <= k) {
                return i;
            }
        }

        return -1;
    }
}
