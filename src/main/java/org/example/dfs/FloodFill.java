package org.example.dfs;

/**
 * 颜色填充
 * 将初始表格的上下左右区域，扩散填充成给定颜色，但是扩散的条件是格子中的数字不是0
 */
public class FloodFill {

    public int[][] floodFill(int[][] images, int sr, int sc, int newColor) {
        int n = images.length;
        int m = images[0].length;
        floodFillDfs(images, n, m, sr, sc, images[sr][sc], newColor);
        return images;
    }

    private void floodFillDfs(int[][] images, int n, int m, int sr, int sc, int color, int newColor) {
        images[sr][sc] = newColor;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for(int k=0; k<4; k++) {
            int newi = sr + dirs[k][0];
            int newj = sc + dirs[k][1];
            if(newi<0 || newi>=n || newj<0 || newj>=n || images[newi][newj]!=color || images[newi][newj]==newColor) {
                continue;
            }
            floodFillDfs(images, n, m, newi, newj, color, newColor);
        }
    }

}
