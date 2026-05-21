class Solution(object):
    def longestCommonPrefix(self, arr1, arr2):
        """
        :type arr1: List[int]
        :type arr2: List[int]
        :rtype: int
        """
        prefixes = set()
        
        # Step 1: Store all possible prefixes of numbers in arr1
        for val in arr1:
            while val > 0:
                prefixes.add(val)
                val //= 10
        
        max_len = 0
        
        # Step 2: Check prefixes of numbers in arr2 against the set
        for val in arr2:
            while val > 0:
                if val in prefixes:
                    # Calculate length of the integer by converting to string
                    max_len = max(max_len, len(str(val)))
                    # Since we check from longest to shortest, 
                    # we can break once a match is found for this number
                    break
                val //= 10
                
        return max_len
