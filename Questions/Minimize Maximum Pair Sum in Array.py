class Solution(object):
    def minPairSum(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # Step 1: Sort the array
        nums.sort()
        
        max_sum = 0
        n = len(nums)
        
        # Step 2: Pair the smallest with the largest
        for i in range(n // 2):
            current_pair_sum = nums[i] + nums[n - 1 - i]
            # Step 3: Track the maximum sum encountered
            if current_pair_sum > max_sum:
                max_sum = current_pair_sum
                
        return max_sum
