class Solution(object):
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """
        max_len = 0
        stack = [-1]  # Base index
        
        for i, char in enumerate(s):
            if char == '(':
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    # If empty, this ')' is an anchor for the next potential valid string
                    stack.append(i)
                else:
                    # Length is current index minus index of the last unmatched '('
                    max_len = max(max_len, i - stack[-1])
        
        return max_len
