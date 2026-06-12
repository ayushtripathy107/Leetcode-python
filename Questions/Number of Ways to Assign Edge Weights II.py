class Solution(object):
    def assignEdgeWeights(self, edges, queries):
        n = len(edges) + 1
        adj = [[] for _ in range(n + 1)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
        
        # Binary Lifting setup for LCA
        LOG = n.bit_length()
        up = [[0] * LOG for _ in range(n + 1)]
        depth = [0] * (n + 1)
        
        # DFS to compute depths and first ancestors
        stack = [(1, 0, 0)]
        while stack:
            u, p, d = stack.pop()
            depth[u] = d
            up[u][0] = p
            for v in adj[u]:
                if v != p:
                    stack.append((v, u, d + 1))
        
        # Precompute binary lifting table
        for j in range(1, LOG):
            for i in range(1, n + 1):
                up[i][j] = up[up[i][j-1]][j-1]
                
        def get_lca(u, v):
            if depth[u] < depth[v]: u, v = v, u
            for j in range(LOG - 1, -1, -1):
                if depth[u] - (1 << j) >= depth[v]:
                    u = up[u][j]
            if u == v: return u
            for j in range(LOG - 1, -1, -1):
                if up[u][j] != up[v][j]:
                    u, v = up[u][j], up[v][j]
            return up[u][0]

        MOD = 10**9 + 7
        ans = []
        for u, v in queries:
            lca = get_lca(u, v)
            L = depth[u] + depth[v] - 2 * depth[lca]
            if L == 0:
                ans.append(0)
            else:
                ans.append(pow(2, L - 1, MOD))
        return ans
