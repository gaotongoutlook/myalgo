package org.example.treenode;

import org.example.pojo.TreeNode;

import java.util.ArrayList;

public class MyTreeNode {

    /**
     * 输出二叉树的右视图
     * @param preOrder 先序遍历
     * @param inOrder 中序遍历
     * @return 二叉树的右视图
     */
    public int[] solve (int[] preOrder, int[] inOrder) {
        if(preOrder == null || preOrder.length == 0 || inOrder == null || inOrder.length == 0) {
            return new int[0];
        }
        if(preOrder.length != inOrder.length) {
            return new int[0];
        }

        // 先序遍历 中左右
        // 中序遍历 左中右
        // 根据两种情况，把二叉树还原回来

        return null;
    }

    /**
     * 按照之字形打印二叉树
     * 从左到右 从右到左 依次打印
     * @param pRoot 根节点
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print (TreeNode pRoot) {
        return null;
    }

}
