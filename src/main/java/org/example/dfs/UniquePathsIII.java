package org.example.dfs;

import org.example.pojo.MyPosi;

import java.util.ArrayList;
import java.util.List;

public class UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int h = grid.length;
        int w = grid[0].length;
        boolean[][] visited = new boolean[h][w];

        // 找到起点并计算需要访问的总格子数
        int startX = -1, startY = -1;
        int emptyCount = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                    emptyCount++;
                } else if (grid[i][j] == 0 || grid[i][j] == 2) {
                    emptyCount++;
                }
            }
        }

        List<List<MyPosi>> result = new ArrayList<>();
        List<MyPosi> path = new ArrayList<>();
        dfsWithPath(grid, visited, startX, startY, emptyCount, 0, path, result);

        return result.size();
    }

    private void dfsWithPath(int[][] grid, boolean[][] visited, int i, int j,
                             int total, int count, List<MyPosi> path,
                             List<List<MyPosi>> result) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
                visited[i][j] || grid[i][j] == -1) {
            return;
        }

        path.add(new MyPosi(i, j));

        if (grid[i][j] == 2) {
            if (count + 1 == total) {
                result.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }

        visited[i][j] = true;

        dfsWithPath(grid, visited, i - 1, j, total, count + 1, path, result);
        dfsWithPath(grid, visited, i + 1, j, total, count + 1, path, result);
        dfsWithPath(grid, visited, i, j - 1, total, count + 1, path, result);
        dfsWithPath(grid, visited, i, j + 1, total, count + 1, path, result);

        visited[i][j] = false;
        path.remove(path.size() - 1);
    }

}
