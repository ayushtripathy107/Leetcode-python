/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // A critical point requires a previous, current, and next node.
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        
        int firstCP = -1;
        int prevCP = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // 0-indexed position for tracking node distances

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check if current node is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > nextNode.val;
            boolean isMinima = curr.val < prev.val && curr.val < nextNode.val;
            
            if (isMaxima || isMinima) {
                if (firstCP == -1) {
                    // First critical point encountered
                    firstCP = index;
                } else {
                    // Consecutive critical point found: update minDistance
                    minDistance = Math.min(minDistance, index - prevCP);
                }
                // Update the most recent critical point tracker
                prevCP = index;
            }
            
            // Move pointers forward
            prev = curr;
            curr = nextNode;
            index++;
        }

        // If fewer than two critical points were found
        if (firstCP == prevCP) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCP - firstCP;
        return new int[]{minDistance, maxDistance};
    }
}
