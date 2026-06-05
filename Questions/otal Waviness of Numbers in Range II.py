class Solution(object):
    def totalWaviness(self, num1, num2):
        """
        :type num1: int
        :type num2: int
        :rtype: int
        """
        
        def countWaviness(n):
            if n < 100:
                return 0
            
            s = str(n)
            length = len(s)
            
            # Memoization table: (idx, is_tight, is_leading, last_digit, prev_digit)
            memo = {}
            
            def dp(idx, is_tight, is_leading, last_digit, prev_digit):
                # Base case: if we reach the end, we can't form any more peaks/valleys
                if idx == length:
                    return 0, 1  # (total_waviness, valid_count)
                
                state = (idx, is_tight, is_leading, last_digit, prev_digit)
                if state in memo:
                    return memo[state]
                
                limit = int(s[idx]) if is_tight else 9
                total_waviness = 0
                total_count = 0
                
                for d in range(limit + 1):
                    next_tight = is_tight and (d == limit)
                    next_leading = is_leading and (d == 0)
                    
                    # Calculate contribution of the transition if a peak/valley is completed
                    # The digit that could be a peak/valley is `last_digit`
                    # It is an interior digit only if `is_leading` was already False when `prev_digit` was placed
                    waviness_contribution = 0
                    if not next_leading and prev_digit != -1 and last_digit != -1:
                        if prev_digit < last_digit and d < last_digit:  # Peak
                            waviness_contribution = 1
                        elif prev_digit > last_digit and d > last_digit:  # Valley
                            waviness_contribution = 1
                    
                    # Determine the new tracking digits for the next state
                    if next_leading:
                        next_last, next_prev = -1, -1
                    else:
                        next_last, next_prev = d, last_digit
                        
                    # Recurse for the rest of the string
                    sub_waviness, sub_count = dp(idx + 1, next_tight, next_leading, next_last, next_prev)
                    
                    # Update totals
                    total_waviness += sub_waviness + (waviness_contribution * sub_count)
                    total_count += sub_count
                    
                memo[state] = (total_waviness, total_count)
                return memo[state]
            
            return dp(0, True, True, -1, -1)[0]

        return countWaviness(num2) - countWaviness(num1 - 1)
