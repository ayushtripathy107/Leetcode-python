class Solution(object):
    def countCompleteComponents(self, n, edges):
        # 1. Build the adjacency list
        adj = [[] for _ in range(n)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
            
        visited = [False] * n
        complete_count = 0
        
        for i in range(n):
            if not visited[i]:
                # 2. Use BFS/DFS to find all nodes and edges in the current component
                component_nodes = []
                stack = [i]
                visited[i] = True
                
                edge_count = 0
                while stack:
                    u = stack.pop()
                    component_nodes.append(u)
                    # Count edges (each undirected edge is counted twice here)
                    edge_count += len(adj[u])
                    
                    for v in adj[u]:
                        if not visited[v]:
                            visited[v] = True
                            stack.append(v)
                
                # 3. Check if complete: 
                # Number of nodes (V), total edges (E). 
                # In a complete graph, E = V * (V - 1)
                # Since we counted each edge twice, we check: edge_count == V * (V - 1)
                v_count = len(component_nodes)
                if edge_count == v_count * (v_count - 1):
                    complete_count += 1
                    
        return complete_count
