import java.util.*;

class SegmentTree {
    private int n;
    private int[] arr;
    private int[] seg;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.seg = new int[4 * n];
        if (n > 0) {
            build(1, 0, n - 1);
        }
    }

    private void build(int p, int l, int r) {
        if (l == r) {
            seg[p] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(p << 1, l, mid);
        build((p << 1) | 1, mid + 1, r);
        seg[p] = Math.max(seg[p << 1], seg[(p << 1) | 1]);
    }

    public int query(int L, int R) {
        if (L > R || n == 0) return 0;
        return query(1, 0, n - 1, L, R);
    }

    private int query(int p, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            return seg[p];
        }
        int mid = (l + r) >> 1;
        int res = 0;
        if (L <= mid) {
            res = Math.max(res, query(p << 1, l, mid, L, R));
        }
        if (R > mid) {
            res = Math.max(res, query((p << 1) | 1, mid + 1, r, L, R));
        }
        return res;
    }
}

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int cnt1 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') cnt1++;
        }

        // Identify zero blocks
        List<Integer> zeroBlocks = new ArrayList<>();
        List<Integer> blockLeftList = new ArrayList<>();
        List<Integer> blockRightList = new ArrayList<>();

        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') {
                    i++;
                }
                int end = i - 1;
                zeroBlocks.add(end - start + 1);
                blockLeftList.add(start);
                blockRightList.add(end);
            } else {
                i++;
            }
        }

        int m = zeroBlocks.size();
        List<Integer> ans = new ArrayList<>();

        // If there are less than 2 blocks of 0s, no trade can gain extra 1s
        if (m < 2) {
            for (int q = 0; q < queries.length; q++) {
                ans.add(cnt1);
            }
            return ans;
        }

        int[] blockLeft = new int[m];
        int[] blockRight = new int[m];
        int[] z = new int[m];
        for (int k = 0; k < m; k++) {
            blockLeft[k] = blockLeftList.get(k);
            blockRight[k] = blockRightList.get(k);
            z[k] = zeroBlocks.get(k);
        }

        // Array of adjacent block sums: tmpSum[k] = z[k] + z[k+1]
        int[] tmpSum = new int[m - 1];
        for (int k = 0; k < m - 1; k++) {
            tmpSum[k] = z[k] + z[k + 1];
        }

        SegmentTree segTree = new SegmentTree(tmpSum);

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            // Find first block ending >= l
            int firstIdx = binarySearchRight(blockRight, l);
            // Find last block starting <= r
            int lastIdx = binarySearchLeft(blockLeft, r);

            if (firstIdx > lastIdx || firstIdx >= m || lastIdx < 0) {
                ans.add(cnt1);
                continue;
            }

            int bestGain = 0;

            if (firstIdx == lastIdx) {
                // Only one block inside the range
                bestGain = 0;
            } else {
                // Effective lengths of boundary blocks inside [l, r]
                int firstLen = blockRight[firstIdx] - Math.max(blockLeft[firstIdx], l) + 1;
                int lastLen = Math.min(blockRight[lastIdx], r) - blockLeft[lastIdx] + 1;

                if (firstIdx + 1 == lastIdx) {
                    // Exactly two blocks in [l, r]
                    bestGain = firstLen + lastLen;
                } else {
                    // Case 1: First block + second block
