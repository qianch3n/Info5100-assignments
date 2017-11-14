import java.util.Arrays;

public class Extra {
    public static boolean findPartition (int arr[]) {
        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = arr.length;
        boolean[] dp = new boolean[sum+1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : arr) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i-num];
                }
            }
        }

        return dp[sum];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 11, 5};
        int[] arr2 = {1, 2, 3, 5};
        System.out.println(findPartition(arr1));
        System.out.println(findPartition(arr2));
    }
}
