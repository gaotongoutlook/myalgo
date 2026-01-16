package org.example;

import org.example.dfs.MyGraph;
import org.example.pojo.TreeNode;
import org.example.utils.PrintUtils;

import java.util.*;


public class Example {

    /**
     * 1.组合
     * 2.子集
     * 3.子集（重复数字）
     * 4.全排列
     * 5.全排列（重复数字）
     * 6.组合总和
     * 7.组合总和（多次使用）
     * 8.组合总和（重复数字）
     */

    private static final Example example = new Example();

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[]{1,2,3,4};

        // 1.组合
        System.out.println("---------- 1.组合 ----------");
        int n = 4;
        int k = 2;
        result = example.one(n, k);
        PrintUtils.printString(result);
        result.clear();

        // 2.子集
        System.out.println("---------- 2.子集 ----------");
        nums = new int[]{1,2,3};
        result = example.two(nums);
        PrintUtils.printString(result);
        result.clear();

        // 3.子集（重复数字）
        System.out.println("---------- 3.子集（重复数字） ----------");
        nums = new int[]{1,2,2};
        result = example.three(nums);
        PrintUtils.printString(result);
        result.clear();

        // 4.全排列
        System.out.println("---------- 4.全排列 ----------");
        nums = new int[]{1,2,3};
        result = example.four(nums);
        PrintUtils.printString(result);
        result.clear();

        // 5.全排列（重复数字）
        System.out.println("---------- 5.全排列（重复数字） ----------");
        nums = new int[]{1,2,2};
        result = example.five(nums);
        PrintUtils.printString(result);
        result.clear();

        // 6.组合总和
        System.out.println("---------- 6.组合总和 ----------");
        nums = new int[]{2,4,5,6,7};
        int target = 9;
        result = example.six(nums, target);
        PrintUtils.printString(result);
        result.clear();

        // 7.组合总和（多次使用）
        System.out.println("---------- 7.组合总和（多次使用） ----------");
        nums = new int[]{2,4,5,6,7};
        target = 9;
        result = example.seven(nums, target);
        PrintUtils.printString(result);
        result.clear();

        // 8.组合总和（重复数字）
        System.out.println("---------- 8.组合总和（重复数字） ----------");
        nums = new int[]{1,2,2,4,4,5,6,7};
        target = 9;
        Arrays.sort(nums);
        result = example.eight(nums, target);
        PrintUtils.printString(result);
        result.clear();

        List<List<String>> stringResult = new ArrayList<>();

        // 9.字符串回文结构
        System.out.println("---------- 9.字符串回文结构 ----------");
        String s = new String("abcba");
        stringResult = example.partition(s);
        PrintUtils.printStringResult(stringResult);
        stringResult.clear();

        // 目标和 添加加和减方式 让和为目标数字
        System.out.println("---------- 目标和 ----------");
        /*nums = new int[]{2,7,9,13,27,31,37,3,2,3,5,7,11,13,17,19,23,29,47,53};
        target = 7;*/
        nums = new int[]{1, 1, 1, 1, 1};
        target = 3;
        Arrays.sort(nums);
        int res = example.findTargetSumWays1(nums, target);
        System.out.println("结果为: "+res);
    }

    public List<List<Integer>> one(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<nums.length; i++) {
            nums[i] = i+1;
        }
        oneBackTrace(nums, 0, k, path, result);
        return result;
    }

    private void oneBackTrace(int[] nums, int start, int k, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            oneBackTrace(nums, i+1, k, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> two(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        twoBackTrace(nums, 0, path, result);
        return result;
    }

    private void twoBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for(int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            twoBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> three(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        threeBackTrace(nums, 0, path, result);
        return result;
    }

    private void threeBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for(int i=start; i<nums.length; i++) {
            if(i>start && nums[i]==nums[i-1]) {
                continue;
            }

            path.add(nums[i]);
            threeBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> four(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        fourBackTrace(nums, 0, path, result);
        return result;
    }

    private void fourBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }

            path.add(nums[i]);
            fourBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> five(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        fiveBackTrace(nums, used, path, result);
        return result;
    }

    private void fiveBackTrace(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(used[i]) {
                continue;
            }

            if(i>0 && nums[i]==nums[i-1] && !used[i-1]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);
            fiveBackTrace(nums, used, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    public List<List<Integer>> six(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        sixBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void sixBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            sixBackTrace(nums, i+1, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> seven(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        sevenBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void sevenBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            sevenBackTrace(nums, i, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> eight(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        eightBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void eightBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            if(visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);

            path.add(nums[i]);
            eightBackTrace(nums, i+1, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 先排序，方便去重
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backtrack(nums, used, path, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字已经使用过，跳过
            if (used[i]) {
                continue;
            }

            // 去重关键：如果当前数字和前一个数字相同，且前一个数字没有被使用，跳过
            // 这样可以保证相同的数字按顺序使用，避免重复排列
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            backtrack(nums, used, path, result);

            // 回溯
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 括号数量
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] path = new char[2*n];
        generateParenthesisBackTrace(n, 0, 0, 0, path, result);
        return result;
    }

    private void generateParenthesisBackTrace(int n, int leftUsed, int rightUsed, int step, char[] path, List<String> result) {
        if(step == 2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if(leftUsed < n) {
            path[step] = '(';
            generateParenthesisBackTrace(n, leftUsed+1, rightUsed, step+1, path, result);
        }
        if(leftUsed > rightUsed && rightUsed < n) { // 右括号数量不能比左括号多，否则不匹配了
            path[step] = ')';
            generateParenthesisBackTrace(n, leftUsed, rightUsed+1, step+1, path, result);
        }
    }

    /**
     * 字符串回文结构
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionBackTrace(s, 0, path, result);
        return result;
    }

    private void partitionBackTrace(String s, int step, List<String> path, List<List<String>> result) {
        if(step == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int end=step; end<s.length(); end++) {
            String subStr = s.substring(step, end+1);
            // System.out.println("第"+step+"步，字符串为： "+subStr+" ，是不是回文串 "+isPalindrome(subStr));
            if(isPalindrome(subStr)) {
                path.add(subStr);
                partitionBackTrace(s, end+1, path, result);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(i<=j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 手机号对应字母的集合
     */
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return Collections.emptyList();
        }

        String[] mappings = new String[10];
        mappings[2] = "abc";
        mappings[3] = "efg";
        mappings[4] = "ghi";
        mappings[5] = "jkl";
        mappings[6] = "mno";
        mappings[7] = "pqrs";
        mappings[8] = "tuv";
        mappings[9] = "wxyz";

        List<String> result = new ArrayList<>();
        char[] path = new char[digits.length()];
        letterCombinationsBackTrace(mappings, digits, 0, path, result);
        return result;
    }

    private void letterCombinationsBackTrace(String[] mappings, String digits, int step, char[] path, List<String> result) {
        if(digits.length()==step) {
            result.add(String.valueOf(path));
            return;
        }

        String mapping = mappings[digits.charAt(step)-'0'];
        for(int i=0; i<mapping.length(); i++) {
            path[step] = mapping.charAt(i);
            letterCombinationsBackTrace(mappings, digits, step+1, path, result);
        }
    }

    /**
     * N皇后问题
     */
    public List<char[][]> numberQueue(int n) {
        List<char[][]> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                path[i][j] = '*';
            }
        }
        numberQueueBackTrace(0, n, path, result);
        return result;
    }

    private void numberQueueBackTrace(int row, int n, char[][] path, List<char[][]> result) {
        if(row == n) {
            char[][] snapshot = new char[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    snapshot[i][j] = path[i][j];
                }
            }
            result.add(snapshot);
            return;
        }

        for(int col=0; col<n; col++) {
            if(isOk(path, n, row, col)) {
                path[row][col] = 'Q';
                numberQueueBackTrace(row+1, n, path, result);
                path[row][col] = '*';
            }
        }
    }

    private boolean isOk(char[][] path, int n, int row, int col) {
        // 检查列是否有冲突
        for(int i=0; i<row; i++) {
            if(path[i][col] == 'Q') {
                return false;
            }
        }

        // 检查右上对角线是否有冲突
        int i = row-1;
        int j = col+1;
        while (i>=0 && j<n) {
            if(path[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        // 检查左上对角线是否有冲突
        i = row - 1;
        j = col - 1;
        while (i>=0 && j>=0) {
            if(path[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        return true;
    }

    /**
     * 二叉树的直径（就是左右子树深度之和）
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int result = 0;
        diameterOfBinaryTreeBackTrace(root, result);
        return result;
    }

    private int diameterOfBinaryTreeBackTrace(TreeNode root, int result) {
        if(root==null) {
            return 0;
        }
        int leftHeight = diameterOfBinaryTreeBackTrace(root.left, result);
        int rightHeight = diameterOfBinaryTreeBackTrace(root.right, result);
        int height = leftHeight + rightHeight; // 计算路径和
        if(height > result) {
            result = height;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 二叉树的路径和 任意两个节点间最大路径和 不存在重复节点
     */
    public int maxPathSum(TreeNode root) {
        int result = -1001;
        maxPathSumDfs(root, result);
        return result;
    }

    private int maxPathSumDfs(TreeNode root, int result) {
        if(root==null) {
            return 0;
        }

        int leftPath = maxPathSumDfs(root.left, result);
        int rightPath = maxPathSumDfs(root.right, result);

        // 处理最大值
        int max = 0;
        if(leftPath > 0) {
            max += leftPath;
        }
        if(rightPath > 0) {
            max += rightPath;
        }
        if(result < max) {
            max = result;
        }

        return Math.max(leftPath, rightPath) + root.val;
    }


    /**
     * 路径和等于某一值
     */
    public List<List<Integer>> pathSumBinaryTree(TreeNode root, int sum) {
        if(root==null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        pathSumBinaryTreeBackTrace(root, sum, 0, path, result);
        return result;
    }

    private void pathSumBinaryTreeBackTrace(TreeNode root, int sum, int pathSum, List<Integer> path, List<List<Integer>> result) {
        if(root.left == null && root.right==null) {
            if(sum == pathSum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        if(root.left!=null) {
            path.add(root.left.val);
            pathSumBinaryTreeBackTrace(root.left, sum, pathSum+root.left.val, path, result);
            path.remove(path.size()-1);
        }
        if(root.right!=null) {
            path.add(root.right.val);
            pathSumBinaryTreeBackTrace(root.right, sum, pathSum+root.right.val, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 岛屿数量
     * 定义一个访问了的数组 向上下左右进行搜索
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        boolean[][] visited = new boolean[h][w];
        int result = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(!visited[i][j] && grid[i][j]=='1') {
                    result++;
                    numIslandsDfs(grid, visited, i, j, h, w);
                }
            }
        }
        return result;
    }

    private void numIslandsDfs(char[][] grid, boolean[][] visited, int i, int j, int h, int w) {
        // 上下左右四个方位
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true; // 1 1    2 2    3 3
        for(int k=0; k<4; k++) {
            int newi = i + directions[k][0]; // -1 1 0 0     0 2 1 1
            int newj = j + directions[k][1]; // 0 0 -1 1     1 1 0 2
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && grid[newi][newj]=='1') {
                numIslandsDfs(grid, visited, newi, newj, h, w);
            }
        }
    }

    /**
     * 矩阵最长递增路径
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxLength = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 尝试从每个位置开始寻找最长递增路径
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLength = Math.max(maxLength,
                        longestIncreasingPathDfs(matrix, i, j, rows, cols, new boolean[rows][cols], 1));
            }
        }

        return maxLength;
    }

    private int longestIncreasingPathDfs(int[][] matrix, int row, int col, int rows, int cols, boolean[][] visited, int currentLength) {
        // 四个方向
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        visited[row][col] = true;
        int maxLength = currentLength;

        // 尝试四个方向
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if(newRow>=0 && newRow<rows && newCol>=0 && newCol<cols && !visited[newRow][newCol] && matrix[newRow][newCol] > matrix[row][col]) {
                // 递归探索
                int length = longestIncreasingPathDfs(matrix, newRow, newCol, rows, cols, visited, currentLength + 1);
                maxLength = Math.max(maxLength, length);
            }
        }
        // 回溯：撤销选择
        visited[row][col] = false;

        return maxLength;
    }

    /**
     * BFS遍历
     */
    public static void bfsTraversal(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        int[] distance = new int[graph.vertices];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited[startVertex] = true;
        distance[startVertex] = 0;

        System.out.println("BFS遍历结果:");
        while(!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            System.out.printf("顶点 %d (距离: %d)\n", current, distance[current]);

            for(Integer neighbor : graph.adjList[current]) {
                if(!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current]+1;
                }
            }
        }
        System.out.println();
    }

    /**
     * 查找从起点到目标的最短路径
     */
    public static List<Integer> bfsShortestPath(MyGraph graph, int startVertex, int target) {
        boolean[] visited = new boolean[graph.vertices];
        int[] parent = new int[graph.vertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited[startVertex] = true;

        boolean found = false;
        while(!queue.isEmpty() && !found) {
            int current = queue.poll();
            for(Integer neighbor : graph.adjList[current]) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                }

                if(neighbor == target) {
                    found = true;
                    break;
                }
            }
        }

        if(found) {
            List<Integer> path = new ArrayList<>();
            for(Integer curr = target; curr!=-1; curr = parent[curr]) {
                path.add(curr);
            }
            Collections.reverse(path);
            return path;
        }

        return Collections.emptyList(); // 没有找到路径
    }

    /**
     * 递归实现DFS
     */
    public static void dfsRecursive(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        System.out.println("DFS递归遍历结果:");
        dfsRecursiveHelper(graph, startVertex, visited);
        System.out.println();
    }

    private static void dfsRecursiveHelper(MyGraph graph, int startVertex, boolean[] visited) {
        visited[startVertex] = true;
        LinkedList<Integer> linkedList = graph.adjList[startVertex];
        for(Integer neighbor : linkedList) {
            if(!visited[neighbor]) {
                dfsRecursiveHelper(graph, neighbor, visited);
            }
        }
    }

    /**
     * 迭代实现DFS（使用栈）
     */
    public static void dfsIterative(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        dfsIterativeHelper(graph, startVertex, visited);
    }

    private static void dfsIterativeHelper(MyGraph graph, int startVertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while(!stack.isEmpty()) {
            Integer current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                LinkedList<Integer> linkedList = graph.adjList[current];
                Collections.reverse(linkedList); // 注意：为了与递归版本结果一致，需要逆序压入栈
                for(Integer neighbor : linkedList) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    /**
     * 检查图中是否有环（有向图）
     */
    public static boolean hasCycle(MyGraph graph) {
        int vertices = graph.vertices;
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (hasCycleBackTrace(graph, i, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycleBackTrace(MyGraph graph, int vertex, boolean[] visited, boolean[] recursionStack) {
        if(recursionStack[vertex]) {
            return true;
        }
        if(visited[vertex]) {
            return false;
        }

        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int neighbor : graph.adjList[vertex]) {
            if (hasCycleBackTrace(graph, neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack[vertex] = false; // 撤销选择的目的是？？？

        return false;
    }

    /**
     * 添加加和减方式 让和为目标数字
     */
    public int findTargetSumWays(int[] nums, int target) {
        if(nums==null || nums.length==0) {
            return 0;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findTargetSumWaysBackTrace(nums,  0, 0, target, path, result);
        return result.size();
    }

    private void findTargetSumWaysBackTrace(int[] nums, int sum, int step, int target, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == path.size()) {
            if(sum == target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        path.add(nums[step]);
        findTargetSumWaysBackTrace(nums, sum+nums[step], step+1, target, path, result);
        path.remove(path.size()-1);


        path.add(-nums[step]);
        findTargetSumWaysBackTrace(nums, sum-nums[step], step+1, target, path, result);
        path.remove(path.size()-1);
    }

    /**
     * 添加加和减方式 让和为目标数字
     */
    public int findTargetSumWays1(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        return findTargetSumWaysBacktrack1(nums, 0, 0, target);
    }

    private int findTargetSumWaysBacktrack1(int[] nums, int index, int sum, int target) {
        // 终止条件：处理完所有数字
        if(index == nums.length) {
            System.out.println("----------------");
            return sum == target ? 1 : 0;
        }

        // 当前数字有两种选择：加号或减号
        System.out.println("addWays: " + sum + " : " + nums[index] + " : " +(sum + nums[index]) + " : "+index);
        int addWays = findTargetSumWaysBacktrack1(nums, index + 1, sum + nums[index], target);
        System.out.println("subtractWays: " + sum + " : " + nums[index] + " : " +(sum - nums[index]) + " : "+index);
        int subtractWays = findTargetSumWaysBacktrack1(nums, index + 1, sum - nums[index], target);

        return addWays + subtractWays;
    }

    /**
     * 不同路径III 1表示开始 2表示结束 0表示可走 -1表示障碍物
     */
    public int uniquePathsIII(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        int result = 0;
        boolean[][] visited = new boolean[h][w];

        // 找到1 和 找到2 当碰到-1时候，直接路径数清零 判断可行走的路径
        int starti = 0;
        int startj = 0;
        int endi = 0;
        int endj = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(grid[i][j] == 1) {
                    starti = i;
                    startj = j;
                }
                if(grid[i][j] == 2) {
                    endi = i;
                    endj = j;
                }
            }
        }

        result = uniquePathsIIIBackTrace(grid, visited, starti, startj, endi, endj, h, w, result);

        return result;
    }

    private int uniquePathsIIIBackTrace(int[][] grid, boolean[][] visited, int starti, int startj, int endi, int endj, int h, int w, int result) {
        visited[starti][startj] = true;

        int curResult = result;

        if(starti==startj && endi==endj) {
            return curResult+1;
        }

        int[][] dur = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int[] d : dur) {
            int newi = starti + d[0];
            int newj = startj + d[1];
            if(newi>=0 && newj<h && newj>=0 && newj<w && !visited[newi][newj]) {
                if(grid[newi][newj]==-1) {
                    return 0;
                }
                if(grid[newi][newj]==0) {
                    uniquePathsIIIBackTrace(grid, visited, newi, newj, endi, endj, h, w, result);
                }
            }
            visited[starti][startj] = false;
        }


        return curResult;
    }


}
