class Solution(object):
    def leftRightDifference(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        leftSum = 0
        rightSum = sum(nums)
        answer = []
        
        for num in nums:
            # Update rightSum to exclude the current element
            rightSum -= num
            
            # Append the absolute difference
            answer.append(abs(leftSum - rightSum))
            
            # Update leftSum to include the current element for the next index
            leftSum += num
            
        return answer

