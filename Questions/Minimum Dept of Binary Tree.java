class Solution {
    public int minDepth(TreeNode root) {
        // Base case: If the tree is empty
        if (root == null) return 0;
        
        // If left child is null, recurse on right child
        if (root.left == null) return minDepth(root.right) + 1;
        
        // If right child is null, recurse on left child
        if (root.right == null) return minDepth(root.left) + 1;
        
        // If both children exist, take the minimum of the two paths
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
