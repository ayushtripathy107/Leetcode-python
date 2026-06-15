class Solution(object):
    def deleteMiddle(self, head):
        # Edge case: if there is only one node, the middle is that node.
        if not head or not head.next:
            return None
        
        # Initialize pointers
        # slow will stop at the node BEFORE the middle node
        slow = head
        fast = head.next.next
        
        # Move fast two steps and slow one step
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            
        # Delete the middle node by skipping it
        slow.next = slow.next.next
        
        return head
