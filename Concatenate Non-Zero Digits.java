class Solution {
    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        long sum = 0;

        for (char c : s.toCharArray()) {
            if (c != '0') {
                sb.append(c);
                sum += (c - '0');
            }
        }

        // If there are no non-zero digits, x = 0
        if (sb.length() == 0) {
            return 0;
        }

        long x = Long.parseLong(sb.toString());
        return x * sum;
    }
}
