class Solution {
    public boolean predictTheWinner(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return helper(nums, 0, nums.length - 1, memo) >= 0;
    }
    
    private int helper(int[] nums, int left, int right, Integer[][] memo) {
        if (left == right) {
            return nums[left];
        }
        if (memo[left][right] != null) {
            return memo[left][right];
        }
        int leftChoice = nums[left] - helper(nums, left + 1, right, memo);
        int rightChoice = nums[right] - helper(nums, left, right - 1, memo);
        memo[left][right] = Math.max(leftChoice, rightChoice);
        return memo[left][right];
    }
}
