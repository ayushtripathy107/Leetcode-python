from sortedcontainers import SortedList

class SegmentTree:
    def __init__(self, size):
        self.n = size
        self.tree = [0] * (4 * size)

    def update(self, index, value):
        self._update(1, 0, self.n - 1, index, value)

    def _update(self, node, start, end, index, value):
        if start == end:
            self.tree[node] = value
            return
        mid = (start + end) // 2
        if index <= mid:
            self._update(2 * node, start, mid, index, value)
        else:
            self._update(2 * node + 1, mid + 1, end, index, value)
        self.tree[node] = max(self.tree[2 * node], self.tree[2 * node + 1])

    def query(self, L, R):
        if L > R:
            return 0
        return self._query(1, 0, self.n - 1, L, R)

    def _query(self, node, start, end, L, R):
        if R < start or end < L:
            return 0
        if L <= start and end <= R:
            return self.tree[node]
        mid = (start + end) // 2
        p1 = self._query(2 * node, start, mid, L, R)
        p2 = self._query(2 * node + 1, mid + 1, end, L, R)
        return max(p1, p2)

class Solution(object):
    def getResults(self, queries):
        """
        :type queries: List[List[int]]
        :rtype: List[bool]
        """
        # Determine the maximum coordinate bounding box dynamically
        max_x = 0
        for q in queries:
            max_x = max(max_x, q[1])
        
        # Upper bound size for the segment tree
        M = max_x + 2
        seg_tree = SegmentTree(M)
        
        # Track obstacles. Initially, we have 0 and an implicit boundary at infinity
        obstacles = SortedList([0, M])
        
        # Initially, the gap from 0 to M is M
        seg_tree.update(M, M)
        
        results = []
        
        for q in queries:
            if q[0] == 1:
                x = q[1]
                # Find where x fits among current obstacles
                idx = obstacles.bisect_left(x)
                prev_obs = obstacles[idx - 1]
                next_obs = obstacles[idx]
                
                # Insert x
                obstacles.add(x)
                
                # Update distances in segment tree
                seg_tree.update(x, x - prev_obs)
                seg_tree.update(next_obs, next_obs - x)
                
            elif q[0] == 2:
                x, sz = q[1], q[2]
                
                # Find the largest obstacle <= x
                idx = obstacles.bisect_right(x)
                prev_obs = obstacles[idx - 1]
                
                # Check maximum gap completely to the left of prev_obs
                max_gap = seg_tree.query(0, prev_obs)
                
                # Check the remaining open gap up to x
                max_gap = max(max_gap, x - prev_obs)
                
                results.append(max_gap >= sz)
                
        return results
