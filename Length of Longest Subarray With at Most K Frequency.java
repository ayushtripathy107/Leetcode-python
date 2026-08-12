import java.util.HashMap;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Add the current element to the frequency map
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
            
            // If the current element's frequency exceeds k, shrink the window from the left
            while (countMap.get(nums[right]) > k) {
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                left++;
            }
            
            // Calculate the maximum length of a valid subarray
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
