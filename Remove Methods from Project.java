class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }

        boolean[] suspicious = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        suspicious[k] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    q.offer(v);
                }
            }
        }

        boolean canRemove = true;
        for (int u = 0; u < n; u++) {
            if (!suspicious[u]) {
                for (int v : graph[u]) {
                    if (suspicious[v]) {
                        canRemove = false;
                        break;
                    }
                }
            }
            if (!canRemove) break;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (canRemove && suspicious[i]) continue;
            result.add(i);
        }
        return result;
    }
}
