class Solution(object):
    def isPowerOfTwo(self, n):
        """
        :type n: int
        :rtype: bool
        """
        # A power of two must be positive and (n & (n - 1)) must be 0
        return n > 0 and (n & (n - 1)) == 0
