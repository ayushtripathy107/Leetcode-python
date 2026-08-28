import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // 1. Check if a valid palindrome can be formed
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // 2. Generate the base characters for the first half
        int halfLen = n / 2;
        char[] half = new char[halfLen];
        int index = 0;
        for (int i = 0; i < 26; i++) {
            int targetCount = count[i] / 2;
            for (int j = 0; j < targetCount; j++) {
                half[index++] = (char) ('a' + i);
            }
        }

        // 3. Find the smallest palindromic permutation strictly greater than target
        // We will try to find the lexicographically smallest arrangement of 'half'
        // that makes the complete palindrome strictly greater than 'target'.
        char[] resultHalf = new char[halfLen];
        if (findNext(0, half, new boolean[halfLen], resultHalf, target, midChar, false)) {
            return buildPalindrome(resultHalf, midChar);
        }

        return "";
    }

    private boolean findNext(int idx, char[] half, boolean[] used, char[] current, 
                             String target, char midChar, boolean isGreater) {
        int halfLen = half.length;
        if (idx == halfLen) {
            // If we are already guaranteed to be greater, or if the remaining part makes it greater
            if (isGreater) return true;
            
            // If the first half is identical to the target's first half, check the middle/back half
            String fullStr = buildPalindrome(current, midChar);
            return fullStr.compareTo(target) > 0;
        }

        char targetChar = target.charAt(idx);
        char lastTried = 0;

        for (int i = 0; i < halfLen; i++) {
            if (used[i] || half[i] == lastTried) continue;
            
            // To find the lexicographically smallest, we should skip characters smaller 
            // than targetChar unless we have already placed a strictly greater character earlier.
            if (!isGreater && half[i] < targetChar) continue;

            used[i] = true;
            current[idx] = half[i];
            lastTried = half[i];

            boolean nextGreater = isGreater || (half[i] > targetChar);
            if (findNext(idx + 1, half, used, current, target, midChar, nextGreater)) {
                return true;
            }

            used[i] = false; // Backtrack
        }
        return false;
    }

    private String buildPalindrome(char[] half, char midChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if (midChar != 0) {
            sb.append(midChar);
        }
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }
        return sb.toString();
    }
}
