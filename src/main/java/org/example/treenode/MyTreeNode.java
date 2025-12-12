package org.example.treenode;

import org.example.pojo.TreeNode;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.ArrayList;
import java.util.List;

public class MyTreeNode {

    /**
     * 输出二叉树的右视图
     *
     * @param preOrder 先序遍历
     * @param inOrder  中序遍历
     * @return 二叉树的右视图
     */
    public int[] solve(int[] preOrder, int[] inOrder) {
        if (preOrder == null || preOrder.length == 0 || inOrder == null || inOrder.length == 0) {
            return new int[0];
        }
        if (preOrder.length != inOrder.length) {
            return new int[0];
        }

        // 先序遍历 中左右
        // 中序遍历 左中右
        // 根据两种情况，把二叉树还原回来
        TreeNode root = buildTree(preOrder, 0, preOrder.length-1, inOrder, 0, inOrder.length-1);
        List<Integer> rightNode = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            rightNode.add(cur.val);
            cur = cur.right;
        }

        int[] result = new int[rightNode.size()];
        for (int i = 0; i < rightNode.size(); i++) {
            result[i] = rightNode.get(i);
        }

        return result;
    }

    private TreeNode buildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 找到左右子树的分界点
        int rootIndex = -1;
        for(int i=inStart; i<=inEnd; i++) {
            if(rootVal == inOrder[i]) {
                rootIndex = i;
                break;
            }
        }

        // 计算左子树节点数
        int leftSize = rootIndex - inStart;

        // 分割为左右子树
        root.left = buildTree(preOrder, preStart+1, preStart+leftSize, inOrder, inStart, rootIndex-1);
        root.right = buildTree(preOrder, preStart+leftSize+1, preEnd, inOrder, rootIndex+1, inEnd);

        return root;
    }

    /**
     * 按照之字形打印二叉树
     * 从左到右 从右到左 依次打印
     * @param pRoot 根节点
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if(pRoot == null) {
            return new ArrayList<>();
        }

        boolean circle = false;


        return null;
    }

}
