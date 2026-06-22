from collections import Counter

class Solution(object):
    def maxNumberOfBalloons(self, text):
        """
        :type text: str
        :rtype: int
        """
        # Count the frequency of each character in the input text
        counts = Counter(text)
        
        # Calculate how many full words can be formed for each required character
        # Note: 'l' and 'o' appear twice, so we divide their counts by 2
        return min(
            counts['b'], 
            counts['a'], 
            counts['l'] // 2, 
            counts['o'] // 2, 
            counts['n']
        )
