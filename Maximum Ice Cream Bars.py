class Solution(object):
    def maxIceCream(self, costs, coins):
        """
        :type costs: List[int]
        :type coins: int
        :rtype: int
        """
        if not costs:
            return 0
        
        # Determine the range for counting sort
        max_cost = max(costs)
        count = [0] * (max_cost + 1)
        
        # Fill the frequency array
        for cost in costs:
            count[cost] += 1
            
        ice_cream_count = 0
        
        # Iterate through the prices from cheapest to most expensive
        for price in range(1, max_cost + 1):
            if count[price] == 0:
                continue
                
            if coins < price:
                break
                
            # Determine how many bars of this price we can afford
            can_buy = min(count[price], coins // price)
            
            ice_cream_count += can_buy
            coins -= can_buy * price
            
        return ice_cream_count
