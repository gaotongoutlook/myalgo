package com.nowcoder;

import com.sun.deploy.panel.ITreeNode;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.example.pojo.TreeNode;
import org.example.utils.PrintUtils;
import sun.swing.PrintColorUIResource;

import javax.lang.model.type.ErrorType;
import java.lang.reflect.Array;
import java.util.*;

public class MyDemo01 {

    public int climbStairs1(int n, int[] costs) {
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return costs[1] + 1;
        }
        if(n==2) {
            return Math.min(costs[2]+4, costs[1]+1+costs[2]+1);
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = costs[1] + 1;
        dp[2] = Math.min(costs[2]+4, dp[1]+costs[2]+1);
        for(int i=3; i<=n; i++) {
            dp[i] = Math.min(dp[i-3]+costs[i]+9, Math.min(dp[i-2]+costs[i]+4, dp[i-1]+costs[i-1]+1));
        }

        return dp[n+1];
    }

    public int climbStairs(int n, int[] costs) {
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return costs[0] + 1;
        }
        if(n==2) {
            return Math.min(costs[1]+4, costs[0]+1+costs[1]+1);
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = costs[0] + 1;
        dp[2] = Math.min(costs[1]+4, costs[0]+1+costs[1]+1);
        for(int i=3; i<=n; i++) {
            dp[i] = Math.min(dp[i-3]+costs[i-1]+9, Math.min(dp[i-2]+costs[i-1]+4, dp[i-1]+costs[i-1]+1));
        }

        return dp[n];
    }

    /**
     * 爬楼梯最小花费
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if(n<=1) {
            return 0;
        }
        if(n==2) {
            return Math.min(cost[1], cost[0]);
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.min(cost[1], cost[0]);
        for(int i=3; i<=n; i++) {
            dp[i] = Math.min(cost[i-2]+dp[i-2], cost[i-1]+dp[i-1]);
        }

        return dp[n];
    }


    public static void main1(String[] args) {
        /*int n = 4;
        int[] costs = new int[]{1,2,3,4};
        int result = new MyDemo01().climbStairs(n, costs);
        System.out.println(result);*/

        /*int[][] grid = new int[][]{{1,2,3},{4,5,6}};
        int result = new MyDemo01().minPathSum(grid);
        System.out.println(result);*/

        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(2);
        triangle.add(new ArrayList<>(temp));
        temp.clear();
        temp.add(3);
        temp.add(4);
        triangle.add(new ArrayList<>(temp));
        temp.clear();
        temp.add(6);
        temp.add(5);
        temp.add(7);
        triangle.add(new ArrayList<>(temp));
        temp.clear();
        temp.add(4);
        temp.add(1);
        temp.add(8);
        temp.add(3);
        triangle.add(new ArrayList<>(temp));

        int result = new MyDemo01().minimumTotal(triangle);
        //System.out.println(result);
    }

    /**
     * 最小路径和
     */
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int m = grid.length; // 行
        int n = grid[0].length; // 列
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=1; i<m; i++) {
            int len = dp[i-1][0];
            dp[i][0] = grid[i][0] + len;
        }
        for(int j=1; j<n; j++) {
            int len = grid[0][j-1];
            dp[0][j] = grid[0][j] + len;
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }


    /**
     * 三角形的最小路径和
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        int[][] dp = new int[height][height];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1; i<height; i++) {
            for(int j=0; j<triangle.get(i).size(); j++) {
                int len = triangle.get(i).get(j);
                if(j==0) {
                    dp[i][j] = dp[i-1][j] + len;
                }else if(j==triangle.get(i).size()-1) {
                    dp[i][j] = dp[i-1][j-1] + len;
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + len;
                }
            }
        }
        int result = dp[height-1][0];
        System.out.println(dp[height-1][0]);
        for(int i=1; i<height; i++) {
            System.out.println(dp[height-1][i]);
            result = Math.min(result, dp[height-1][i]);
        }
        return result;
    }

    /**
     * 不同路径
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        for(int i=1; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int j=1; j<n; j++) {
            dp[0][j] = 1;
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * 不同路径II
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        for(int i=1; i<m; i++) {
            if(obstacleGrid[i][0]==1) {
                dp[i][0] = 0;
            }else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int j=1; j<n; j++) {
            if(obstacleGrid[0][j]==1) {
                dp[0][j] = 0;
            }else {
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j]==1) {
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * 股票买卖只进行一次交易
     */
    public int maxProfit1(int[] prices) {
        if(prices.length==1) {
            return 0;
        }

        int n = prices.length;

        // 找左侧最小值 找到最佳买入时机
        int[] leftArr = new int[n];
        leftArr[0] = prices[0];
        for(int i=1; i<n; i++) {
            if(prices[i] < leftArr[i-1]) {
                leftArr[i] = prices[i];
            }else {
                leftArr[i] = leftArr[i-1];
            }
        }

        // 找右侧最大值 找到最佳卖出时机
        int[] rightArr = new int[n];
        rightArr[n-1] = prices[n-1];
        for(int i=n-2; i>=0; i--) {
            if(prices[i] > prices[i+1]) {
                rightArr[i] = prices[i];
            }else {
                prices[i] = prices[i+1];
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int diff = rightArr[i] - leftArr[i];
            if(result < diff) {
                result = diff;
            }
        }

        return result;
    }

    public int maxProfitOnePass(int[] prices) {
        return 0;
    }

    /**
     * 股票买卖可以进行两次操作
     */
    public int maxProfit2(int[] prices) {
        if(prices.length==1) {
            return 0;
        }
        int n = prices.length;
        // 0 第i天后持有股票，手中利润的最大值 继续持有或者买入
        // 1 第i天后不持有股票，手中利润的最大值 继续不操作或者将当前的卖出
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i=1; i<n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], prices[i]+dp[i-1][0]);
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    /**
     * 单词拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++) {
            for(String word: wordDict) {
                int len = word.length();
                int startup = i-len;
                if(i>=len && s.startsWith(word, startup) && dp[startup]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.print("[");
        for(int i=0; i<=n; i++) {
            System.out.print(" "+dp[i]+" ");
        }
        System.out.println("]");

        return dp[n];
    }

    /**
     * 将数字转换为字母串
     */
    public int crackNumber(int ciphertext) {
        char[] cs = String.valueOf(ciphertext).toCharArray();
        int n = cs.length;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = dp[0];
        if(isValidNum(cs[0], cs[1])) {
            dp[1] += 1;
        }
        for(int i=2; i<n; i++) {
            dp[i] = dp[i-1];
            if(isValidNum(cs[i-1], cs[i])) {
                dp[i] += dp[i-2]; // 为什么是 i-2
            }
        }

        return dp[n-1];
    }

    private boolean isValidNum(char a, char b) {
        int left = a - '0';
        int right = b - '0';
        if(left==0) {
            return false;
        }
        int num = left*10+right;
        return num>=10 && num<=25;
    }

    /**
     * 砍竹子
     */
    public int cuttingBamboo(int bamboo_len) {
        if(bamboo_len==1 || bamboo_len==2) {
            return 1;
        }
        if(bamboo_len==3) {
            return 2;
        }
        int[] dp = new int[bamboo_len+1];
        dp[0] = 1;
        for(int i=1; i<=bamboo_len; i++) {
            for(int j=1; j<=i; j++) {
                if(dp[i] < j*dp[i-j]) {
                    dp[i] = j*dp[i-j];
                }
            }
        }
        return dp[bamboo_len]%1000000007;
    }

    /**
     * 粉刷房子
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        int m = costs[0].length;
        int[] dp = new int[n];
        int[] selected = new int[n];
        int index = 0;
        dp[0] = costs[0][0];
        for(int i=1; i<m; i++) {

        }


        dp[0] = Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));


        return dp[n-1];
    }

    public List<String> generateParenthesis(int n) {
        return null;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        boolean reverse = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for(int i=0; i<levelSize; i++) {
                TreeNode node = queue.poll();

                if(reverse) {
                    currentLevel.add(0, node.val);
                }else {
                    currentLevel.add(node.val);
                }

                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            reverse = !reverse;
            result.add(currentLevel);
        }

        return result;
    }

    /**
     * 分割等和子集
     */
    public boolean canPartition(int[] nums) {
        if(nums==null || nums.length<=1) {
            return false;
        }

        int sum = 0;
        int n = nums.length;
        for(int i=0; i<n; i++) {
            sum += nums[i];
        }
        if(sum%2==1) {
            return false;
        }
        int target = sum/2;
        Arrays.sort(nums);

        boolean[][] dp = new boolean[n][target+1];

        dp[0][0] = true;
        if(nums[0]<=target) {
            dp[0][nums[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=target; j++) {
                if(dp[i-1][j]) {
                    dp[i][j] = true;
                }
                if(j-nums[i]>=0 && dp[i-1][j-nums[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][target];
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
     * 分糖果 前后缀统计
     */
    public int candy (int[] arr) {
        return -1;
    }

    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left)+1;
        int rightDepth = maxDepth(root.right)+1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 兑换零钱
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int n = coins.length;

        int[] dp = new int[amount+1];
        for(int i=0; i<=amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i=1; i<=amount; i++) { // 容量
             for(int j=0; j<n; j++) { // 可选列表
                 if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE && dp[i-coins[j]]+1<dp[i]) {
                     dp[i] = dp[i-coins[j]]+1;
                 }
             }
        }

        if(dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        // 打印结果数组
        PrintUtils.printArr(dp);

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        new MyDemo01().coinChange(coins, amount);
    }

    /**
     *  兑换零钱的方式有几种
     */
    public int coinChangeTotal(int amount, int[] coins) {
        Arrays.sort(coins);
        int n = coins.length;

        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin : coins) {
            for(int i=coin; i<=amount; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }


}
