import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Create a sorted copy of the array
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        // Maps each number to its corresponding group ID
        Map<Integer, Integer> numToGroup = new HashMap<>();
        // Maps group ID to a queue of its sorted elements
        Map<Integer, Queue<Integer>> groupToElements = new HashMap<>();
        
        int groupId = 0;
        numToGroup.put(sortedNums[0], groupId);
        groupToElements.put(groupId, new LinkedList<>());
        groupToElements.get(groupId).offer(sortedNums[0]);
        
        // Step 2: Form groups based on the limit condition
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                groupId++;
            }
            numToGroup.put(sortedNums[i], groupId);
            if (!groupToElements.containsKey(groupId)) {
                groupToElements.put(groupId, new LinkedList<>());
            }
            groupToElements.get(groupId).offer(sortedNums[i]);
        }
        
        // Step 3: Reconstruct the result array using the original order of indices
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int currGroupId = numToGroup.get(nums[i]);
            result[i] = groupToElements.get(currGroupId).poll();
        }
        
        return result;
    }
}
