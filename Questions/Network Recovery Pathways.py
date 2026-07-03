import heapq

class Solution(object):
    def findMaxPathScore(self, edges, online, k):
        n = len(online)
        adj = [[] for _ in range(n)]
        weights = set()
        
        for u, v, cost in edges:
            if online[u] and online[v]:
                adj[u].append((v, cost))
                weights.add(cost)
        
        sorted_weights = sorted(list(weights))
        
        def can_reach(min_allowed_weight):
            # Dijkstra to find shortest path using only edges >= min_allowed_weight
            pq = [(0, 0)]  # (total_cost, current_node)
            min_costs = {0: 0}
            
            while pq:
                curr_total, u = heapq.heappop(pq)
                
                if curr_total > min_costs.get(u, float('inf')):
                    continue
                if u == n - 1:
                    return True
                
                for v, cost in adj[u]:
                    if cost >= min_allowed_weight:
                        new_cost = curr_total + cost
                        if new_cost <= k and new_cost < min_costs.get(v, float('inf')):
                            min_costs[v] = new_cost
                            heapq.heappush(pq, (new_cost, v))
            return False

        ans = -1
        low, high = 0, len(sorted_weights) - 1
        
        while low <= high:
            mid = (low + high) // 2
            if can_reach(sorted_weights[mid]):
                ans = sorted_weights[mid]
                low = mid + 1
            else:
                high = mid - 1
                
        return ans
