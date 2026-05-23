class Solution(object):
    def check(self, nums):
        count = 0
        n = len(nums)
        
        for i in range(n):
            # Compare current element with the next (using % for wrap-around)
            if nums[i] > nums[(i + 1) % n]:
                count += 1
        
        # If there's 0 or 1 "drop", it's a sorted rotated array
        return count <= 1
