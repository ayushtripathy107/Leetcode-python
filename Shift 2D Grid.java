import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Handle case where k is larger than the total number of elements
        k = k % totalElements;
        
        // Initialize the result list with empty rows
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>());
        }
        
        // Temporary 2D array to easily set values at specific indexes
        int[][] temp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate the new position after shifting by k
                int new1DIndex = (i * n + j + k) % totalElements;
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                
                temp[newRow][newCol] = grid[i][j];
            }
        }
        
        // Populate the final List<List<Integer>> structure
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result.get(i).add(temp[i][j]);
            }
        }
        
        return result;
    }
}
