class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        # Pointer for the position to place the next non-val element
        k = 0
        
        for i in range(len(nums)):
            # If the current element is NOT the value we want to remove
            if nums[i] != val:
                # Move it to the front at index k
                nums[k] = nums[i]
                # Increment k to the next available spot
                k += 1
                
        # k now represents the number of elements not equal to val
        return k
