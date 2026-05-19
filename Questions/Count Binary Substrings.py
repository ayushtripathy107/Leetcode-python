class Solution(object):
    def countBinarySubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        groups = []
        if not s:
            return 0
        
        # Count consecutive groups: "00110" -> [2, 2, 1]
        count = 1
        for i in range(1, len(s)):
            if s[i] == s[i-1]:
                count += 1
            else:
                groups.append(count)
                count = 1
        groups.append(count)
        
        # Sum the minimum of adjacent groups
        ans = 0
        for i in range(1, len(groups)):
            ans += min(groups[i-1], groups[i])
            
        return ans
