class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        # Handle negative power: x^-n = (1/x)^n
        if n < 0:
            x = 1 / x
            n = -n
        
        result = 1
        current_product = x
        
        while n > 0:
            # If n is odd, multiply result by the current product
            if n % 2 == 1:
                result *= current_product
            
            # Square the base and halve the exponent
            current_product *= current_product
            n //= 2
            
        return result
