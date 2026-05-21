class Solution(object):
    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        # Iterate from the end of the list to the beginning
        for i in range(len(digits) - 1, -1, -1):
            if digits[i] < 9:
                digits[i] += 1
                return digits
            
            # If the digit is 9, it becomes 0
            digits[i] = 0
        
        # If all digits were 9, we need an extra 1 at the start
        return [1] + digits
