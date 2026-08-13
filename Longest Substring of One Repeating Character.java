class Solution {
    class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        int size;

        Node(int size) {
            this.size = size;
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
        }
    }

    private Node[] tree;
    private char[] chars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        this.chars = s.toCharArray();
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            
            chars[idx] = ch;
            update(1, 0, n - 1, idx);
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        
        tree[node] = new Node(end - start + 1);
        merge(node, start, mid, end);
    }

    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            // Leaf node characteristics stay 1 even when character updates
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx);
        } else {
            update(2 * node + 1, mid + 1, end, idx);
        }
        merge(node, start, mid, end);
    }

    private void merge(int node, int start, int mid, int end) {
        Node left = tree[2 * node];
        Node right = tree[2 * node + 1];
        Node parent = tree[node];

        parent.prefLen = left.prefLen;
        parent.suffLen = right.suffLen;
        parent.maxLen = Math.max(left.maxLen, right.maxLen);

        // Check if mid and mid + 1 characters match to bridge the two intervals
        if (chars[mid] == chars[mid + 1]) {
            parent.maxLen = Math.max(parent.maxLen, left.suffLen + right.prefLen);
            
            if (left.prefLen == left.size) {
                parent.prefLen = left.size + right.prefLen;
            }
            if (right.suffLen == right.size) {
                parent.suffLen = right.size + left.suffLen;
            }
        }
    }
}
