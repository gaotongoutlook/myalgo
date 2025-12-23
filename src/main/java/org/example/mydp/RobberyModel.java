package org.example.mydp;

import org.example.pojo.TreeNode;

/**
 * 打家劫舍
 * 一般动态规划问题，上一个阶段做了什么决策，不影响下一个阶段的决策
 * 打家劫舍这类问题，上一个阶段的决策会影响下一个阶段的决策，所以，每个阶段需要记录不同的决策对应的最值，而不是一个全局的最值
 */
public class RobberyModel {

    /**
     * 相邻的不能打劫
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        // dp[i][0]表示第i个物品没有选时的最大金额
        // dp[i][1]表示第i个物品选择时的最大金额
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for(int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    /**
     * 二叉树，相邻的不能打劫
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] money = postorder(root);
        return Math.max(money[0], money[1]);
    }

    private int[] postorder(TreeNode root) {
        if(root == null) {
            return new int[]{0, 0};
        }
        int[] leftMoney = postorder(root.left);
        int[] rightMoney = postorder(root.right);
        int[] money = new int[2];
        money[0] = Math.max(leftMoney[0], leftMoney[1]) + Math.max(rightMoney[0], rightMoney[1]);
        money[1] = (leftMoney[0] + rightMoney[0]) + root.val;
        return money;
    }

    public int robCircle(int[] nums) {
        int n = nums.length;
        if(n==1) {
            return nums[0];
        }
        if(n==2) {
            return Math.max(nums[0], nums[1]);
        }
        // 第0个不偷窃，偷窃1~n-1之间的房子
        int max1 = robDp(nums, 1, n-1);
        // 第0个偷窃，偷窃2~n-2之间的房子
        int max2 = robDp(nums, 2, n-2) + nums[0];
        return Math.max(max1, max2);
    }

    private int robDp(int[] nums, int p, int r) {
        int n = nums.length;
        // dp[i][0]表示第i个物品没有选时的最大金额
        // dp[i][1]表示第i个物品选择时的最大金额
        int[][] dp = new int[n][2];
        dp[p][0] = 0;
        dp[p][0] = nums[p];
        for(int i = p+1; i <= r; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[r][0], dp[r][1]);
    }

}
