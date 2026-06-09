class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        # Define 32-bit signed integer boundaries
        MIN_INT, MAX_INT = -2**31, 2**31 - 1
        
        # Determine sign and work with absolute value
        sign = -1 if x < 0 else 1
        res = 0
        x = abs(x)
        
        while x != 0:
            # Extract last digit
            pop = x % 10
            x //= 10
            
            # Check for overflow before updating result
            if res > (MAX_INT - pop) // 10:
                return 0
                
            res = res * 10 + pop
            
        return res * sign
