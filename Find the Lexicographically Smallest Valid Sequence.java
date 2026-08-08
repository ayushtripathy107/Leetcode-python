class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // last[j] stores the largest index in word1 from which 
        // the suffix word2[j...] can be matched perfectly without any modifications.
        int[] last = new int[m + 1];
        java.util.Arrays.fill(last, -1);
        last[m] = n; // Base case: empty suffix can always be matched at the end
        
        int p2 = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (p2 >= 0 && word1.charAt(i) == word2.charAt(p2)) {
                last[p2] = i;
                p2--;
            }
        }
        
        int[] ans = new int[m];
        int j = 0; // Pointer for word2
        boolean changed = false; // Tracks if we used our 1-character modification
        
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } else if (!changed && last[j + 1] > i) {
                // If they don't match, check if we can safely mutate word1[i] to word2[j]
                // This is valid if the rest of word2 (j+1 onwards) can be matched after index i
                ans[j] = i;
                j++;
                changed = true; // Use up the allowed modification
            }
        }
        
        // If we successfully matched all characters of word2, return the sequence
        return j == m ? ans : new int[0];
    }
}
