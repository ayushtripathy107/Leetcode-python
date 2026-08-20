import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Step 1: Distribute the first two elements
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Step 2: Distribute the remaining elements based on conditions
        for (int i = 2; i < nums.length; i++) {
            int lastArr1 = arr1.get(arr1.size() - 1);
            int lastArr2 = arr2.get(arr2.size() - 1);
            
            if (lastArr1 > lastArr2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Step 3: Concatenate arr1 and arr2 into the final result array
        int[] result = new int[nums.length];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}
