package org.example;

import org.example.dfs.MyGraph;
import org.example.pojo.Interval;
import org.example.pojo.ListNode;
import org.example.pojo.MyPosi;
import org.example.pojo.TreeNode;
import org.example.utils.PrintUtils;
import java.util.*;
import java.util.List;

public class Test {

    /**
     * 非重复数组非重复选择
     */
    public List<List<Integer>> selectOnce(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        selectOnceBackTrace(nums, 0, 0, target, path, result);
        return result;
    }

    // nums 可选列表
    // k 阶段
    // path 路径
    private void selectOnceBackTrace(int[] nums, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 结束条件
        if(k == nums.length) {
            if(sum==target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        // 不做选择
        selectOnceBackTrace(nums, k+1, sum, target, path, result);

        // 选择
        path.add(nums[k]);
        // 回溯
        selectOnceBackTrace(nums, k+1, sum+nums[k], target, path, result);
        // 撤销选择
        path.remove(path.size()-1);
    }


    /**
     * 非重复数组重复选择 不能够存在某一个数字不选择，而是可能选择多次
     */
    public List<List<Integer>> selectOnceRepeat(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        selectOnceRepeatBackTrace(nums, 0, 0, target, path, result);
        return result;
    }

    // nums 可选列表
    // k 阶段
    // path 路径
    private void selectOnceRepeatBackTrace(int[] nums, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 结束条件 当都必须选择的时候，就不存在阶段信息了
        if(sum==target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=k; i<nums.length; i++) {
            if(sum + nums[i] > target) { // 剪枝
                break;
            }
            //  选择
            path.add(nums[i]);
            // 回溯 可能重复选择多次，所以阶段也会重复多次
            selectOnceRepeatBackTrace(nums, i, sum+nums[i], target, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }

    public static void main1(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};
        int target = 9;
        List<List<Integer>> result = new Test().selectOnce(nums, target);
        PrintUtils.printString(result);

        System.out.println();
        result.clear();
        result = new Test().selectOnceRepeat(nums, target);
        PrintUtils.printString(result);

        System.out.println();
        result.clear();
        int n = 4;
        int k = 2;
        result = new Test().testplzh(n, k);
        PrintUtils.printString(result);

        System.out.println();
        nums = new int[]{1,2,3};
        result.clear();
        result = new Test().subSets(nums);
        PrintUtils.printString(result);

        System.out.println();
        nums = new int[]{1,2,2};
        result.clear();
        result = new Test().subSetsWithDup(nums);
        PrintUtils.printString(result);

        System.out.println();
        nums = new int[]{1,2,2};
        result.clear();
        result = new Test().subSetsWithDup1(nums);
        PrintUtils.printString(result);

        System.out.println();
        new Test().mytest();
    }

    public List<List<Integer>> testplzh(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = i+1;
        }
        testplzhbacktrace(nums, 0, 0, k, path, result);
        return result;
    }

    private void testplzhbacktrace(int[] nums, int start, int step, int k, List<Integer> path, List<List<Integer>> result) {
        if(k == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            testplzhbacktrace(nums, i+1, step+1, k, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> subSets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subSetsBackTrace(nums, 0, 0, path, result);
        return result;
    }

    private void subSetsBackTrace(int[] nums, int start, int step, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == step) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            // 不选择
            subSetsBackTrace(nums, i+1,step+1, path, result);
            if(path.contains(nums[i])) { // 剪枝
                continue;
            }
            // 选择
            path.add(nums[i]);
             // 回溯
            subSetsBackTrace(nums, i+1,step+1, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> subSetsWithDup1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        subSetsWithDupBackTrace1(nums, 0, 0, path, result);
        return result;
    }

    private void subSetsWithDupBackTrace1(int[] nums, int start, int step, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == step) {
            result.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for(int i=start; i<nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 不选择
            subSetsWithDupBackTrace1(nums, i+1,step+1, path, result);
            /*if(path.contains(nums[i])) { // 剪枝
                continue;
            }*/
            /*if(visited.contains(nums[i])) {
                continue;
            }
            if(!path.contains(nums[i])) {
                visited.add(nums[i]);
            }*/

            // 选择
            path.add(nums[i]);
            // 回溯
            subSetsWithDupBackTrace1(nums, i+1,step+1, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }


    public List<List<Integer>> subSetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);  // 关键步骤：必须先排序
        subSetsWithDupBackTrace(nums, 0, path, result);
        return result;
    }

    private void subSetsWithDupBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));  // 添加当前子集到结果集

        for (int i = start; i < nums.length; i++) {
            // 跳过重复元素：如果当前元素与前一个元素相同，且不是第一个，则跳过
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 选择当前元素
            path.add(nums[i]);

            // 递归探索下一层
            subSetsWithDupBackTrace(nums, i + 1, path, result);

            // 回溯，撤销选择
            path.remove(path.size() - 1);
        }
    }





    private void backtrackBinarySearch(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        // 到达决策树的叶子节点
        if (step == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 情况1：选择当前元素
        path.add(nums[step]);
        backtrackBinarySearch(nums, step + 1, path, result);
        path.remove(path.size() - 1);

        // 跳过重复元素（对于重复情况）
        // 普通情况下直接递归到不选择分支

        // 情况2：不选择当前元素
        // 对于重复元素，需要跳过所有相同的
        int nextIndex = step + 1;
        while (nextIndex < nums.length && nums[nextIndex] == nums[step]) {
            nextIndex++;
        }
        backtrackBinarySearch(nums, nextIndex, path, result);
    }

    public List<List<Integer>> mytest() {
        int[] nums = new int[]{1,2,2};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrackStandard(nums, 0, path, result);
        PrintUtils.printString(result);

        System.out.println();

        result.clear();
        path.clear();
        nums = new int[]{1,2,3};
        backtrackStandard(nums, 0, path, result);
        PrintUtils.printString(result);


        System.out.println();
        System.out.println("----------------------");
        System.out.println();
        permute(nums);
        //PrintUtils.printString(result);

        result.clear();
        path.clear();
        System.out.println();
        nums = new int[]{1,1,2};
        Arrays.sort(nums);
        permute(nums);
        //PrintUtils.printString(result);

        return result;
    }

    private void backtrackStandard(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // 收集结果（所有路径都是子集）
        result.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            // 去重条件（对于重复元素）
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 做选择
            path.add(nums[i]);

            // 递归到下一层
            backtrackStandard(nums, i + 1, path, result);

            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        permuteBackTrace(nums, 0, path, result);
        PrintUtils.printString(result);
        return result;
    }

    private void permuteBackTrace(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            // 去重条件（对于重复元素）
            if (i > 0 && nums[i] == nums[i - 1] && !path.contains(nums[i-1])) {
                continue;
            }

            // 做选择
            path.add(nums[i]);

            // 递归到下一层
            permuteBackTrace(nums, step+1, path, result);

            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    /**
     * 岛屿数量
     */
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int num = 0;
        int h = grid.length;
        int w = grid[0].length;
        boolean[][] visited = new boolean[h][w];
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                visited[i][j] = false;
            }
        }

        for(int i=0; i<h; i++) {
            for (int j = 0; j < w; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    numIslandsDfs(grid, visited, 0, 0, h, w);
                }
            }
        }

        return num;
    }

    private void numIslandsDfs(char[][] grid, boolean[][] visited, int i, int j, int h, int w) {
        // 上下左右四个方位
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int newi = 0;
        int newj = 0;
        visited[i][j] = true;
        for(int[] d : directions) {
            newi = i + d[0];
            newj = j + d[1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && grid[newi][newj]==1) {
                numIslandsDfs(grid, visited, newi, newj, h, w);
            }
        }
    }

    /**
     * 括号生成
     */
    public List<String> generateParenthesis(int n) {
        if(n <= 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        char[] path = new char[2*n];
        generateParenthesisBackTrace(n, 0, 0, 0, path, result);
        return result;
    }

    private void generateParenthesisBackTrace(int n, int used, int leftUsed, int rightUsed, char[] path, List<String> result) {
        if(used == 2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if(leftUsed < n) {
            path[used] = '(';
            generateParenthesisBackTrace(n, used+1, leftUsed+1, rightUsed, path, result);
        }
        if(leftUsed < rightUsed && rightUsed < n) {
            path[used] = ')';
            generateParenthesisBackTrace(n, used+1, leftUsed, rightUsed+1, path, result);
        }
    }

    /**
     * 矩阵最长递增路径
     */
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return 0;
        }

        int maxLength = 0;
        int h = matrix.length;
        int w = matrix[0].length;
        boolean[][] visited = new boolean[h][w];

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(visited[i][j]) {
                    int result = longestIncreasingPathBackTrace(matrix, visited, i, j, h, w, 1);
                    maxLength = Math.max(maxLength, result);
                }
            }
        }

        return maxLength;
    }

    private int longestIncreasingPathBackTrace(int[][] matrix, boolean[][] visited, int i, int j, int h, int w, int currentLength) {
        // 上下左右四个方位
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int newi = 0;
        int newj = 0;
        int maxLength = currentLength;
        visited[i][j] = true;

        for(int[] d : directions) {
            newi = i + d[0];
            newj = j + d[1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && matrix[newi][newj] > matrix[i][j]) {
                int result = longestIncreasingPathBackTrace(matrix, visited, newi, newj, h, w, maxLength+1);
                maxLength = Math.max(result, maxLength);
            }
        }

        visited[i][j] = false;

        return maxLength;
    }

    /**
     * N皇后
     */
    public List<char[][]> numberQueue(int n) {
        if(n<=0) {
            return Collections.emptyList();
        }

        int row = 0;
        List<char[][]> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                path[i][j] = '*';
            }
        }

        numberQueueBackTrace(n, row, path, result);

        return result;
    }

    private void numberQueueBackTrace(int n, int row, char[][] path, List<char[][]> result) {
        if(row == n) {
            char[][] snapshots = new char[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    snapshots[i][j] = path[i][j];
                }
            }
            result.add(snapshots);
            return;
        }

        for(int i=row; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(numberQueueIsOK(path, i, j, n)) {
                    path[i][j] = 'Q';
                    numberQueueBackTrace(n, row+1, path, result);
                    path[i][j] = '*';
                }
            }
        }
    }

    private boolean numberQueueIsOK(char[][] path, int i, int j, int n) {
        boolean flag = true;

        // 竖行不能有
        for(int row=0; row<i; row++) {
            if(path[row][j]=='Q') {
                return false;
            }
        }

        // 左上斜边不能有
        int row = i-1;
        int col = j-1;
        while(row>=0 && col>=0) {
            if(path[row][col]=='Q') {
                return false;
            }
            row--;
            col--;
        }

        // 右上斜边不能有
        row = i-1;
        col = j+1;
        while(row>=0 && col<n) {
            if(path[row][col]=='Q') {
                return false;
            }
            row--;
            col++;
        }

        return flag;
    }

    /**
     * BFS遍历 包含统计路径长度
     */
    public static void bfsTraversal(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        int[] distance = new int[graph.vertices];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);
        distance[startVertex] = 0;

        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            for(Integer negi : graph.adjList[current]) {
                if(!visited[negi]) {
                    queue.add(negi);
                    visited[negi] = true;
                    distance[negi] = distance[current]+1;
                }
            }
        }
    }

    /**
     * 递归实现DFS
     */
    public static void dfsRecursive(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        dfsRecursiveDfs(graph, visited, startVertex);
    }

    private static void dfsRecursiveDfs(MyGraph graph, boolean[] visited, int startVertex) {
        visited[startVertex] = true;
        for(Integer neighbor : graph.adjList[startVertex]) {
            if(!visited[neighbor]) {
                dfsRecursiveDfs(graph, visited, neighbor);
            }
        }
    }

    /**
     * 迭代实现DFS（使用栈）
     */
    public static void dfsIterative(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while(!stack.isEmpty()) {
            Integer current = stack.pop();
            visited[current] = true;

            List<Integer> neighbors = graph.adjList[current];
            Collections.reverse(neighbors);
            for(Integer neighbor : neighbors) {
                if(!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
    }

    /**
     * 查找从起点到目标的最短路径
     */
    public static List<Integer> bfsShortestPath(MyGraph graph, int startVertex, int target) {
        return null;
    }

    /**
     * 二叉树的路径和
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
            result = max;
        }

        return Math.max(leftPath, rightPath) + root.val;
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

        int leftDepth = diameterOfBinaryTreeBackTrace(root.left, result);
        int rightDepth = diameterOfBinaryTreeBackTrace(root.right, result);
        result = Math.max(result, leftDepth+rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 路径和等于某一值
     */
    public List<List<Integer>> pathSumBinaryTree(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumBinaryTreeBackTrace(root, 0, sum, path, result);
        return result;
    }

    private void pathSumBinaryTreeBackTrace(TreeNode root, int cur, int sum, List<Integer> path, List<List<Integer>> result) {
        if(root.left==null && root.right==null) {
            if(cur == sum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        if(root.left != null) {
            path.add(root.left.val);
            pathSumBinaryTreeBackTrace(root.left, cur+root.left.val, sum, path, result);
            path.remove(path.size()-1);
        }

        if(root.right != null) {
            path.add(root.right.val);
            pathSumBinaryTreeBackTrace(root.right, cur + root.right.val, sum, path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 查找从起点到目标的最短路径
     */
    public static List<Integer> bfsShortestPath1(MyGraph graph, int start, int target) {
        if(start==target) {
            return Arrays.asList(start);
        }

        boolean[] visited = new boolean[graph.vertices];
        int[] parent = new int[graph.vertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        boolean found = false;

        while(!queue.isEmpty() && !found) {
            Integer cur = queue.poll();

            for(Integer neighbor : graph.adjList[cur]) {
                if(!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = cur;

                    if(neighbor == target) {
                        found = true;
                        break;
                    }
                }
            }
        }

        if(found) {
            List<Integer> path = new ArrayList<>();
            for(Integer at = target; at != -1; at= parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);
            return path;
        }

        return Collections.emptyList();
    }

    /**
     * 合并区间
     */
    public ArrayList<Interval> merge (ArrayList<Interval> intervals) {
        // 先做排序 之后在做别的 排序过程中进行合并
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        // 按区间起始位置排序
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        ArrayList<Interval> result = new ArrayList<>();

        // 3. 遍历并合并区间
        Interval current = intervals.get(0);
        result.add(current);

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);

            // 如果当前区间与下一个区间有重叠
            if(current.end >= next.start) {
                current.end = Math.max(next.end, current.end);
            } else {
                // 没有重叠，开始处理下一个区间
                current = next;
                result.add(current);
            }
        }

        return result;
    }

    /**
     * 删除链表的倒数第N个节点
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        if(head == null || n<=0) {
            return null;
        }

        int k = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        // 先看看有多少个节点
        while(cur!=null) {
            cur = cur.next;
            k++;
        }
        if(k < n) {
            return dummy.next;
        }
        if(k == n) {
            dummy.next = head.next;
            return dummy.next;
        }

        // 存在n比链表长度长 等于链表长度 小于链表长度 记录前一个链表节点
        int temp = k-n; // 此处注意，下边是大于1还是大于0 k-n+1 是第几个节点，从1开始 注意下标从0开始
        cur = dummy.next;
        while(temp>0 && cur!=null) {
            pre = cur;
            cur = cur.next;
            temp--;
        }

        // 删除这个节点
        pre.next = cur.next;

        return dummy.next;
    }

    /**
     * 比较版本号
     */
    public int compare (String version1, String version2) {
        if(version1==null && version2==null) {
            return 0;
        }
        if(version1==null || version2==null) {
            return version1==null ? 1 : 1;
        }

        // 将两个版本号分割成两个列表或者数组，之后做比对
        String[] v1Arr = version1.split("\\.");
        String[] v2Arr = version2.split("\\.");
        int v1Len = v1Arr.length;
        int v2Len = v2Arr.length;

        // 长度相同 和 长度不同
        for(int i=0; i<Math.min(v1Len, v2Len); i++) {
            int num1 = Integer.parseInt(v1Arr[i]);
            int num2 = Integer.parseInt(v2Arr[i]);
            if(num1 > num2) {
                return 1;
            } else if(num1 < num2) {
                return -1;
            }
        }

        // 需要判断后续剩余的是不是都为0
        if(v1Len < v2Len) {
            for(int i=v1Len; i<v2Len; i++) {
                int num = Integer.parseInt(v2Arr[i]);
                if(num!=0) {
                    return -1;
                }
            }
        } else {
            for(int i=v2Len; i<v1Len; i++) {
                int num = Integer.parseInt(v1Arr[i]);
                if(num!=0) {
                    return 1;
                }
            }
        }

        return 0;
    }

    /**
     * 合并二叉树
     */
    public TreeNode mergeTrees (TreeNode t1, TreeNode t2) {
        if(t1==null && t2==null) {
            return null;
        }
        if(t1==null || t2==null) {
            return t1==null ? t2 : t1;
        }
        return buildTreeNode(t1, t2);
    }

    private TreeNode buildTreeNode(TreeNode t1, TreeNode t2) {
        // 终止条件
        if(t1==null) {
            return t2;
        }
        if(t2==null) {
            return t1;
        }

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = buildTreeNode(t1.left, t2.left);
        root.right = buildTreeNode(t1.right, t2.right);
        return root;
    }

    public TreeNode mergeTrees1 (TreeNode t1, TreeNode t2) {
        // 终止条件
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees1(t1.left, t2.left);
        root.right = mergeTrees1(t1.right, t2.right);

        return root;
    }

    /**
     * 原地合并，修改 t1
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        // 如果 t1 为空，返回 t2
        if (t1 == null) {
            return t2;
        }
        // 如果 t2 为空，直接返回 t1
        if (t2 == null) {
            return t1;
        }

        // 合并当前节点值
        t1.val += t2.val;

        // 递归合并左右子树
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    /**
     * 使用队列实现广度优先合并
     */
    public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{t1, t2});

        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();
            TreeNode node1 = nodes[0];
            TreeNode node2 = nodes[1];

            // 合并当前节点值
            node1.val += node2.val;

            // 处理左子树
            if (node1.left != null && node2.left != null) {
                queue.offer(new TreeNode[]{node1.left, node2.left});
            } else if (node1.left == null) {
                node1.left = node2.left;
            }
            // 如果 node1.left 不为空但 node2.left 为空，什么都不做

            // 处理右子树
            if (node1.right != null && node2.right != null) {
                queue.offer(new TreeNode[]{node1.right, node2.right});
            } else if (node1.right == null) {
                node1.right = node2.right;
            }
        }

        return t1;
    }

    /**
     * 两数之和
     */
    public int[] twoSum (int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<numbers.length; i++) {
            if(map.containsKey(numbers[i])) {
                return new int[]{map.get(numbers[i])+1, i+1};
            }
            map.put(target-numbers[i], i);
        }

        return new int[]{};
    }

    /**
     * 三数之和
     */
    public ArrayList<ArrayList<Integer>> threeSum (int[] num) {
        if(num==null || num.length<=2) {
            return new ArrayList<>();
        }

        Map<Integer, int[]> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        // 怎么转换为两束之和 同时怎么去重
        for(int i=0; i<num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }

            // 返回下标，方便后续操作
            ArrayList<Integer> list = twoSum(num, -num[i], i);

            // 没有匹配的结果
            if(list.size()==0) {
                continue;
            }
            // 存在匹配的结果，但是这两个值已经引用过了
            if(list.size()==2 && map.containsKey(i) && list.contains(map.get(i)[0]) && list.contains(map.get(i)[1])) {
                continue;
            }

            int a = list.get(0);
            int b = list.get(1);
            ArrayList<Integer> r = new ArrayList<>();
            r.add(num[a]);
            r.add(num[b]);
            r.add(num[i]);
            result.add(r);

            map.put(a, new int[]{i, b});
            map.put(b, new int[]{i, a});
        }

        return result;
    }

    public ArrayList<Integer> twoSum (int[] numbers, int target, int except) {
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<numbers.length; i++) {
            if(i==except) {
               continue;
            }
            if(map.containsKey(numbers[i])) {
                result.add(map.get(numbers[i]));
                result.add(i);
                break;
            }
            map.put(target-numbers[i], i);
        }

        return result;
    }


    /**
     * 使用双指针的方式方法
     */
    public ArrayList<ArrayList<Integer>> threeSum1(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        // 边界条件处理
        if (num == null || num.length < 3) {
            return result;
        }

        // 1. 先排序
        Arrays.sort(num);
        int n = num.length;

        // 2. 遍历数组
        for (int i = 0; i < n - 2; i++) {
            // 跳过重复的元素（避免重复的三元组）
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }

            // 如果当前最小值已经大于0，不可能有三数之和为0
            if (num[i] > 0) {
                break;
            }

            // 使用双指针查找另外两个数
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = num[i] + num[left] + num[right];

                if (sum == 0) {
                    // 找到满足条件的三元组
                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(num[i]);
                    triplet.add(num[left]);
                    triplet.add(num[right]);
                    result.add(triplet);

                    // 跳过重复的左指针元素
                    while (left < right && num[left] == num[left + 1]) {
                        left++;
                    }
                    // 跳过重复的右指针元素
                    while (left < right && num[right] == num[right - 1]) {
                        right--;
                    }

                    // 移动指针
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 和太小，左指针右移
                    left++;
                } else {
                    // 和太大，右指针左移
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * N数之和
     */
    private ArrayList<ArrayList<Integer>> nSum(int[] nums, int target, int n, int start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        // 边界条件
        if (n < 2 || nums.length < n) {
            return result;
        }

        // 两数之和的情况
        if (n == 2) {
            int left = start;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(nums[left]);
                    pair.add(nums[right]);
                    result.add(pair);

                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        } else {
            // 递归处理 n > 2 的情况
            for (int i = start; i < nums.length - n + 1; i++) {
                // 跳过重复元素
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                // 递归调用
                ArrayList<ArrayList<Integer>> subResult = nSum(nums, target - nums[i], n - 1, i + 1);

                // 添加当前元素到结果中
                for (ArrayList<Integer> list : subResult) {
                    list.add(0, nums[i]); // 在开头添加当前元素
                    result.add(list);
                }
            }
        }

        return result;
    }

    /**
     * 三数之和
     */
    public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (num == null || num.length < 3) {
            return result;
        }

        Arrays.sort(num);
        int n = num.length;

        for (int i = 0; i < n - 2; i++) {
            // 跳过重复元素
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }

            // 使用Map存储需要查找的补数
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < n; j++) {
                int complement = -num[i] - num[j];

                if (map.containsKey(complement)) {
                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(num[i]);
                    triplet.add(complement);
                    triplet.add(num[j]);

                    // 确保不添加重复的三元组
                    if (!containsTriplet(result, triplet)) {
                        result.add(triplet);
                    }
                }

                map.put(num[j], j);
            }
        }

        return result;
    }

    // 检查是否已经包含该三元组
    private boolean containsTriplet(ArrayList<ArrayList<Integer>> result,
                                    ArrayList<Integer> triplet) {
        for (ArrayList<Integer> list : result) {
            if (list.get(0).equals(triplet.get(0)) &&
                    list.get(1).equals(triplet.get(1)) &&
                    list.get(2).equals(triplet.get(2))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 滑动窗口的最大值
     */
    public ArrayList<Integer> maxInWindows (int[] num, int size) {
        return null;
    }

    /**
     * 在二叉树中找到两个节点的最近公共祖先
     */
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        TreeNode result = findLCA(root, o1, o2);
        return result.val;
    }

    /**
     * 递归查找LCA
     * 返回找到的LCA节点，如果没找到但找到了o1或o2，返回对应的节点
     */
    private TreeNode findLCA(TreeNode root, int o1, int o2) {
        // 基本情况
        if (root == null || root.val == o1 || root.val == o2) {
            return root;
        }

        // 在左子树中查找
        TreeNode left = findLCA(root.left, o1, o2);
        // 在右子树中查找
        TreeNode right = findLCA(root.right, o1, o2);

        // 情况1：左右子树各找到一个节点，当前节点就是LCA
        if (left != null && right != null) {
            return root;
        }

        // 情况2：只在左子树找到，返回左子树的结果
        if (left != null) {
            return left;
        }

        // 情况3：只在右子树找到，返回右子树的结果
        if (right != null) {
            return right;
        }

        // 情况4：两个都没找到
        return null;
    }

    /**
     * 序列化与反序列化
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        // 先确定多少层


        // 之后根据层数做序列化


        return null;
    }
    TreeNode Deserialize(String str) {
        return null;
    }

    /**
     * 机器人的运动范围
     * 颜色填充
     * 节点的通路 无
     * 岛屿数量
     * 水域大小
     * 课程表
     * 单词搜索
     * 跳跃游戏III
     * 打开转盘锁 无
     *
     * 单词转换
     * 婴儿名字
     * 扫雷游戏
     * 单词接龙
     * 单词接龙II
     */

    /**
     * 课程及其课程依赖
     * [a, b] 如果学习a课程前必须学习课程b
     */
    public boolean course(int cources, int[][] depends) {
        // 首先先列出该课程被哪些课程依赖
        ArrayList<Integer>[] courseDependList = new ArrayList[cources];
        for(int i=0; i<cources; i++) {
            courseDependList[i] = new ArrayList<Integer>();
        }

        // 其次列出修当前课程需要依赖的课程数
        int[] dependArr = new int[cources];
        for(int i=0; i<cources; i++) {
            int course = depends[i][0];
            int dependCourse = depends[i][1];
            courseDependList[dependCourse].add(course);
            dependArr[course]++;
        }

        // 更具依赖关系，找出不需要依赖别的课程可修的课程，一次推理执行
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<dependArr.length; i++) {
            int dependCourses = dependArr[i];
            if(dependCourses==0) {
                // 注意放入的顺序
                queue.offer(i);
            }
        }

        // 统计可以完成的课程数
        int finishCourses = 0;
        while(!queue.isEmpty()) {
            int dependCourse = queue.remove();
            // 总共修的课程数又增了一门
            finishCourses++;
            // 看看哪些课程依赖了本课程
            ArrayList<Integer> dependList = courseDependList[dependCourse];
            for(Integer course : dependList) {
                // 依赖的课程少了一门
                dependArr[course]--;
                if(dependArr[course]==0) {
                    queue.add(course);
                }
            }
        }

        return finishCourses==cources;
    }

    /**
     * 不同路径III 节点A到节点B，排除障碍，总共有多少种方法到达
     * https://leetcode.cn/problems/unique-paths-iii/?envType=problem-list-v2&envId=backtracking
     */
    public int uniquePathsIII(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        boolean[][] visited = new boolean[h][w];
        List<List<MyPosi>> result = new ArrayList<>();
        List<MyPosi> path = new ArrayList<>();
        // 首先找到开始的坐标
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(grid[i][j]==0) {
                    uniquePathsIIIDfs(grid, visited, i, j, h, w, path, result);
                    break;
                }
            }
        }

        return result.size();
    }

    private void uniquePathsIIIDfs(int[][] grid, boolean[][] visited, int i, int j, int h, int w, List<MyPosi> path, List<List<MyPosi>> result) {
        path.add(new MyPosi(i, j));
        if(path.size() == h*w) {
            return;
        }
        if(grid[i][j]==2) {
            result.add(new ArrayList<>(path));
            return;
        }
        visited[i][j] = true;
        if(grid[i][j]==-1) {
            return;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] di : dirs) {
            int newi = i + di[0];
            int newj = j + di[1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && grid[newi][newj]==0) {
                uniquePathsIIIDfs(grid, visited, newi, newj, h, w, path, result);
            }
        }

        // path.remove(path.size()-1);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0}, {0,0,0,0}, {0,0,2,-1}};
        int result = new Test().uniquePathsIII(grid);
        System.out.println(result);
    }


}
