class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        # Pointer to track the position for the next non-zero element
        non_zero_pos = 0
        
        # Iterate through the array with a scanner pointer
        for i in range(len(nums)):
            # If the current element is not zero
            if nums[i] != 0:
                # Swap the elements at the scanner and non_zero_pos pointers
                nums[non_zero_pos], nums[i] = nums[i], nums[non_zero_pos]
                # Move the non-zero tracker forward
                non_zero_pos += 1
