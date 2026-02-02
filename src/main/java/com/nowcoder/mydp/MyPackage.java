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

        return -1;
    }



}
