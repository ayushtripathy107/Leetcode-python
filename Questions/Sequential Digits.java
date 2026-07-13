import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // Iterate through all possible window lengths (from 2 to 9)
        for (int length = 2; length <= 9; length++) {
            // Slide the window across the '123456789' string
            for (int i = 0; i <= 9 - length; i++) {
                String sub = digits.substring(i, i + length);
                int num = Integer.parseInt(sub);
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        // result is already sorted because we iterate by length then by starting digit
        return result;
    }
}
