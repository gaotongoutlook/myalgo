package org.example.mydp;

import java.util.List;

/**
 * 路径模型
 * 给到一个二维矩阵，从某个点以某种走法（只能向下或向右）到达另一个点
 * 1.总共有多少种走法 技术问题
 * 2.最长/最短路径为多少 最值问题
 * 步骤
 * 1.构建多阶段决策模型（确定多少个阶段）
 * 2.定义状态（存在哪些状态）
 * 3.定义状态转移方程（怎么通过之前状态转移过来）
 */
public class PathModel {

    /**
     * 最小路径和
     * 从左上角到右下角，使得路径上的数字总和为最小
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // 处理横竖边界
        int len = 0;
        for (int i = 0; i < m; i++) {
            len += grid[i][0];
            dp[i][0] = len;
        }

        len = 0;
        for (int i = 1; i < n; i++) {
            len += grid[0][i];
            dp[0][i] = len;
        }

        // 正常转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 不同路径
     * 到达某一点有多少种到达方法
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 两种路径到达此点的和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 不同路径
     * 到达某一点有多少种到达方法 中间存在障碍物
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        if(obstacleGrid[0][0] == 1) { // 有障碍，没有走法
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }

        for(int j=1; j<n; j++) {
            if(obstacleGrid[0][j] == 1) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int i=1; i<m; i++) {
            if(obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * 礼物的最大价值
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // 处理横竖边界
        int len = 0;
        for (int i = 0; i < m; i++) {
            len += grid[i][0];
            dp[i][0] = len;
        }

        len = 0;
        for (int i = 1; i < n; i++) {
            len += grid[0][i];
            dp[0][i] = len;
        }

        // 正常转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1; i<n; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for(int j=1; j<i; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]+triangle.get(i).get(j));
            }
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        int res = Integer.MAX_VALUE;
        for(int j=0; j<n; j++) {
            if(dp[n-1][j] < res) {
                res = dp[n-1][j];
            }
        }

        return res;
    }

}
