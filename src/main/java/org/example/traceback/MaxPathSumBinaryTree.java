package org.example.traceback;

import org.example.pojo.TreeNode;

/**`
 * 二叉树中的最大路径和
 */
public class MaxPathSumBinaryTree {
    private int result = -1001;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    private int dfs(TreeNode root) {
        if(root==null) {
            return 0;
        }

        int leftMaxPath = dfs(root.left);
        int rightMaxPath = dfs(root.right);

        int max = root.val;
        if(leftMaxPath>0) {
            max += leftMaxPath;
        }
        if(rightMaxPath>0) {
            max += rightMaxPath;
        }
        if(max>result) {
            result = max;
        }

        int ret = root.val;
        if(ret < leftMaxPath+root.val) {
            ret = leftMaxPath+root.val;
        }
        if(ret < rightMaxPath+root.val) {
            ret = rightMaxPath+root.val;
        }
        return ret;
    }

}
