import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // 1. Sort: ascending by start, then descending by end
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int remainingCount = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            int currentEnd = interval[1];
            
            // 2. If the current end extends beyond the maxEnd seen, it's not covered
            if (currentEnd > maxEnd) {
                remainingCount++;
                maxEnd = currentEnd;
            }
        }

        return remainingCount;
    }
}
