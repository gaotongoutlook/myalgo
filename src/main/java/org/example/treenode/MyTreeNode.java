package org.example.treenode;

import org.example.pojo.TreeNode;

import java.util.*;

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
        TreeNode root = buildTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
        // 层次遍历
        List<List<Integer>> list = levelOrderWithLevels(root);
        // 右视图，层次遍历的最右侧数据 
        if (list == null || list.size() == 0) {
            return new int[0];
        }

        int index = 0;
        int[] result = new int[list.size()];
        for (List<Integer> integers : list) {
            int last = integers.size() - 1;
            result[index] = integers.get(last);
            index++;
        }

        return result;
    }

    private TreeNode buildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 找到左右子树的分界点
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inOrder[i]) {
                rootIndex = i;
                break;
            }
        }

        // 计算左子树节点数
        int leftSize = rootIndex - inStart;

        // 分割为左右子树
        root.left = buildTree(preOrder, preStart + 1, preStart + leftSize, inOrder, inStart, rootIndex - 1);
        root.right = buildTree(preOrder, preStart + leftSize + 1, preEnd, inOrder, rootIndex + 1, inEnd);

        return root;
    }

    /**
     * 按照之字形打印二叉树
     * 从左到右 从右到左 依次打印
     *
     * @param pRoot 根节点
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        // 记录遍历的方向 之后层次遍历二叉树，然后做打印
        boolean circle = false;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            // 当前层的节点数量
            int levelSize = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<>();

            // 遍历当前层
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // 将下一层的节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 根据circle值的不同 返回的顺序不同
            if (circle) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = currentLevel.size()-1; i >= 0; i--) {
                    temp.add(currentLevel.get(i));
                }
                result.add(temp);
            } else {
                result.add(currentLevel);
            }

            circle = !circle;

            /*if (!circle) {
                result.add(currentLevel);
            } else {
                ArrayList<Integer> reversed = new ArrayList<>();
                for (int i = currentLevel.size() - 1; i >= 0; i--) {
                    reversed.add(currentLevel.get(i));
                }
                result.add(reversed);
            }
            circle = !circle;*/
        }

        return result;
    }

    /**
     * 二叉树的层次遍历
     *
     * @param root 根节点
     * @return
     */
    public List<List<Integer>> levelOrderWithLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 当前层的节点数量
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // 遍历当前层
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // 将下一层的节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(currentLevel);
        }

        return result;
    }


    /**
     * 二叉树最大深度
     * @param root
     * @return
     */
    public int maxDepth (TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return Math.max(left, right);
    }

    /**
     * 二叉树和为某一值的路径是否存在
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        if(root == null || sum <= 0) {
            return false;
        }
        // hasPathSum(root, 0, sum);
        return false;
    }

    public static void main(String[] args) {
        TreeNode leftLeftNode = new TreeNode(5);
        TreeNode leftRightNode = new TreeNode(1);

        TreeNode rightLeftNode = new TreeNode(7);
        TreeNode rightRightNode = new TreeNode(8);

        TreeNode leftNode = new TreeNode(9, leftLeftNode, leftRightNode);
        TreeNode rightNode = new TreeNode(0, rightLeftNode, rightRightNode);

        TreeNode root = new TreeNode(4, leftNode, rightNode);

        ArrayList<Integer> list = new MyTreeNode().sumNumbers2(root);
        System.out.println();
        for(Integer num : list) {
            System.out.println(num);
        }
        System.out.println();
    }

    public ArrayList<Integer> sumNumbers2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        sumNumbers3(root, new ArrayList<>(), 0, result);
        return result;
    }

    public int sumNumbers3(TreeNode node, ArrayList<Integer> list, int deep, ArrayList<Integer> result) {
        if(node == null) {
            int num = 0;
            for(int i=deep; i>0; i--) {
                num = num * 10 + list.get(i);
            }
            result.add(num);
            deep--;
            list.remove(deep);
            return num;
        }

        // 生成数字 这儿存储插入数字
        list.add(node.val);
        deep++;

        if(node.left != null) {
            sumNumbers3(node.left, list, deep, result);
        }
        if(node.right != null) {
            sumNumbers3(node.right, list, deep, result);
        }

        return 0;
    }


    /**
     * 二叉树所有根到叶子路径组成的数字之和
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode node, int sum) {
        if(node == null) {
            return 0;
        }

        sum = sum * 10 + node.val;
        if(node.left == null && node.right == null) {
            return sum;
        }

        return sumNumbers(node.left, sum) + sumNumbers(node.right, sum);
    }

    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(root.val);

        int totalSum = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currentSum = sumStack.pop();

            // 如果是叶子节点，累加到总和
            if (node.left == null && node.right == null) {
                totalSum += currentSum;
                continue;
            }

            // 处理右子节点
            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(currentSum * 10 + node.right.val);
            }

            // 处理左子节点
            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(currentSum * 10 + node.left.val);
            }
        }

        return totalSum;
    }

}
