package org.example.dfs;

/**
 * 二维矩阵搜索或遍历
 */
public class MovingCountDfs {

    private boolean[][] visited;
    private int count = 0;

    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        dfs(0, 0, m, n, k);
        return count;
    }

    private void dfs(int i, int j, int m, int n, int k) {
        visited[i][j] = true;
        count++;
        int[][] directions= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int di=0; di<4; di++) {
            int newi = j + directions[di][0];
            int newj = j + directions[di][1];
            if(newi>=m || newi<0 || newj>=n || newj<0 || visited[newi][newj] || !check(newi, newj, k)) {
                continue;
            }
            dfs(newi, newj, m, n, k);
        }
    }

    private boolean check(int i, int j, int k) {
        int sum = 0;
        while(i != 0) {
            sum += (i%10);
            i /= 10;
        }
        while(j != 0) {
            sum += (j%10);
            j /= 10;
        }
        return sum <= k;
    }

}
