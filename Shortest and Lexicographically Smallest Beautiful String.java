class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int left = 0;
        int countones = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countones++;
            }

            // Shrink from the left to minimize the window size when count exceeds or equals k
            while (countones == k) {
                // Remove leading zeros to find the actual shortest substring for this ending point
                while (s.charAt(left) == '0') {
                    left++;
                }

                String currentString = s.substring(left, right + 1);

                // Update answer if it's the first valid substring found, 
                // or if it's shorter, or if it's equal length but lexicographically smaller
                if (ans.equals("") || currentString.length() < ans.length()) {
                    ans = currentString;
                } else if (currentString.length() == ans.length() && currentString.compareTo(ans) < 0) {
                    ans = currentString;
                }

                // Move left pointer forward to look for the next window
                if (s.charAt(left) == '1') {
                    countones--;
                }
                left++;
            }
        }

        return ans;
    }
}
