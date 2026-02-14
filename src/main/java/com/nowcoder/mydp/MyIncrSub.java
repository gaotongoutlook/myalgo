package com.nowcoder.mydp;

import org.example.pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长递增子序列
 */
public class MyIncrSub {

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int result = 0;
        for(int i=0; i<n; i++) {
            if(dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    /**
     * 路径总和3
     */
    public int pathSum3(TreeNode root, int targetSum) {
        int count = 0;
        pathSum3Dfs(root, targetSum, count);
        return count;
    }

    private Map<Integer, Integer> pathSum3Dfs(TreeNode root, int targetSum, int count) {
        if(root==null) {
            return new HashMap<>();
        }

        Map<Integer, Integer> leftValues = new HashMap<>();
        Map<Integer, Integer> rightValues = new HashMap<>();
        Map<Integer, Integer> rootValues = new HashMap<>();
        rootValues.put(root.val, 1);

        for(Map.Entry<Integer, Integer> entry : leftValues.entrySet()) {
            int newKey = entry.getKey() + root.val;
            int newValue = entry.getValue();
            if(rootValues.containsKey(newKey)) {
                newValue += rootValues.get(newKey);
            }
            rootValues.put(newKey, newValue);
        }

        for(Map.Entry<Integer, Integer> entry : rightValues.entrySet()) {
            int newKey = entry.getKey() + root.val;
            int newValue = entry.getValue();
            if(rootValues.containsKey(newKey)) {
                newValue += rootValues.get(newKey);
            }
            rootValues.put(newKey, newValue);
        }

        for(Map.Entry<Integer, Integer> entry : rootValues.entrySet()) {
            if(entry.getKey() == targetSum) {
                count += entry.getValue();
            }
        }

        return rootValues;
    }

    public int pathSum(TreeNode root, int targetSum) {
        return pathSumDfs(root, targetSum).total;
    }

    private Result pathSumDfs(TreeNode node, int target) {
        if (node == null) {
            return new Result(0, new HashMap<>());
        }

        // 后序遍历：先处理左右子树
        Result left = pathSumDfs(node.left, target);
        Result right = pathSumDfs(node.right, target);

        // 当前节点的路径和统计表
        Map<Long, Integer> curMap = new HashMap<>();
        // 1. 当前节点单独作为路径
        curMap.put((long) node.val, 1);

        // 2. 合并左子树：从 node 出发，经过左孩子的所有路径
        for (Map.Entry<Long, Integer> entry : left.sumMap.entrySet()) {
            long sum = entry.getKey();
            long newSum = sum + node.val;
            curMap.put(newSum, curMap.getOrDefault(newSum, 0) + entry.getValue());
        }

        // 3. 合并右子树：从 node 出发，经过右孩子的所有路径
        for (Map.Entry<Long, Integer> entry : right.sumMap.entrySet()) {
            long sum = entry.getKey();
            long newSum = sum + node.val;
            curMap.put(newSum, curMap.getOrDefault(newSum, 0) + entry.getValue());
        }

        // 当前节点作为起点的路径中，和为 target 的数量
        int curTotal = curMap.getOrDefault((long) target, 0);

        // 总合法路径数 = 左子树内 + 右子树内 + 当前节点作为起点的
        int total = left.total + right.total + curTotal;

        return new Result(total, curMap);
    }

    // 辅助类，存储递归结果
    class Result {
        int total;                  // 子树中所有合法路径数目
        Map<Long, Integer> sumMap;  // 从当前节点出发向下的路径和 -> 出现次数
        Result(int total, Map<Long, Integer> sumMap) {
            this.total = total;
            this.sumMap = sumMap;
        }
    }

}
