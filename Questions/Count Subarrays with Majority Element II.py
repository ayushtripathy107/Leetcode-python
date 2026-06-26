class Solution(object):
    def countMajoritySubarrays(self, nums, target):
        n = len(nums)
        # Offset to handle negative prefix sums (min sum is -n)
        offset = n + 1
        bit = [0] * (2 * n + 2)
        
        def update(idx, val):
            idx += offset
            while idx < len(bit):
                bit[idx] += val
                idx += idx & (-idx)
        
        def query(idx):
            idx += offset
            s = 0
            while idx > 0:
                s += bit[idx]
                idx -= idx & (-idx)
            return s

        count = 0
        current_sum = 0
        # Initial prefix sum is 0
        update(0, 1)
        
        for num in nums:
            current_sum += 1 if num == target else -1
            # We need previous_sum < current_sum
            count += query(current_sum - 1)
            update(current_sum, 1)
            
        return count
