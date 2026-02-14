package com.nowcoder;

import org.example.utils.PrintUtils;

public class MyTest02 {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            // 统计当前字符串中 0 和 1 的个数
            int zeros = 0, ones = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }
            // 逆序更新 dp 数组
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        PrintUtils.printArr(dp);

        return dp[m][n];
    }

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        // 前缀和数组，prefix[i] 表示前 i 个元素的和
        double[] prefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // dp[i][t] 表示前 i 个元素分成 t 组的最大平均值和
        double[][] dp = new double[n + 1][k + 1];

        // 初始化：分成一组的情况
        for (int i = 1; i <= n; i++) {
            dp[i][1] = (prefix[i] - prefix[0]) / i;
        }

        // 动态规划，从分成2组到分成k组
        for (int t = 2; t <= k; t++) {
            for (int i = t; i <= n; i++) { // 前i个元素，至少需要t个元素
                for (int j = t - 1; j < i; j++) { // 最后一组从j到i-1
                    double avg = (prefix[i] - prefix[j]) / (i - j);
                    dp[i][t] = Math.max(dp[i][t], dp[j][t - 1] + avg);
                }
            }
        }

        // 取所有分组数（1到k）的最大值
        double ans = 0;
        for (int t = 1; t <= k; t++) {
            ans = Math.max(ans, dp[n][t]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int result = new MyTest02().findMaxForm(strs, m, n);
        System.out.println("--------------");
        System.out.println(result);
    }

}
