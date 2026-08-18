import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k equals the length of the array
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 2: k is 1
        if (k == 1) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        int firstElement = nums[0];
        int lastElement = nums[n - 1];
        
        int firstCount = 0;
        int lastCount = 0;

        for (int num : nums) {
            if (num == firstElement) firstCount++;
            if (num == lastElement) lastCount++;
        }

        int ans = -1;
        if (firstCount == 1) {
            ans = Math.max(ans, firstElement);
        }
        if (lastCount == 1) {
            ans = Math.max(ans, lastElement);
        }

        return ans;
    }
}
