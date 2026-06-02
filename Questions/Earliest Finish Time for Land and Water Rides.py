class Solution(object):
    def earliestFinishTime(self, landStartTime, landDuration, waterStartTime, waterDuration):
        """
        :type landStartTime: List[int]
        :type landDuration: List[int]
        :type waterStartTime: List[int]
        :type waterDuration: List[int]
        :rtype: int
        """
        # --- Case 1: Land Ride First, then Water Ride ---
        # Find the absolute earliest possible finish time for ANY land ride
        min_land_finish = min(start + dur for start, dur in zip(landStartTime, landDuration))
        
        # Find the best water ride to transition into after that land ride finishes
        case_land_first = min(max(min_land_finish, start) + dur 
                              for start, dur in zip(waterStartTime, waterDuration))
        
        # --- Case 2: Water Ride First, then Land Ride ---
        # Find the absolute earliest possible finish time for ANY water ride
        min_water_finish = min(start + dur for start, dur in zip(waterStartTime, waterDuration))
        
        # Find the best land ride to transition into after that water ride finishes
        case_water_first = min(max(min_water_finish, start) + dur 
                               for start, dur in zip(landStartTime, landDuration))
        
        # Return the minimum of both sequencing strategies
        return min(case_land_first, case_water_first)
