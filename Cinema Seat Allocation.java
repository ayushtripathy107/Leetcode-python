import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store row -> bitmask of reserved seats
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about columns 2 to 9
            if (col >= 2 && col <= 9) {
                // Set the (col - 2)-th bit to 1 representing reserved
                rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }
        
        // Start with the maximum possible families assuming all rows are empty
        int maxFamilies = 2 * n;
        
        // Bitmasks representing patterns that block family placement
        // Columns: 2, 3, 4, 5 -> positions 0, 1, 2, 3 -> mask = 15 (01111)
        int leftMask = 0b00001111; 
        // Columns: 6, 7, 8, 9 -> positions 4, 5, 6, 7 -> mask = 240 (11110000)
        int rightMask = 0b11110000;
        // Columns: 4, 5, 6, 7 -> positions 2, 3, 4, 5 -> mask = 60 (00111100)
        int middleMask = 0b00111100;
        
        // Deduct families for rows that have reservations
        for (int mask : rowToSeats.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            
            if (leftFree && rightFree) {
                // Both sides are completely free, row holds 2 families (no deduction)
                continue;
            } else if (leftFree || rightFree || (mask & middleMask) == 0) {
                // Either left is free, right is free, or middle is free. 
                // Row can only hold 1 family instead of 2. Deduct 1.
                maxFamilies -= 1;
            } else {
                // No combination can accommodate a family. Deduct 2.
                maxFamilies -= 2;
            }
        }
        
        return maxFamilies;
    }
}
