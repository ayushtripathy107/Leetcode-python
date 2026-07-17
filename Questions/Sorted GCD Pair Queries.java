import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum value in nums to define our boundaries
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Count occurrences of each number in the input array
        int[] counts = new int[maxVal + 1];
        for (int num : nums) {
            counts[num]++;
        }

        // Step 2 & 3: Compute the exact number of pairs for each GCD
        long[] exactGcdPairs = new long[maxVal + 1];
        
        // Iterate backwards to apply inclusion-exclusion correctly
        for (int i = maxVal; i >= 1; i--) {
            long totalMultiples = 0;
            
            // Count how many numbers in nums are multiples of i
            for (int j = i; j <= maxVal; j += i) {
                totalMultiples += counts[j];
            }
            
            // Number of pairs where both elements are multiples of i
            long totalPairs = totalMultiples * (totalMultiples - 1) / 2;
            
            // Subtract pairs that have a strictly larger common multiple (e.g., 2i, 3i...)
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= exactGcdPairs[j];
            }
            
            exactGcdPairs[i] = totalPairs;
        }

        // Step 4: Create a prefix sum array of the counts of exact GCD pairs
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + exactGcdPairs[i];
        }

        // Step 5: Answer each query using binary search over the prefix sums
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = binarySearch(prefixSums, queries[i]);
        }

        return answer;
    }

    // Helper method to find the smallest GCD value whose prefix sum is strictly greater than the query index
    private int binarySearch(long[] prefixSums, long target) {
        int low = 1;
        int high = prefixSums.length - 1;
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSums[mid] > target) {
                result = mid; // Candidate found, try to look for a smaller GCD index
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
