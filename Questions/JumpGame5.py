class Solution(object):
    def maxJumps(self, arr, d):
        n = len(arr)
        memo = {}

        def dp(i):
            if i in memo:
                return memo[i]
            
            res = 1
            # Try jumping to the right
            for j in range(i + 1, min(i + d + 1, n)):
                if arr[i] > arr[j]:
                    res = max(res, 1 + dp(j))
                else:
                    # Condition arr[i] > arr[k] for all k between i and j is broken
                    break
            
            # Try jumping to the left
            for j in range(i - 1, max(-1, i - d - 1), -1):
                if arr[i] > arr[j]:
                    res = max(res, 1 + dp(j))
                else:
                    break
            
            memo[i] = res
            return res

        return max(dp(i) for i in range(n))
