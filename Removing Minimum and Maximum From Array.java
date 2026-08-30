class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        // Find the indices of the minimum and maximum elements
        int minIdx = 0;
        int maxIdx = 0;
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            }
            if (nums[k] > nums[maxIdx]) {
                maxIdx = k;
            }
        }

        // Order the indices so i is always the smaller index
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Option 1: Remove both from the front
        int op1 = j + 1;
        
        // Option 2: Remove both from the back
        int op2 = n - i;
        
        // Option 3: Remove i from the front and j from the back
        int op3 = (i + 1) + (n - j);

        // Return the minimum of all three strategies
        return Math.min(op1, Math.min(op2, op3));
    }
}
