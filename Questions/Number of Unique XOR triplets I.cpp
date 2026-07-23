class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int n = nums.size();
        
        // Base cases for small permutations
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Find the highest bit set in n
        int highest_bit = 0;
        for (int i = 31; i >= 0; i--) {
            if ((n >> i) & 1) {
                highest_bit = i;
                break;
            }
        }
        
        // The total number of unique values from 0 to (2^(highest_bit + 1) - 1)
        return 1 << (highest_bit + 1);
    }
};
