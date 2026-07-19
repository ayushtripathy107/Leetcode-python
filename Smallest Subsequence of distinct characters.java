import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {
        // Track the last seen index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Track if a character is already included in our result stack
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If the character is already in the stack, skip it
            if (seen[c - 'a']) {
                continue;
            }
            
            // Pop characters from the stack if they are larger than the current character
            // AND they appear later in the string
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            // Add the current character to the stack and mark it as seen
            stack.push(c);
            seen[c - 'a'] = true;
        }
        
        // Build the final string from the stack characters
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
