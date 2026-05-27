class Solution(object):
    def numberOfSpecialChars(self, word):
        """
        :type word: str
        :rtype: int
        """
        # Initialize arrays to track indices for the 26 English letters
        last_lower = [-1] * 26
        first_upper = [-1] * 26
        
        # Traverse the string to record the positions
        for i, ch in enumerate(word):
            if ch.islower():
                idx = ord(ch) - ord('a')
                last_lower[idx] = i
            elif ch.isupper():
                idx = ord(ch) - ord('A')
                # Only record the first occurrence of the uppercase letter
                if first_upper[idx] == -1:
                    first_upper[idx] = i
                    
        # Count letters that satisfy all conditions
        special_count = 0
        for i in range(26):
            if last_lower[i] != -1 and first_upper[i] != -1:
                if last_lower[i] < first_upper[i]:
                    special_count += 1
                    
        return special_count
