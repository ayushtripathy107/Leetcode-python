import heapq

class Solution(object):
    def findSafeWalk(self, grid, health):
        m, n = len(grid), len(grid[0])
        # directions for moving up, down, left, right
        dirs = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        
        # dist[r][c] will store the minimum health lost to reach (r, c)
        min_loss = [[float('inf')] * n for _ in range(m)]
        
        # Initial health loss depends on the starting cell
        start_loss = grid[0][0]
        min_loss[0][0] = start_loss
        
        # Priority Queue: (health_lost, r, c)
        pq = [(start_loss, 0, 0)]
        
        while pq:
            curr_loss, r, c = heapq.heappop(pq)
            
            # If we reached the end, check if we have at least 1 health left
            if r == m - 1 and c == n - 1:
                return curr_loss < health
            
            # If we've already found a better path to this cell, skip
            if curr_loss > min_loss[r][c]:
                continue
                
            for dr, dc in dirs:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < m and 0 <= nc < n:
                    new_loss = curr_loss + grid[nr][nc]
                    # If this new path is better and doesn't deplete health
                    if new_loss < min_loss[nr][nc] and new_loss < health:
                        min_loss[nr][nc] = new_loss
                        heapq.heappush(pq, (new_loss, nr, nc))
        
        return False
