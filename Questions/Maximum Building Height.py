class Solution(object):
    def maxBuilding(self, n, restrictions):
        # 1. Add boundaries and sort
        restrictions.append([1, 0])
        restrictions.sort()
        
        # If the last building isn't restricted, add a virtual limit based on n
        if restrictions[-1][0] != n:
            restrictions.append([n, n - 1])
            
        m = len(restrictions)
        
        # 2. Forward pass: limit height based on previous restriction
        for i in range(1, m):
            dist = restrictions[i][0] - restrictions[i-1][0]
            restrictions[i][1] = min(restrictions[i][1], restrictions[i-1][1] + dist)
            
        # 3. Backward pass: limit height based on next restriction
        for i in range(m - 2, -1, -1):
            dist = restrictions[i+1][0] - restrictions[i][0]
            restrictions[i][1] = min(restrictions[i][1], restrictions[i+1][1] + dist)
            
        # 4. Calculate the peak between every pair of restricted buildings
        ans = 0
        for i in range(1, m):
            id1, h1 = restrictions[i-1]
            id2, h2 = restrictions[i]
            # Peak height formula: (distance + h1 + h2) // 2
            peak = (id2 - id1 + h1 + h2) // 2
            ans = max(ans, peak)
            
        return ans
