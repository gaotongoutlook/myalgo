package com.nowcoder;

/**
 * 0-1背包相关问题
 * 题目列表及其解决方式方法
 * 总结如何解决问题
 */
public class MyTest01 {

    /**
     * 普通0-1背包
     * 背包可装物品总重量的最大值是多少
     */
    public int knapsack1(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        // 第0个物品不选择
        dp[0][0] = true;
        if(weight[0]<=w) {
            // 第0个物品选择
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || j-weight[i]>=0 && dp[i-1][j-weight[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 普通0-1背包
     * 是否能装满整个背包
     */
    public boolean knapsack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        // 第0个物品不选择
        dp[0][0] = true;
        if(weight[0]<=w) {
            // 第0个物品选择
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || j-weight[i]>=0 && dp[i-1][j-weight[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 普通0-1背包
     * 正好装满背包最少需要多少物品
     */
    public int knapsack3(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 第0个物品不选择
        dp[0][0] = 0;
        if(weight[0]<=w) {
            // 第0个物品选择
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(j-weight[i]<0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-weight[i]]+1);
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 普通0-1背包
     * 装满背包有多少种装法
     */
    public int knapsack4(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        dp[0][0] = 0;
        if(weight[0]<=w) {
            // 第0个物品选择
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-weight[i]>=0) {
                    dp[i][j] += dp[i-1][j-weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 分割等和子集
     * https://leetcode.cn/problems/partition-equal-subset-sum/description/
     */
    public boolean canPartition(int[] nums) {

        return false;
    }

    /**
     * 最后一块石头的重量 II
     * https://leetcode.cn/problems/last-stone-weight-ii/
     */
    public int lastStoneWeightII(int[] stones) {
        return -1;
    }

    /**
     * 目标和
     * https://leetcode.cn/problems/target-sum/
     */
    public int findTargetSumWays(int[] nums, int target) {
        return -1;
    }

    /**
     * 一和零
     * https://leetcode.cn/problems/ones-and-zeroes/description/
     */
    public int findMaxForm(String[] strs, int m, int n) {
        return -1;
    }

    /**
     * 盈利计划 困难
     * https://leetcode.cn/problems/profitable-schemes/
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        return -1;
    }

    /**
     * 零钱兑换
     * https://leetcode.cn/problems/coin-change/description/
     */
    public int coinChange(int[] coins, int amount) {
        return -1;
    }

    /**
     * 零钱兑换 II
     * https://leetcode.cn/problems/coin-change-ii/
     */
    public int change(int amount, int[] coins) {
        return -1;
    }

    /**
     * 最大平均值和的分组
     * https://leetcode.cn/problems/largest-sum-of-averages/description/
     */
    public double largestSumOfAverages(int[] nums, int k) {
        return -1;
    }

    /**
     * 最佳买卖股票时机含冷冻期
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     */
    public int maxProfit(int[] prices) {
        return -1;
    }

    /**
     * 组合总和 IV
     * https://leetcode.cn/problems/D0F0SV/
     */
    public int combinationSum4(int[] nums, int target) {
        return -1;
    }

}
