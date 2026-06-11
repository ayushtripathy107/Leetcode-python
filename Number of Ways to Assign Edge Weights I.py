import collections

class Solution(object):
    def assignEdgeWeights(self, edges):
        """
        :type edges: List[List[int]]
        :rtype: int
        """
        if not edges:
            return 0
            
        # 1. Build adjacency list
        adj = collections.defaultdict(list)
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
            
        # 2. Find maximum depth (L) using BFS
        # Root is node 1, depth of root is 0
        max_depth = 0
        queue = collections.deque([(1, 0)]) # (node, current_depth)
        visited = {1}
        
        while queue:
            curr, depth = queue.popleft()
            max_depth = max(max_depth, depth)
            for neighbor in adj[curr]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append((neighbor, depth + 1))
        
        # 3. Result is 2^(L-1) % (10^9 + 7)
        # Note: If L=0 (only one node), problem implies no path/edges.
        if max_depth == 0:
            return 0
            
        MOD = 10**9 + 7
        return pow(2, max_depth - 1, MOD)
