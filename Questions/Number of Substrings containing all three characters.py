class Solution(object):
    def numberOfSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        # Track last seen positions of a, b, and c
        last_pos = {'a': -1, 'b': -1, 'c': -1}
        count = 0
        
        for i, char in enumerate(s):
            last_pos[char] = i
            
            # If we've seen all three characters at least once
            if -1 not in last_pos.values():
                # Any substring starting from index 0 to min(last_pos) 
                # and ending at i is valid.
                count += min(last_pos.values()) + 1
                
        return count
