package org.example.dfs;

/**
 * 单词搜索
 */
public class SearchLetter {

    public boolean searchLetter(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean existed = false;
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                boolean[][] visited = new boolean[h][w];
                searchLetterDfs(board, word, i, j, 0, visited, existed, h, w);
                if(existed) {
                    break; // return true
                }
            }
        }

        return existed;
    }

    private void searchLetterDfs(char[][] board, String word, int i, int j, int k, boolean[][] visited, boolean existed, int h, int w) {
        if(existed) {
            return;
        }
        if(word.charAt(k) != board[i][j]) {
            return;
        }
        visited[i][j] = true;
        if(k == word.length()-1) {
            existed = true;
            return;
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] di : dirs) {
            int newi = i + di[0];
            int newj = j + di[1];
            if(newi>=0 && newi<h && newj>=0 && newj<w && !visited[newi][newj]) {
                searchLetterDfs(board, word, newi, newj, k+1, visited, existed, h, w);
            }
        }
        visited[i][j] = false;
    }

}
