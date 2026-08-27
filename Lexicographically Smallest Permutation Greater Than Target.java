import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        char[] result = new char[n];
        if (canConstruct(0, true, counts, target, result)) {
            return new String(result);
        }
        return "";
    }
    
    private boolean canConstruct(int index, boolean isBound, int[] counts, String target, char[] result) {
        int n = target.length();
        if (index == n) {
            // If we successfully placed all characters, check if it's strictly greater
            // If isBound is still true, it means it is exactly equal to target, which is invalid.
            return !isBound;
        }
        
        if (!isBound) {
            // If we are no longer bound by target, greedily pick the smallest available characters
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0) {
                    counts[i]--;
                    result[index] = (char) ('a' + i);
                    if (canConstruct(index + 1, false, counts, target, result)) {
                        return true;
                    }
                    counts[i]++; // Backtrack
                }
            }
            return false;
        }
        
        // Case 1: Try to match the current target character exactly to keep prefix identical
        int targetCharIdx = target.charAt(index) - 'a';
        if (counts[targetCharIdx] > 0) {
            counts[targetCharIdx]--;
            result[index] = target.charAt(index);
            if (canConstruct(index + 1, true, counts, target, result)) {
                return true;
            }
            counts[targetCharIdx]++; // Backtrack
        }
        
        // Case 2: Try to pick a character strictly greater than target[index]
        for (int i = targetCharIdx + 1; i < 26; i++) {
            if (counts[i] > 0) {
                counts[i]--;
                result[index] = (char) ('a' + i);
                // Since this character is strictly greater, subsequent choices are not bound by target
                if (canConstruct(index + 1, false, counts, target, result)) {
                    return true;
                }
                counts[i]++; // Backtrack
            }
        }
        
        return false;
    }
}
