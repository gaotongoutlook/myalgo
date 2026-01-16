package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MyBackTraceTest {

    /**
     * 没有重复项数字的全排列
     */
    public ArrayList<ArrayList<Integer>> permute (int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        if(num==null || num.length==0) {
            return result;
        }

        permuteBackTrace(num, 0, path, result);

        return result;
    }

    private void permuteBackTrace(int[] num, int step, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if(step == num.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<num.length; i++) {
            if(path.contains(num[i])) {
                continue;
            }
            path.add(num[i]);
            permuteBackTrace(num, step+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 有重复项数字的全排列
     */
    public ArrayList<ArrayList<Integer>> permuteUnique (int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[num.length];
        if(num==null || num.length==0) {
            return result;
        }
        Arrays.sort(num);

        permuteUniqueBackTrace(num, used, 0, path, result);

        return result;
    }

    private void permuteUniqueBackTrace(int[] num, boolean[] used, int step, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if(step == num.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<num.length; i++) {
            if(used[i]) {
                continue;
            }

            if(i>0 && num[i]==num[i-1] && !used[i-1]) {
                continue;
            }

            used[i] = true;
            path.add(num[i]);
            permuteUniqueBackTrace(num, used, step+1, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    /**
     * 岛屿数量
     */
    public int numIslands (char[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        int result = 0;
        boolean[][] visited = new boolean[h][w];

        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(!visited[i][j] && grid[i][j]=='1') {
                    result++;
                    numIslandsBackTrace(grid, visited, i, j, h, w);
                }
            }
        }

        return result;
    }

    private void numIslandsBackTrace(char[][] grid, boolean[][] visited, int i, int j, int h, int w) {
        int[][] dur = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;

        for(int[] d : dur) {
            int newi = i + d[0];
            int newj = j + d[1];
            if(newi>=0 && newi <h && newj>=0 && newj<w && !visited[newi][newj] && grid[newi][newj]=='1') {
                numIslandsBackTrace(grid, visited, newi, newj, h, w);
            }
        }
    }

    /**
     * 字符串的排列
     */
    public ArrayList<String> Permutation (String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str==null || str.length()==0) {
            return result;
        }
        char[] cs = str.toCharArray();
        StringBuilder path = new StringBuilder();
        boolean[] used = new boolean[cs.length];
        Arrays.sort(cs);

        PermutationBackTrace(cs, used, 0, path, result);

        return result;
    }

    private void PermutationBackTrace(char[] cs, boolean[] used, int step, StringBuilder path, ArrayList<String> result) {
        if(step==cs.length) {
            result.add(path.toString().trim());
            return;
        }

        for(int i=0; i<cs.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i>0 && cs[i]==cs[i-1] && !used[i-1]) {
                continue;
            }

            path.append(cs[i]);
            used[i] = true;
            PermutationBackTrace(cs, used, step+1, path, result);
            path.deleteCharAt(path.length()-1);
            used[i] = false;
        }
    }

    /**
     * N皇后
     */
    public int Nqueen (int n) {
        if(n<=1) {
            return n;
        }

        List<char[][]> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                path[i][j] = '*';
            }
        }

        NqueenBackTrace(0, n, path, result);

        return result.size();
    }

    private void NqueenBackTrace(int row, int n, char[][] path, List<char[][]> result) {
        if(row == n) {
            char[][] snapshot = new char[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(path[i], 0, snapshot[i], 0, n);
            }
            result.add(snapshot);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isNqueenOk(path, row, col, n)) {  // 修正参数顺序
                path[row][col] = 'Q';
                NqueenBackTrace(row + 1, n, path, result);
                path[row][col] = '*';  // 回溯
            }
        }
    }

    private boolean isNqueenOk(char[][] path, int row, int col, int n) {
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
        char[] path = new char[2*n];

        generateParenthesisBackTrace(n, 0, 0, 0, path, result);

        return result;
    }

    private void generateParenthesisBackTrace(int n, int used, int leftUsed, int rightUsed, char[] path, ArrayList<String> result) {
        if(used==2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if(leftUsed < n) {
            path[used] = '(';
            generateParenthesisBackTrace(n, used+1, leftUsed+1, rightUsed, path, result);
            // 这个为什么没有撤回的代码呢原因

        }
        if(leftUsed > rightUsed && rightUsed < n) {
            path[used] = ')';
            generateParenthesisBackTrace(n, used+1, leftUsed, rightUsed+1, path, result);
        }
    }

    /**
     * 矩阵最长递增路径
     */
    public int longestIncreasingPath (int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return 0;
        }

        int maxLength = 0;
        int h = matrix.length;
        int w = matrix[0].length;
        boolean[][] visited = new boolean[h][w];
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                maxLength = Math.max(maxLength, longestIncreasingPathBackTrace(matrix, visited, i, j, h, w, 1));
            }
        }

        return maxLength;
    }

    private int longestIncreasingPathBackTrace(int[][] matrix, boolean[][] visited, int i, int j, int h, int w, int curLength) {
        visited[i][j] = true;
        int[][] dur = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int maxLength = curLength;
        for(int[] d : dur) {
            int newi = i + d[0];
            int newj = j + d[1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && matrix[i][j] < matrix[newi][newj]) {
                visited[newi][newj] = true;
                int result = longestIncreasingPathBackTrace(matrix, visited, newi, newj, h, w, curLength+1);
                maxLength = Math.max(maxLength, result);
            }
            // 这儿为什么撤销的是这个呢
            visited[i][j] = false;
        }

        return maxLength;
    }


}
