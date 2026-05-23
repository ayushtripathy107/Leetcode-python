# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution(object):
    def isSymmetric(self, root):
        """
        :type root: Optional[TreeNode]
        :rtype: bool
        """
        if not root:
            return True
        return self.isMirror(root.left, root.right)

    def isMirror(self, t1, t2):
        # Both nodes are null
        if not t1 and not t2:
            return True
        # Only one node is null or values don't match
        if not t1 or not t2 or t1.val != t2.val:
            return False
        
        # Check if outer children and inner children are mirrors
        return self.isMirror(t1.left, t2.right) and self.isMirror(t1.right, t2.left)
