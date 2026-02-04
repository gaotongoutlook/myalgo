package com.nowcoder.mydp;

/**
 * 路径问题
 */
public class MyPath {

    /**
     * 最小路径和
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int len = 0;
        for(int i=0; i<m; i++) {
            len += grid[i][0];
            dp[i][0] = len;
        }
        len = 0;
        for(int j=0; j<n; j++) {
            len += grid[0][j];
            dp[0][j] = len;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 不同路径
     * 无障碍总共有多少条道路
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


}
