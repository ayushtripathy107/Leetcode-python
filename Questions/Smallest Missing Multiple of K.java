import java.util.HashSet;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Step 1: Add all numbers from nums into a HashSet for O(1) lookups
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Step 2: Check multiples of k sequentially starting from 1 * k
        int currentMultiple = k;
        while (set.contains(currentMultiple)) {
            currentMultiple += k;
        }
        
        // Step 3: Return the first multiple missing from the set
        return currentMultiple;
    }
}
