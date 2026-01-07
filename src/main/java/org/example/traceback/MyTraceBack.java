package org.example.traceback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 回溯
 */
public class MyTraceBack {


    public int knapasck(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j]) {
                   dp[i][j] = true;
                    if (j+weight[i] <= w) {
                        dp[i][j+weight[i]] = true;
                    }
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]){
                return i;
            }
        }

        return 0;
    }

    /**
     * 物品的总价值最大
     * @return
     */
    private int maxV = Integer.MIN_VALUE;
    private int[] weight = new int[]{};
    private int[] value = new int[]{};
    int n = 5;
    private int w = 9;

    public void bigValue(int i, int cw, int cv) {
        if(cw == w || i == n) {
            if(cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 不选择第i个物品
        bigValue(i+1, cw, cv);
        if(weight[i] + cw <= w) {
            // 选择第i个物品
            bigValue(i+1, cw+weight[i], cv+value[i]);
        }
    }



    public int knapasck2(int[] weight, int[] value, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = value[0];
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i][j] == Integer.MIN_VALUE) {
                    continue;
                }
                // 不选择
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
                // 选择
                if(j+weight[i]<=w) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + value[i]);
                }
            }
        }

        // 在结果集中求出最大价值的数
        int res = Integer.MIN_VALUE;
        for(int i=w; i>=0; i++) {
            if(dp[n-1][w] > res) {
                res = dp[n-1][w];
            }
        }

        return res;
    }

    public int knapsack4(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        dp[0][0] = 1;
        if(weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]];
                }
            }

        }

        return dp[n-1][w];
    }

    public int knapsack3(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-weight[i]]+1);
                }
            }
        }

        if(dp[n-1][w] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[n-1][w];
    }

    public boolean knapsack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][w];
    }

    public int knapsack1(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return 0;
    }

    public static void main1(String[] args) {
        List<List<Integer>> result = new MyTraceBack().combine(4, 2);
        System.out.println();
        for (List<Integer> temp : result) {
            for (Integer num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backtrace(list, new ArrayList<>(), 1, n, k);
        return list;
    }

    public void backtrace(List<List<Integer>> list, List<Integer> temp, int step, int n, int k) {
        // 结束条件
        if(temp.size() == k) {
            List<Integer> result = new ArrayList<>(temp);
            list.add(result);
            return;
        }

        for(int i=step; i<=n; i++) {
            if(temp.contains(i)){
                continue;
            }
            // 选择
            temp.add(i);
            // 回溯
            backtrace(list, temp, n, step+1, k);
            // 不选择
            temp.remove(temp.size()-1);
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return null;
    }

    public static void main2(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        new MyTraceBack().permute(nums);
        System.out.println(result.size());
        for (List<Integer> s : result) {
            System.out.println();
            for(Integer integer : s) {
                System.out.println(integer);
            }
            System.out.println();
        }
    }

    public static void main4(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        /*List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        new MyTraceBack().backtrace(nums, 0, path, res);

        System.out.print("总数为: " + res.size());
        for (List<Integer> s : res) {
            for(Integer integer : s) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }*/

        new MyTraceBack().permute(nums);
        System.out.print("总数为: " + result.size());
        for (List<Integer> s : result) {
            for(Integer integer : s) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }
    }

    private void backtrace(int[] nums, int k, List<Integer> path, List<List<Integer>> res) {
        if(k == nums.length) {
            res.add(new ArrayList<>(path));
        } else {
            // 不做选择
            backtrace(nums, k+1, path, res);
            // 做选择
            path.add(nums[k]);
            // 回溯 递归
            backtrace(nums, k+1, path, res);
            // 移除选择
            path.remove(path.size()-1);
        }
    }

    private static List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrace1(nums, 0, path);
        return result;
    }

    private void backtrace1(int[] nums, int k, List<Integer> path) {
        // 结束条件
        if(k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 不做选择
        backtrace1(nums, k+1, path);
        // 做选择
        path.add(nums[k]);
        // 回溯 递归
        backtrace1(nums, k+1, path);
        // 移除选择
        path.remove(path.size()-1);
    }


    private void mytestbacktrace(int[] nums, int count, int k, int cw, int w, List<Integer> path) {
        if(k==count) {
            if(cw == w) {
                List<Integer> sortedUniqueList = new ArrayList<>(path).stream()
                        .sorted() // 排序
                        .distinct() // 去重
                        .collect(Collectors.toList());

                result.add(sortedUniqueList);
            }
            return;
        }
        for(int i=k; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            //  不做选择
            //mytestbacktrace(nums, count, k+1, cw, w, path);
            // 选择
            path.add(nums[i]);
            mytestbacktrace(nums, count,k+1, cw+nums[i], w, path);
            path.remove(path.size()-1);
        }
    }

    public static void main3(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        int k = 3;
        int n = 9;
        new MyTraceBack().mytestbacktrace(nums, k,0,0,n, new ArrayList<>());
        //new MyTraceBack().mytestbacktrace1(nums, k,0,0,n, new ArrayList<>());
        //result = new MyTraceBack().combinationSum3Alt(k, n);
        System.out.println("总数为: " + result.size());
        for (List<Integer> s : result) {
            for(Integer integer : s) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum3Alt(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        dfs(1, k, n, current, result);
        return result;
    }

    private static void dfs(int start, int k, int target,
                            List<Integer> current, List<List<Integer>> result) {
        // 剪枝：如果剩余和小于0 或者 当前组合元素个数超过k
        if (target < 0 || current.size() > k) {
            return;
        }

        // 找到有效组合
        if (current.size() == k && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 遍历可能的选择
        for (int i = start; i <= 9; i++) {
            // 剪枝：如果当前数字已经大于目标值
            if (i > target) {
                break;
            }

            // 剪枝：剩余数字不足以凑够k个元素
            if ((9 - i + 1) < (k - current.size())) {
                break;
            }

            current.add(i);
            dfs(i + 1, k, target - i, current, result);
            current.remove(current.size() - 1);
        }
    }



    private void mytestbacktrace1(int[] nums, int count, int k, int cw, int w, List<Integer> path) {
        if(cw == w) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=k; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            // 选择
            path.add(nums[i]);
            mytestbacktrace1(nums, count,k+1, cw+nums[i], w, path);
            path.remove(path.size()-1);
        }
    }

    // 可选列表 路径 阶段
    public List<List<Integer>> test1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        test2(candidates, 0, 0, target, path, result);
        return result;
    }

    private void test2(int[] candidates, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 终止条件
        if(k == candidates.length) {
            if(sum == target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        // 不选择
        test2(candidates, k+1, sum, target, path, result);
        // 选择
        path.add(candidates[k]);
        // 回溯
        test2(candidates, k+1, sum+candidates[k], target, path, result);
        // 移除
        path.remove(path.size()-1);
    }

    private List<List<Integer>> result = new ArrayList<>();
    /**
     * 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrace(nums, 0, path);
        return result;
    }

    // 路径：记录在path中
    // 决策阶段： k
    // 可选列表： nums中除掉存在于path中的数据
    private void backtrace(int[] nums, int k, List<Integer> path) {
        // 结束条件
        if(k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 回溯 递归
            backtrace(nums, k+1, path);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }


    /**
     * 八皇后
     */
    public List<char[][]> eightQueue() {
        List<char[][]> result = new ArrayList<>();
        char[][] board = new char[8][8];
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                board[i][j] = '*';
            }
        }
        backtrace(0, board, result);
        return result;
    }

    // row：阶段
    // board：路径，记录已经做出的决策
    // 可选列表：通过board推导出来，没有显式记录
    private void backtrace(int row, char[][] board, List<char[][]> result) {
        // 结束条件，得到可行解
        if(row == 8) {
            char[][] snapshot = new char[8][8];
            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    snapshot[i][j] = board[i][j];
                }
            }
            result.add(snapshot);
            return;
        }

        for(int col=0; col<8; col++) { // 每一行都有8种放法
            if(isOk(board, row, col)) { // 可选列表
                board[row][col] = 'Q'; // 做选择，第row行的棋子放到了col列
                backtrace(row+1, board, result); // 考察下一行
                board[row][col] = '*'; // 恢复选择
            }
        }
    }

    private boolean isOk(char[][] board, int row, int col) {
        int n = 8;
        // 检查列是否有冲突
        for(int i=0; i<row; i++) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查右上对角线是否有冲突
        int i = row+1;
        int j = col+1;
        while (i>=0 && j<n) {
            if(board[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        // 检查左上对角线是否有冲突
        i = row - 1;
        j = col - 1;
        while (i>=0 && j>=0) {
            if(board[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        return true;
    }

    /**
     * 0-1背包问题
     */
    public int bage(int[] items, int w) {
        int maxW = Integer.MIN_VALUE;
        backtrace(items, 0, 0, w, maxW);
        return maxW;
    }

    // k:阶段
    // cw：路径，记录已经选择的物品的总重量
    // items[k]：选择列表，选或不选
    // w：剪枝的条件
    private void backtrace(int[] items, int k, int cw, int w, int maxW) {
        // cw==w 表示装满了； k==items.length 表示已经考察完所有的物品
        if(cw == w || k == items.length) {
            if(cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 做选择
        backtrace(items, k+1, cw, w, maxW); // 不装
        if(cw + items[k] <= w) { // 剪枝
            backtrace(items, k+1, cw+items[k], w, maxW); // 装
        }
        // 都是局部变量，自动撤销选择
    }

    public ArrayList<Set<Integer>> test(int[] nums) {
        ArrayList<Set<Integer>> result = new ArrayList<>();
        mybacktrace(nums, 0, new HashSet<>(), result);
        return result;
    }

    private void mybacktrace(int[] nums, int k, Set<Integer> path, ArrayList<Set<Integer>> result) {
        if(k == nums.length) {
            result.add(new HashSet<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            // 不做选择
            mybacktrace(nums, k+1, path, result);
            // 做选择
            path.add(nums[i]);
            mybacktrace(nums, k+1, path, result);
            path.remove(nums[i]);
        }
    }



    public List<List<Integer>> test3(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        mybacktrace(candidates, 0, 0, target, path, result);
        return result;
    }

    private void mybacktrace(int[] candidates, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        if(sum == target) {
            result.add(new ArrayList<>(path));
            System.out.println("知道一个添加到集合");
            return;
        }

        for(int i=k; i<candidates.length; i++) {
            if(sum + candidates[i] > target) { // 快速剪枝 并且发现当前数字全部用完了还不支持
                break;
            }
            path.add(candidates[i]);
            // 阶段i和阶段k的差别是什么
            System.out.println("i的值为： "+i+" , sum的值为： "+sum+" , target的值为： "+target+" , path中的值为： "+path2String(path));
            mybacktrace(candidates, i,sum+candidates[i], target, path, result);
            System.out.println("当前准备要删除的数字是： "+path.get(path.size()-1));
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 排序，方便去重
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();

        backtrack(nums, used, path, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        // 如果当前排列的长度等于数组长度，说明找到了一个完整排列
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果数字已经被使用，跳过
            if (used[i]) {
                continue;
            }

            // 去重关键：如果当前数字和前一个数字相同，且前一个数字没有被使用，跳过
            // 这样可以保证相同数字的相对顺序，避免重复排列
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // 选择当前数字
            used[i] = true;
            path.add(nums[i]);

            // 递归继续构建排列
            backtrack(nums, used, path, result);

            // 回溯，撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    private boolean matched = false;
    public boolean match(char[] text, char[] pattern) {
        backtrace(text, pattern, 0, 0);
        return matched;
    }

    // pj 相当于阶段
    // 路径不需要记录(pattern中的每个字符怎么匹配的)
    // 可选列表由: pattern[pj] text ti 推导出来
    private void backtrace(char[] text, char[] pattern, int ti, int pj) {
        // 结束条件
        if(pj == pattern.length) { // 正则表达式到结尾了
            if(ti == text.length) { // 可行解
                matched = true; // 文本串也到结尾了
            }
            return;
        }

        // 做选择(根据pattern中当前考察字符的具体情况)
        if(pattern[pj] == '*') { // *匹配任意个字符
            for(int k=0; k<=text.length-ti; k++) {
                backtrace(text, pattern, ti+k, pj+1);
            }
        } else if(pattern[pj] == '?') {
            backtrace(text, pattern, ti, pj+1);
            if(ti < text.length) {
                backtrace(text, pattern, ti+1, pj+1);
            }
        } else if(ti<text.length && pattern[pj]==text[ti]) {
            backtrace(text, pattern, ti+1, pj+1);
        }
        // 撤销选择，因为没有全局变量，局部变量会在递归返回时自动恢复
    }

    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][][] blocks = new boolean[3][3][10];
    private boolean solved = false;
    public void solveSudoku(char[][] board) {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    blocks[i/3][j/3][num] = true;
                }
            }
        }
        backtrace(0, 0, board);
    }

    private void backtrace(int row, int col, char[][] board) {
        if(row==9) {
            solved = true;
            return;
        }
        if(board[row][col]!='.') {
            int nextRow = row;
            int nextCol = col+1;
            if(col == 8) {
                nextRow = row+1;
                nextCol = 0;
            }
            backtrace(nextRow, nextCol, board);
            if(solved) {
                return;
            }
        } else {
            for(int num=1; num<=9; num++) {
                if(!rows[row][num] && !cols[col][num] && !blocks[row/3][col/3][num]) {
                    board[row][col] = String.valueOf(num).charAt(0); // 数字转化成字符
                    rows[row][num] = true;
                    cols[col][num] = true;
                    blocks[row/3][col/3][num] = true;
                    int nextRow = row;
                    int nextCol = col+1;
                    if(col==8) {
                        nextRow = row+1;
                        nextCol = 0;
                    }
                    backtrace(nextRow, nextCol, board);
                    if(solved) {
                        return;
                    }
                    board[row][col] = '.';
                    rows[row][num] = false;
                    cols[col][num] = false;
                    blocks[row/3][col/3][num] = false;
                }
            }
        }
    }

    private List<String> result1 = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
       if(digits.length()==0) {
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

       char[] path = new char[digits.length()];
       backtrace(mappings, digits, 0, path);
       return result1;
    }

    // k表示阶段
    // path路径
    // digits[k]+mappings确定当前阶段的可选列表
    private void backtrace(String[] mappings, String digits, int k, char[] path) {
        if(k==digits.length()) {
            result1.add(new String(path));
            return;
        }
        String mapping = mappings[digits.charAt(k)-'0'];
        for(int i=0; i<mapping.length(); i++) {
            path[k] = mapping.charAt(i);
            backtrace(mappings, digits, k+1, path);
        }
    }

    private String path2String(List<Integer> path) {
        StringBuilder sb = new StringBuilder("[");
        for(Integer i : path) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        /*int[] nums = new int[]{2, 3, 6, 7};
        int target = 9;
        List<List<Integer>> result = new MyTraceBack().test4(nums, target);
        System.out.println("数量: "+ result.size());
        for(List<Integer> list : result) {
            for(Integer integer : list) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }*/
        int[] nums = new int[]{2,5,2,1,2};
        int target = 5;
        List<List<Integer>> result = new MyTraceBack().combinationSum3(nums, target);
        System.out.println("数量: "+ result.size());
        for(List<Integer> list : result) {
            for(Integer integer : list) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 先对数组排序，方便剪枝和去重
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start,
                           List<Integer> current, List<List<Integer>> result) {
        // 如果剩余目标为0，说明找到了一个有效组合
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 从start开始遍历，避免重复组合
        for (int i = start; i < candidates.length; i++) {
            // 剪枝：如果当前数字大于剩余目标，由于数组已排序，后面的数字只会更大
            if (candidates[i] > remain) {
                break;
            }

            // 选择当前数字
            current.add(candidates[i]);

            // 递归调用，注意这里传递的是i而不是i+1，因为数字可以重复使用
            backtrack(candidates, remain - candidates[i], i, current, result);

            // 撤销选择（回溯）
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> test4(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        mybacktrace1(candidates, 0, 0, target, path, result);
        return result;
    }

    private void mybacktrace1(int[] candidates, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        if(k == candidates.length) {
            if( sum == target) {
                result.add(new ArrayList<>(path));
                System.out.println("知道一个添加到集合");
            }
            return;
        }
        if(sum > target) { // 剪枝
            return;
        }

        // 选择和不选两种分支
        mybacktrace1(candidates, k+1, sum, target, path, result);

        path.add(candidates[k]);
        mybacktrace1(candidates, k+1,sum+candidates[k], target, path, result);
        path.remove(path.size()-1);

        /*for(int i=k; i<candidates.length; i++) {
            if(sum + candidates[i] > target) { // 快速剪枝 并且发现当前数字全部用完了还不支持
                break;
            }
            path.add(candidates[i]);
            // 阶段i和阶段k的差别是什么
            System.out.println("i的值为： "+i+" , sum的值为： "+sum+" , target的值为： "+target+" , path中的值为： "+path2String(path));
            mybacktrace1(candidates, i,sum+candidates[i], target, path, result);
            System.out.println("当前准备要删除的数字是： "+path.get(path.size()-1));
            path.remove(path.size()-1);
        }*/
    }

    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        trace1(result, path,candidates,target,0,0);
        return result;
    }

    public static void trace1(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int sum, int k) {
        if (sum == target) {
            //得到预期目标
            result.add(new ArrayList<>(path));
        }
        Set<Integer> visited = new HashSet<>(); // set的作用范围问题
        for (int j = k; j < candidates.length; j++) {
            if (visited.contains(candidates[j])) { // 添加是否访问过这个参数
                continue;
            } else {
                visited.add(candidates[j]);
            }
            if (sum + candidates[j] > target){ // 剪枝
                //此路不通，后路肯定也不通
                break;
            }
            //继续试
            path.add(candidates[j]);
            trace1(result, path,candidates,target,sum+candidates[j],j);
            path.remove(path.size()-1);
        }
    }



    private void mybacktrace(int[] candidates, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        if(sum == target) {
            result.add(new ArrayList<>(path));
            System.out.println("知道一个添加到集合");
            return;
        }

        for(int i=k; i<candidates.length; i++) {
            if(sum + candidates[i] > target) { // 快速剪枝 并且发现当前数字全部用完了还不支持
                break;
            }
            path.add(candidates[i]);
            // 阶段i和阶段k的差别是什么
            System.out.println("i的值为： "+i+" , sum的值为： "+sum+" , target的值为： "+target+" , path中的值为： "+path2String(path));
            mybacktrace(candidates, i,sum+candidates[i], target, path, result);
            System.out.println("当前准备要删除的数字是： "+path.get(path.size()-1));
            path.remove(path.size()-1);
        }
    }



    public static void trace(List<List<Integer>> result, List<Integer> res, int[] candidates, int target, int curr, int index) {
        if (curr == target) {
            //得到预期目标
            result.add(new ArrayList<>(res));
        }
        Set<Integer> visit = new HashSet<>();
        for (int j = index; j < candidates.length; j++) {
            if (visit.contains(candidates[j])) {
                continue;
            } else {
                visit.add(candidates[j]);
            }
            if (curr + candidates[j] > target){
                //此路不通，后路肯定也不通
                break;
            }
            //继续试
            res.add(candidates[j]);
            int len = res.size();
            trace(result, res,candidates,target,curr+candidates[j],j+1);
            res.remove(len-1);
        }
    }

    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        trace(result, res,candidates,target,0,0);
        return result;
    }

}
