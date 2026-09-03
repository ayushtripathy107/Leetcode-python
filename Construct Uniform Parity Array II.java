class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Step 1: Find the minimum odd number in the array
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // If there are no odd numbers, it means the array is already all even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }
        
        // Step 2: Validate even numbers against the minimum odd number
        for (int x : nums1) {
            // If an even number is smaller than the smallest odd number, 
            // we cannot subtract any odd number from it to make it odd.
            if (x % 2 == 0 && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}
