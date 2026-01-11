package org.example.traceback;

import org.example.pojo.TreeNode;

import java.awt.image.RasterOp;
import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 */
public class PathSumBinaryTree {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) {
            return result;
        }
        dfs(root, sum, new ArrayList<>(), 0);
        return result;
    }

    private void dfs(TreeNode root, int sum, ArrayList<Integer> path, int pathSum) {
        path.add(root.val);
        pathSum += root.val;
        if(root.left==null && root.right==null) {
            if(pathSum==sum) {
                List<Integer> pathSnapshot = new ArrayList<>();
                pathSnapshot.addAll(path);
                result.add(pathSnapshot);
            }
            path.remove(path.size()-1);
            return;
        }
        if(root.left!=null) {
            dfs(root.left, sum, path, pathSum);
        }
        if(root.right!=null) {
            dfs(root.right, sum, path, pathSum);
        }
        path.remove(path.size()-1);
        // pathSum==root.val 不需要这一句
    }

}
