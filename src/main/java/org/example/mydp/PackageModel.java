package org.example.mydp;

import sun.plugin.services.WIExplorerBrowserService;
import sun.security.action.PutAllAction;

import javax.smartcardio.TerminalFactory;
import java.time.temporal.ValueRange;

/**
 * 背包模型
 * 0-1背包 完全背包 多重背包 二维费用背包
 */
public class PackageModel {

    /**
     * 1、背包可装物品总重量的最大值是多少
     * 0-1背包
     */
    public int knapsack1(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        // 状态转移方程
        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 2、是否能装满整个背包
     * 0-1背包
     */
    public boolean knapsack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        // 状态转移方程
        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 3、正好装满背包最少需要多少物品
     * 0-1背包
     */
    public int knapsack3(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        if (weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        // 状态转移方程
        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-weight[i]]+1);
                }
            }
        }

        if(dp[n-1][w] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[n-1][w];
    }

    /**
     * 4、装满背包有多少种装法
     * 0-1背包
     */
    public int knapsack4(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        dp[0][0] = 1;
        if (weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        // 状态转移方程
        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 1、背包可装物品总重量的最大值是多少
     * 完全背包
     */
    public int wanquanknapsack1(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        for(int c=0; c<=w/weight[0]; c++) {
            dp[0][c*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                int k = j / weight[i];
                for(int c=0; c<=k; c++) {
                    if(dp[i-1][j-c*weight[i]]) {
                        dp[i][j] = true;
                        break; // 为什么添加这个呢
                    }
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 2、是否能装满整个背包
     * 完全背包
     */
    public boolean wanquanknapsack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        for(int c=0; c<=w/weight[0]; c++) {
            dp[0][c*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                int k = j / weight[i];
                for(int c=0; c<=k; c++) {
                    if(dp[i-1][j-c*weight[i]]) {
                        dp[i][j] = true;
                        break; // 为什么添加这个呢
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 3、正好装满背包最少需要多少物品
     * 完全背包
     */
    public int wanquanknapsack3(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int c=0; c<=w/weight[0]; c++) {
            dp[0][c*weight[0]] = c;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                int k = j / weight[i];
                for(int c=0; c<=k; c++) {
                    // 这块儿需要认真研究下
                    if(dp[i-1][j-c*weight[i]] != Integer.MAX_VALUE && (dp[i-1][j-c*weight[i]] + c < dp[i][j] )) {
                        dp[i][j] = dp[i-1][j-c*weight[i]] + c;
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 4、装满背包有多少种装法
     * 完全背包
     */
    public int wanquanknapsack4(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int c=0; c<=w/weight[0]; c++) {
            dp[0][c*weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                int k = j / weight[i];
                for(int c=0; c<=k; c++) {
                    dp[i][j] += dp[i-1][j-c*weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 4、装满背包有多少种装法
     * 多重背包问题
     */
    public int duochongknapsack4(int[] weight, int[] count, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int c=0; c<= Math.min(w/weight[0], count[0]); c++) {
            dp[0][c*weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                int k = Math.min(j/weight[i], count[i]);
                for(int c=0; c<=k; c++) {
                    dp[i][j] += dp[i-1][j-c*weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 1、背包可装物品总重量的最大值是多少 最大价值
     * 二维背包
     */
    public int knapsack1(int[] weight, int[] value, int n, int w) {
        int[][] dp = new int[n][w + 1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = value[0];
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                // 这儿为什么这么写
                if(dp[i-1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
                }
                if(j-weight[i]>=0 && dp[i-1][j-weight[i]] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weight[i]]+value[i]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int j=0; j<=w; j++) {
            if(res < dp[n-1][j]) {
                res = dp[n-1][j];
            }
        }

        return res;
    }

    /**
     * 分割等和子集
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += nums[i];
        }

        if(sum % 2 == 1) {
            return false;
        }
        sum /= 2;

        boolean[][] dp = new boolean[n][sum+1];

        dp[0][0] = true;
        if(nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=sum; j++) {
                if(dp[i-1][j] && (j-nums[i]>=0 && dp[i-1][j-nums[i]])) {
                    dp[i][j] = true;
                }

                // 另外一种表达式
                /*if(j-nums[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }*/
            }
        }

        return dp[n-1][sum];
    }

    /**
     * 目标和
     */
    public int findTargetSumWays(int[] nums, int S) {
        if(Math.abs(S) > 1000) {
            return 0;
        }

        int n = nums.length;
        int offset = 1000;
        int m = 2000;
        int[][] dp = new int[n][m+1];
        dp[0][offset-nums[0]] = 1;
        dp[0][offset+nums[0]] = 1;

        for(int i=1; i<n; i++) {
            for(int j=0; j<=m; j++) {
                if(j-nums[0]>=0 && j-nums[i]<=m) {
                    dp[i][j] = dp[i-1][j-nums[i]];
                }
                if(j+nums[0]>=0 && j+nums[i]<=m) {
                    dp[i][j] += dp[i-1][j+nums[i]];
                }
            }
        }

        return dp[n-1][S+1000];
    }

    /**
     * 零钱兑换
     * 类似完全背包问题
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<=amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int c=0; c<=amount/coins[0]; c++) {
            dp[0][c*coins[0]] = c;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=amount; j++) {
                int k = j/coins[i];
                for(int c=0; c<=k; c++) {
                    if(dp[i-1][j-c*coins[i]] != Integer.MAX_VALUE && (dp[i-1][j-c*coins[i]]+c < dp[i][j])) {
                        dp[i][j] = dp[i-1][j-coins[i]] + c;
                    }
                }
            }
        }

        if(dp[n-1][amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[n-1][amount];
    }

    /**
     * 零钱兑换2 组合总数
     * 类似完全背包问题数量
     */
    public int coinChangeCount(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        for(int c=0; c<=amount/coins[0]; c++) {
            dp[0][c*coins[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for (int j = 0; j <= amount; j++) {
                int k = j / coins[i];
                for (int c = 0; c <= k; c++) {
                    dp[i][j] += dp[i-1][j-c*coins[i]];
                }
            }
        }

        return dp[n-1][amount];
    }

}
