class Solution(object):
    def minElement(self, nums):
        return min(sum(int(digit) for digit in str(n)) for n in nums)
