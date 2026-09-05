class Solution(object):
    def firstStableIndex(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        n = len(nums)
        if n == 0:
            return -1
        
        # Step 1: Precompute prefix maximums
        pref_max = [0] * n
        pref_max[0] = nums[0]
        for i in range(1, n):
            pref_max[i] = max(pref_max[i-1], nums[i])
            
        # Step 2: Precompute suffix minimums
        suff_min = [0] * n
        suff_min[n-1] = nums[n-1]
        for i in range(n-2, -1, -1):
            suff_min[i] = min(suff_min[i+1], nums[i])
            
        # Step 3: Find the first index with an instability score <= k
        for i in range(n):
            instability_score = pref_max[i] - suff_min[i]
            if instability_score <= k:
                return i
                
        return -1
