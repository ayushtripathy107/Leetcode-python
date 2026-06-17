class Solution(object):
    def processStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        # Step 1: Forward pass to compute lengths after each operation
        lengths = []
        curr_len = 0
        
        for char in s:
            if char.islower():
                curr_len += 1
            elif char == '*':
                if curr_len > 0:
                    curr_len -= 1
            elif char == '#':
                curr_len *= 2
            elif char == '%':
                # Reversing doesn't change the length
                pass
            
            lengths.append(curr_len)
            
            # Optimization: Stop if current length comfortably exceeds k
            if curr_len > 10**15:
                break
        
        # If k is out of bounds of the final string length
        if k >= curr_len:
            return "."
            
        # Step 2: Backward pass to decode the index k
        for i in range(len(lengths) - 1, -1, -1):
            char = s[i]
            
            if char.islower():
                # If k is pointing to the newly added character
                if k == lengths[i] - 1:
                    return char
                # Otherwise, it belongs to the string before this character
                continue
                
            elif char == '*':
                # 'lengths' already accounts for the pre-pop state when looking backwards
                continue
                
            elif char == '#':
                # The string before doubling
                prev_len = lengths[i] // 2
                # Wrap k back into the original first half range
                if k >= prev_len:
                    k %= prev_len
                    
            elif char == '%':
                # Mirror the index across the midpoint of the current length
                k = lengths[i] - 1 - k
                
        return "."
