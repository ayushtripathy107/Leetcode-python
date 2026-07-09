class Solution(object):
    def pathExistenceQueries(self, n, nums, maxDiff, queries):
        # Step 1: Assign each node to a connected component group
        group_id = [0] * n
        current_group = 0
        
        for i in range(1, n):
            # Since nums is sorted, we only check the gap with the previous element
            if nums[i] - nums[i-1] > maxDiff:
                current_group += 1
            group_id[i] = current_group
            
        # Step 2: Process queries
        answer = []
        for u, v in queries:
            # Path exists if both nodes are in the same group
            answer.append(group_id[u] == group_id[v])
            
        return answer
