class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        # Ensure nums1 is the shorter array for binary search efficiency
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
            
        m, n = len(nums1), len(nums2)
        low, high = 0, m
        
        while low <= high:
            partitionX = (low + high) // 2
            partitionY = (m + n + 1) // 2 - partitionX
            
            # Edge cases for partition boundaries
            maxLeftX = nums1[partitionX - 1] if partitionX != 0 else float('-inf')
            minRightX = nums1[partitionX] if partitionX != m else float('inf')
            
            maxLeftY = nums2[partitionY - 1] if partitionY != 0 else float('-inf')
            minRightY = nums2[partitionY] if partitionY != n else float('inf')
            
            if maxLeftX <= minRightY and maxLeftY <= minRightX:
                # Correct partition found
                if (m + n) % 2 == 0:
                    return (max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2.0
                else:
                    return float(max(maxLeftX, maxLeftY))
            elif maxLeftX > minRightY:
                # Too far right in nums1, move left
                high = partitionX - 1
            else:
                # Too far left in nums1, move right
                low = partitionX + 1
