class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Check if t can be factored using only single-digit prime factors (2, 3, 5, 7)
        long temp = t;
        for (int i = 2; i <= 9; i++) {
            while (temp % i == 0) {
                temp /= i;
            }
        }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        long[] rem = new long[n + 1];
        rem[0] = t;

        // Find position of the first '0' if present
        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                firstZero = i;
                break;
            }
            rem[i + 1] = rem[i] / gcd(rem[i], num.charAt(i) - '0');
        }

        // If no zeros in num and it's already divisible by t, return num
        if (firstZero == -1 && rem[n] == 1) {
            return num;
        }

        // Maximum position we can start modifying num
        int startPos = (firstZero == -1) ? n - 1 : firstZero;
        char[] arr = num.toCharArray();

        // Step 2: Try to pivot at index i from right to left
        for (int i = startPos; i >= 0; i--) {
            int currentDigit = arr[i] - '0';

            for (int d = currentDigit + 1; d <= 9; d++) {
                long nextRem = rem[i] / gcd(rem[i], d);
                int spaceLeft = n - 1 - i;

                if (minDigitsNeeded(nextRem) <= spaceLeft) {
                    arr[i] = (char) ('0' + d);
                    // Fill remaining suffix greedily (smallest digit from '1' to '9')
                    fillSuffix(arr, i + 1, n - 1, nextRem);
                    return new String(arr);
                }
            }
        }

        // Step 3: If no solution of length n exists, expand to length n + 1 (or min needed)
        int minLen = Math.max(n + 1, minDigitsNeeded(t));
        char[] result = new char[minLen];
        fillSuffix(result, 0, minLen - 1, t);
        return new String(result);
    }

    // Helper to greedily fill array range [start, end] to satisfy required product factor target
    private void fillSuffix(char[] arr, int start, int end, long target) {
        for (int j = start; j <= end; j++) {
            int spaceLeft = end - j;
            for (int d = 1; d <= 9; d++) {
                long nextTarget = target / gcd(target, d);
                if (minDigitsNeeded(nextTarget) <= spaceLeft) {
                    arr[j] = (char) ('0' + d);
                    target = nextTarget;
                    break;
                }
            }
        }
    }

    // Returns the minimum number of single digits (2..9) needed to multiply to a product divisible by target
    private int minDigitsNeeded(long target) {
        if (target <= 1) return 0;

        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (target % 2 == 0) { c2++; target /= 2; }
        while (target % 3 == 0) { c3++; target /= 3; }
        while (target % 5 == 0) { c5++; target /= 5; }
        while (target % 7 == 0) { c7++; target /= 7; }

        int count = c7 + c5 + (c3 / 2) + (c2 / 3);
        int r3 = c3 % 2;
        int r2 = c2 % 3;

        if (r3 == 1 && r2 == 1) {
            count += 1; // 3 * 2 = 6 (1 digit)
        } else if (r3 == 1 && r2 == 2) {
            count += 2; // 3 * 2 * 2 -> e.g. 6 and 2 (2 digits)
        } else if (r3 == 1 && r2 == 0) {
            count += 1; // 3 (1 digit)
        } else if (r3 == 0 && r2 == 1) {
            count += 1; // 2 (1 digit)
        } else if (r3 == 0 && r2 == 2) {
            count += 1; // 2 * 2 = 4 (1 digit)
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
