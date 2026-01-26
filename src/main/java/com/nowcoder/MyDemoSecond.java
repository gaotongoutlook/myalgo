package com.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDemoSecond {

    public static void main(String[] args) {
        MyDemoSecond example = new MyDemoSecond();
    }

    /**
     * 字符串的回文结构
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        char[] cs = s.toCharArray();
        partitionBackTrace(cs, 0, path, result);

        return result;
    }

    private void partitionBackTrace(char[] cs, int start, List<String> path, List<List<String>> result) {
        result.add(new ArrayList<>(path));

        for(int i=start; i<cs.length; i++) {
            path.add(String.valueOf(cs[i]));

            path.remove(path.size()-1);
        }
    }

    /**
     * 岛屿数量
     */
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        int result = 0;
        boolean[][] visited = new boolean[h][w];

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(grid[i][j]=='1' && !visited[i][j]) {
                    numIslandsDfs(grid, i, j, visited, h, w);
                    result++;
                }
            }
        }

        return result;
    }

    private void numIslandsDfs(char[][] grid, int i, int j, boolean[][] visited, int h, int w) {
        visited[i][j] = true;

        int[][] dur = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] d : dur) {
            int newi = i+d[0];
            int newj = j+d[1];
            if(newi>=0 && newj>=0 && newi<h && newj<w && !visited[newi][newj] && grid[newi][newj]=='1') {
                numIslandsDfs(grid, newi, newj, visited, h, w);
            }
        }
    }

    public int Nqueen (int n) {
        List<char[][]> result = new ArrayList<>();
        if(n<=0) {
            return result.size();
        }

        char[][] path = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                path[i][j] = '*';
            }
        }

        NqueenDfs(0, n, path, result);

        return result.size();
    }

    private void NqueenDfs(int row, int n, char[][] path, List<char[][]> result) {
        if(row == n) {
            char[][] snapshot = new char[n][n];
            for(int i=0; i<n; i++) {
                System.arraycopy(path[i], 0, snapshot[i], 0, n);
            }
            result.add(snapshot);
            return;
        }

        for(int col=0; col<n; col++) {
            if(isOk(path, row, col, n)) {
                path[row][col] = 'Q';
                NqueenDfs(row+1, n, path, result);
                path[row][col] = '*';
            }
        }
    }

    private boolean isOk(char[][] path, int row, int col, int n) {
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
     * 括号生成
     */
    public ArrayList<String> generateParenthesis (int n) {
        ArrayList<String> result = new ArrayList<>();
        if(n<=0) {
            return result;
        }

        StringBuilder path = new StringBuilder();
        generateParenthesisDfs( 0, 0, n, path, result);

        return result;
    }

    private void generateParenthesisDfs(int leftUsed, int rightUsed, int n, StringBuilder path, ArrayList<String> result) {
        if(leftUsed + rightUsed == 2*n) {
            result.add(path.toString());
            return;
        }
        if(leftUsed < n) {
            path.append("(");
            generateParenthesisDfs(leftUsed+1, rightUsed, n, path, result);
            path.deleteCharAt(path.length()-1);
        }
        if(rightUsed < leftUsed && rightUsed < n) {
            path.append(")");
            generateParenthesisDfs(leftUsed, rightUsed+1, n, path, result);
            path.deleteCharAt(path.length()-1);
        }
    }

    /**
     * 矩阵最长递增路径
     */
    public int solve (int[][] matrix) {
        int maxLength = 0;
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return maxLength;
        }

        int h = matrix.length;
        int w = matrix[0].length;
        boolean[][] visited = new boolean[h][w];
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                int r = solveDfs(matrix, i, j, visited, 1, h, w);
                maxLength = Math.max(maxLength, r);
            }
        }

        return maxLength;
    }

    private int solveDfs(int[][] matrix, int i, int j, boolean[][] visited, int curLength, int h, int w) {
        visited[i][j] = true;
        int maxLength = curLength;

        int[][] dur = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] d : dur) {
            int newi = i+d[0];
            int newj = j+d[1];
            if(newi>=0 && newj>=0 && newi<h && newj<w && !visited[newi][newj] && matrix[newi][newj]>matrix[i][j]) {
                int length = solveDfs(matrix, newi, newj, visited, curLength+1, h, w);
                maxLength = Math.max(maxLength, length);
            }
        }

        visited[i][j] = false;

        return maxLength;
    }

    /**
     * 没有重复数字的全排列
     */
    public ArrayList<ArrayList<Integer>> permute (int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        permuteBackTrace(num, 0, path, result);

        return result;
    }

    private void permuteBackTrace(int[] nums, int step, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if(path.size()==nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 全排列，每一个都要运算多次，只能从0开始，排除使用过的重复的数据
        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }

            path.add(nums[i]);
            permuteBackTrace(nums, step+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 有重复数字的全排列
     */
    public ArrayList<ArrayList<Integer>> permuteUnique (int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        Arrays.sort(num);
        boolean[] used = new boolean[num.length];
        permuteUniqueBackTrace(num, used, path, result);

        return result;
    }

    private void permuteUniqueBackTrace(int[] nums, boolean[] used, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if(path.size()==nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i>0 && nums[i-1]==nums[i] && !used[i-1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteUniqueBackTrace(nums, used, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    /**
     * 字符串的全排列
     */
    public ArrayList<String> Permutation (String str) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        char[] cs = str.toCharArray();
        Arrays.sort(cs);
        boolean[] used = new boolean[cs.length];
        PermutationBackTrace(cs, used, path, result);

        return result;
    }

    private void PermutationBackTrace(char[] cs, boolean[] used, StringBuilder path, ArrayList<String> result) {
        if(path.length()==cs.length) {
            result.add(path.toString());
            return;
        }
        for(int i=0; i<cs.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i>0 && cs[i-1]==cs[i] && !used[i-1]) {
                continue;
            }
            path.append(cs[i]);
            used[i] = true;
            PermutationBackTrace(cs, used, path, result);
            path.deleteCharAt(path.length()-1);
            used[i] = false;
        }
    }

}
