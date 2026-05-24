class Solution(object):
    def convert(self, s, numRows):
        # If only one row or string is too short for zigzag, return as is
        if numRows == 1 or numRows >= len(s):
            return s
        
        # Create a list of strings for each row
        rows = [''] * numRows
        cur_row = 0
        going_down = False
        
        for char in s:
            rows[cur_row] += char
            
            # Flip direction when we reach the top or bottom row
            if cur_row == 0 or cur_row == numRows - 1:
                going_down = not going_down
            
            # Move to the next row based on direction
            cur_row += 1 if going_down else -1
            
        # Combine all rows into the final result
        return "".join(rows)
