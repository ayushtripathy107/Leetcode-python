class Solution(object):
    def countMajoritySubarrays(self, nums, target):
        n = len(nums)
        # Prefix sums can range from -n to n. 
        # Use an offset to keep indices positive for a frequency array.
        offset = n
        count_map = [0] * (2 * n + 1)
        
        current_sum = 0
        result = 0
        
        # Initial prefix sum (before any elements) is 0
        count_map[current_sum + offset] = 1
        
        # Track the total number of prefix sums seen that are smaller than current_sum
        # Since we iterate and update, we can use a Fenwick tree for O(N log N)
        # or a running total if we process the counts carefully.
        
        # For simplicity and speed in this specific range, a Fenwick Tree is standard:
        bit = [0] * (2 * n + 2)
        
        def update(idx, val):
            idx += 1 # 1-based indexing for BIT
            while idx < len(bit):
                bit[idx] += val
                idx += idx & (-idx)
                
        def query(idx):
            idx += 1
            s = 0
            while idx > 0:
                s += bit[idx]
                idx -= idx & (-idx)
            return s

        update(0 + offset, 1)
        for x in nums:
            current_sum += 1 if x == target else -1
            # Count how many previous prefix sums are strictly less than current_sum
            result += query(current_sum + offset - 1)
            update(current_sum + offset, 1)
            
        return result
