class Solution(object):
    def angleClock(self, hour, minutes):
        # 1. Calculate the position of the minute hand
        # 360 degrees / 60 minutes = 6 degrees per minute
        minute_angle = minutes * 6
        
        # 2. Calculate the position of the hour hand
        # 360 degrees / 12 hours = 30 degrees per hour
        # Plus the shift caused by minutes: 30 degrees / 60 mins = 0.5 degrees per minute
        hour_angle = (hour % 12) * 30 + minutes * 0.5
        
        # 3. Find the absolute difference
        diff = abs(hour_angle - minute_angle)
        
        # 4. Return the smaller angle
        return min(diff, 360 - diff)
