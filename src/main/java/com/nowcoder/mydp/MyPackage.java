package com.nowcoder.mydp;

/**
 * 背包 0-1背包 完全背包 多重背包 二维背包
 * 可装物品总重量的最大值
 * 是否能装满整个背包
 * 正好装满需要最少多少物品
 * 装满背包有多少种装法
 */
public class MyPackage {

    /**
     * 0-1背包
     * 可装物品总重量的最大值
     */
    public int knapsackOne(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || dp[i-1][j-weight[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        for(int j=w-1; j>=0; j++) {
            if(dp[n-1][j]) {
                return j;
            }
        }

        return -1;
    }

    /**
     * 0-1背包
     * 是否能装满整个背包
     */
    public boolean knapsackTwo(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] || dp[i-1][j-weight[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 0-1背包
     * 正好装满需要最少多少物品
     */
    public int knapsackThree(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int i=0; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                if(j-weight[i]<0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-weight[i]]+1);
                }
            }
        }

        if(dp[n-1][w]!=Integer.MAX_VALUE) {
            return dp[n-1][w];
        }

        return -1;
    }

    /**
     * 0-1背包
     * 装满背包有多少种装法
     */
    public int knapsackFour(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        dp[0][0] = 1;
        if(weight[0]<=w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(j-weight[i]<0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]];
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 完全背包
     * 可装物品总重量的最大值
     */
    public int wanquanOne(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        for(int k=0; k*weight[0]<=w; k++) {
            dp[0][k*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j-k*weight[i]];
                    }
                }
            }
        }

        for(int i=w; i>=0; i++) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 完全背包
     * 是否能装满整个背包
     */
    public boolean wanquanTwo(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        for(int k=0; k*weight[0]<=w; k++) {
            dp[0][k*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j-k*weight[i]];
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 完全背包
     * 正好装满需要最少多少物品
     */
    public int wanquanThree(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int k=0; k*weight[0]<=w; k++) {
            dp[0][k*weight[0]] = k;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-k*weight[i]]+k);
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 完全背包
     * 装满背包有多少种装法
     */
    public int wanquanFour(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int k=0; k*weight[0]<=w; k++) {
            dp[0][k*weight[0]] = k;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j] + dp[i-1][j-k*weight[i]];
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 多重背包
     * 可装物品总重量的最大值
     */
    public int duochongOne(int[] weight, int[] count, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        for(int i=0; i<=count[0] && i*weight[i]<=w; i++) {
            dp[0][i*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                for (int k = 0; k * weight[i] <= w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j-k*weight[i]];
                    }
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
     * 多重背包
     * 是否能装满整个背包
     */
    public boolean duochongTwo(int[] weight, int[] count, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];

        for(int i=0; i<=count[0] && i*weight[i]<=w; i++) {
            dp[0][i*weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for (int j = 0; j <= w; j++) {
                for (int k = 0; k * weight[i] <= w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j-k*weight[i]];
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 多重背包
     * 正好装满需要最少多少物品
     */
    public int duochongThree(int[] weight, int[] count, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int i=0; i<count[0] && i*weight[0]<=w; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k<count[i] && k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j], dp[j-k*weight[i]][j]+k);
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 多重背包
     * 装满背包有多少种装法
     */
    public int duochongFour(int[] weight, int[] count, int n, int w) {
        int[][] dp = new int[n][w+1];

        for(int i=0; i<count[0] && i*weight[0]<=w; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                for(int k=0; k<count[i] && k*weight[i]<=w; k++) {
                    if(j-k*weight[i]<0) {
                        dp[i][j] = dp[i-1][j];
                    }else {
                        dp[i][j] = dp[i-1][j] + dp[j-k*weight[i]][j];
                    }
                }
            }
        }

        return dp[n-1][w];
    }

    /**
     * 二维背包
     * 装满背包的最大价值
     */
    public int knapsack2(int[] weight, int[] value, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0]<=w) {
            dp[0][weight[0]] = value[0];
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                }
                if(j-weight[i]>=0 && dp[i-1][j-weight[i]]!=Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-weight[i]]+value[i]);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for(int j=0; j<=w; j++) {
            if(res<dp[n-1][j]) {
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
        if(sum%2==1) {
            return false;
        }
        sum/=2;

        boolean[][] dp = new boolean[n][sum+1];
        dp[0][0] = true;
        if(nums[0]<=sum) {
            dp[0][nums[0]] = true;
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<=sum; j++) {
                if(j-nums[i]>=0) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n-1][sum];
    }

    /**
     * 目标和
     */
    public int findTargetSubWays(int[] nums, int S) {
        if(S>1000 || S<-1000) {
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
                if(j-nums[i]>=0 && j-nums[i]<=m) {
                    dp[i][j] = dp[i-1][j-nums[i]];
                }
                if(j+nums[i]>=0 && j+nums[i]<=m) {
                    dp[i][j] += dp[i-1][j+nums[i]];
                }
            }
        }

        return dp[n-1][S+1000];
    }


}
