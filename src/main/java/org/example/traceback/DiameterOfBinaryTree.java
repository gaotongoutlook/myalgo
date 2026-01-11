package org.example.traceback;

import org.example.pojo.TreeNode;

/**
 * 二叉树的直径（就是左右子树深度之和）
 */
public class DiameterOfBinaryTree {

    private int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calMaxHeight(root);
        return result;
    }

    private int calMaxHeight(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int maxLeftHeight = calMaxHeight(root.left);
        int maxRightHeight = calMaxHeight(root.right);
        int diameter = maxLeftHeight + maxRightHeight;
        if(diameter>result) {
            result = diameter;
        }
        return Math.max(maxLeftHeight, maxRightHeight) + 1;
    }


}
