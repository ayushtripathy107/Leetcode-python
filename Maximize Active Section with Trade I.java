import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int cnt1 = 0;

        // Count total '1's in s and collect lengths of contiguous '0' blocks
        List<Integer> zeroBlocks = new ArrayList<>();
        int i = 0;
        
        while (i < n) {
            if (s.charAt(i) == '1') {
                cnt1++;
                i++;
            } else {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                zeroBlocks.add(i - start);
            }
        }

        // If there are fewer than 2 zero blocks, no valid trade can increase the count
        if (zeroBlocks.size() < 2) {
            return cnt1;
        }

        // The maximum net gain of '1's is the maximum sum of two adjacent zero block lengths
        int maxGain = 0;
        for (int j = 0; j < zeroBlocks.size() - 1; j++) {
            maxGain = Math.max(maxGain, zeroBlocks.get(j) + zeroBlocks.get(j + 1));
        }

        return cnt1 + maxGain;
    }
}
