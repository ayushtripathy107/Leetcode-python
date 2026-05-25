class Solution(object):
    def canReach(self, s, minJump, maxJump):
        n = len(s)
        # dp[i] will be True if index i is reachable
        dp = [False] * n
        dp[0] = True
        
        # reachable_count tracks the number of reachable indices 
        # in the window [i - maxJump, i - minJump]
        reachable_count = 0
        
        for i in range(1, n):
            # Add dp[i - minJump] to the window
            if i >= minJump:
                if dp[i - minJump]:
                    reachable_count += 1
            
            # Remove dp[i - maxJump - 1] from the window
            if i > maxJump:
                if dp[i - maxJump - 1]:
                    reachable_count -= 1
            
            # If current character is '0' and the window has reachable indices
            if s[i] == '0' and reachable_count > 0:
                dp[i] = True
                
        return dp[n-1]
