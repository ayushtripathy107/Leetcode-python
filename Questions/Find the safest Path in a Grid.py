import heapq
from collections import deque

class Solution(object):
    def maximumSafenessFactor(self, grid):
        n = len(grid)
        if grid[0][0] == 1 or grid[n-1][n-1] == 1:
            return 0

        # 1. Multi-source BFS to find distance to nearest thief
        dist = [[-1] * n for _ in range(n)]
        queue = deque()
        for r in range(n):
            for c in range(n):
                if grid[r][c] == 1:
                    dist[r][c] = 0
                    queue.append((r, c))

        while queue:
            r, c = queue.popleft()
            for dr, dc in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                nr, nc = r + dr, c + dc
                if 0 <= nr < n and 0 <= nc < n and dist[nr][nc] == -1:
                    dist[nr][nc] = dist[r][c] + 1
                    queue.append((nr, nc))

        # 2. Max-Heap (Dijkstra) to find the max safeness path
        # Store (-safeness, r, c) to use heapq as a max-heap
        max_heap = [(-dist[0][0], 0, 0)]
        visited = [[False] * n for _ in range(n)]
        visited[0][0] = True

        while max_heap:
            d, r, c = heapq.heappop(max_heap)
            d = -d # Convert back to positive
            
            if r == n - 1 and c == n - 1:
                return d

            for dr, dc in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                nr, nc = r + dr, c + dc
                if 0 <= nr < n and 0 <= nc < n and not visited[nr][nc]:
                    visited[nr][nc] = True
                    # The safeness of a path is the minimum distance encountered
                    heapq.heappush(max_heap, (-min(d, dist[nr][nc]), nr, nc))
        
        return 0
