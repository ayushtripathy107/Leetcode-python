class Solution(object):
    def totalWaviness(self, num1, num2):
        def solve(n_str):
            memo = {}

            def dp(i, is_less, is_started, p1, p2):
                state = (i, is_less, is_started, p1, p2)
                if i == len(n_str):
                    return 0
                if state in memo:
                    return memo[state]

                res = 0
                upper = int(n_str[i]) if not is_less else 9
                
                for d in range(upper + 1):
                    new_is_less = is_less or (d < upper)
                    new_is_started = is_started or (d > 0)
                    
                    # Count waviness contribution of current digit 'd'
                    waviness = 0
                    if is_started and p1 is not None and p2 is not None:
                        if (p2 < p1 > d) or (p2 > p1 < d):
                            waviness = 1
                    
                    # Add current waviness * number of ways to complete suffix
                    if waviness:
                        res += count_ways(i + 1, new_is_less, new_is_started, d, p1)
                    
                    # Recursively add waviness from future digits
                    res += dp(i + 1, new_is_less, new_is_started, 
                              d if new_is_started else None, 
                              p1 if new_is_started else None)
                
                memo[state] = res
                return res

            memo_ways = {}
            def count_ways(i, is_less, is_started, p1, p2):
                state = (i, is_less, is_started, p1, p2)
                if i == len(n_str):
                    return 1
                if state in memo_ways:
                    return memo_ways[state]
                
                ways = 0
                upper = int(n_str[i]) if not is_less else 9
                for d in range(upper + 1):
                    ways += count_ways(i + 1, is_less or (d < upper), is_started or (d > 0), d, p1)
                
                memo_ways[state] = ways
                return ways

            return dp(0, False, False, None, None)

        return solve(str(num2)) - solve(str(num1 - 1))
