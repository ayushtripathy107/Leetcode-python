import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Step 1: Optimize by removing coins that are multiples of other coins
        Arrays.sort(coins);
        int uniqueCount = 0;
        int[] filteredCoins = new int[coins.length];
        for (int coin : coins) {
            boolean keep = true;
            for (int i = 0; i < uniqueCount; i++) {
                if (coin % filteredCoins[i] == 0) {
                    keep = false;
                    break;
                }
            }
            if (keep) {
                filteredCoins[uniqueCount++] = coin;
            }
        }
        int[] cleanCoins = Arrays.copyOf(filteredCoins, uniqueCount);

        // Step 2: Set up Binary Search range
        long low = 1;
        long high = (long) cleanCoins[0] * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMultiples(mid, cleanCoins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Helper to count valid multiples <= mid using PIE via Bitmasking
    private long countMultiples(long target, int[] coins) {
        long totalCount = 0;
        int n = coins.length;
        int totalSubsets = 1 << n; // 2^n subsets

        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            long multiples = target / currentLcm;
            if (bitCount % 2 == 1) {
                totalCount += multiples; // Odd subset size: Add
            } else {
                totalCount -= multiples; // Even subset size: Subtract
            }
        }
        return totalCount;
    }

    // GCD utility
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // LCM utility
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
