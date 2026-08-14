class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int maxLen = 0;
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            count[s.charAt(r) - 'a']++;

            // Shrink window if any character count exceeds 2
            while (count[s.charAt(r) - 'a'] > 2) {
                count[s.charAt(l) - 'a']--;
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        return maxLen;
    }
}
