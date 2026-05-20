class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n <= 2:
            return n
        
        # Initialize the first two steps
        first, second = 1, 2
        
        for i in range(3, n + 1):
            # Calculate current ways and shift pointers
            current = first + second
            first = second
            second = current
            
        return second
