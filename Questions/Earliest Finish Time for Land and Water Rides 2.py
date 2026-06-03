class Solution(object):
    def earliestFinishTime(self, landStartTime, landDuration, waterStartTime, waterDuration):
        def get_min_total_finish(start1, dur1, start2, dur2):
            # Step 1: Find the earliest any ride in category 1 can finish
            min_f1 = float('inf')
            for s, d in zip(start1, dur1):
                min_f1 = min(min_f1, s + d)
            
            # Step 2: Find the earliest any ride in category 2 can finish after min_f1
            min_f2 = float('inf')
            for s, d in zip(start2, dur2):
                finish_time = max(min_f1, s) + d
                min_f2 = min(min_f2, finish_time)
            return min_f2

        # Case 1: Land then Water
        res1 = get_min_total_finish(landStartTime, landDuration, waterStartTime, waterDuration)
        # Case 2: Water then Land
        res2 = get_min_total_finish(waterStartTime, waterDuration, landStartTime, landDuration)
        
        return min(res1, res2)
