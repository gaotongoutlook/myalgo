package org.example.dfs;

/**
 * 岛屿数量
 */
public class NumIslandsDfs {

    private boolean[][] visited;
    private int h;
    private int w;

    public int numIslands(char[][] grid) {
        h = grid.length;
        w = grid[0].length;
        visited = new boolean[h][w];
        int result = 0;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(!visited[i][j] && grid[i][j]=='1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;
        for(int k=0; k<4; k++) {
            int newi = j + directions[k][0];
            int newj = j + directions[k][1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj] && grid[newi][newj]=='1') {
                dfs(grid, newi, newj);
            }
        }
    }

}
