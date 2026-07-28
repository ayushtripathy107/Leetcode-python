class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder t = new StringBuilder();
        String ch = "";
        for (char c = 'a'; c <= 'z'; c++) {
            int v = cnt[c - 'a'] / 2;
            for (int i = 0; i < v; i++) {
                t.append(c);
            }
            cnt[c - 'a'] -= v * 2;
            if (cnt[c - 'a'] == 1) {
                ch = String.valueOf(c);
            }
        }
        StringBuilder ans = new StringBuilder(t);
        ans.append(ch);
        ans.append(t.reverse());
        return ans.toString();
    }
}
