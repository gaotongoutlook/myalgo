package org.example.mydp;

import org.example.pojo.TreeNode;

/**
 * 打家劫舍
 */
public class RobberyModel {


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

}
