import java.util.*;

class EqualSumPartition {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(equalSumPartition(n, arr));
        sc.close();
    }

    static int equalSumPartition(int n, int[] arr) {
        int sum = Arrays.stream(arr).sum();

        if (sum % 2 != 0) {
            return 0;
        }

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }

        return dp[target] ? 1 : 0;
    }
}
