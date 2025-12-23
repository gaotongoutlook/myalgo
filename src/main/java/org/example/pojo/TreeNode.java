package org.example.pojo;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode(int val) {
      this.val = val;
    }
    public TreeNode() {

    }

    public TreeNode(int val, TreeNode leftNode, TreeNode rightNode) {
        this.val = val;
        this.left = leftNode;
        this.right = rightNode;
    }
}