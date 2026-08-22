class Solution {
    public boolean checkDivisibility(int n) {
        int originalN = n;
        int digitSum = 0;
        int digitProduct = 1;
        
        // Extract and process digits
        while (n > 0) {
            int digit = n % 10;
            digitSum += digit;
            digitProduct *= digit;
            n /= 10;
        }
        
        int combinedSum = digitSum + digitProduct;
        
        // Prevent division by zero and check divisibility
        if (combinedSum == 0) {
            return false; 
        }
        
        return originalN % combinedSum == 0;
    }
}
