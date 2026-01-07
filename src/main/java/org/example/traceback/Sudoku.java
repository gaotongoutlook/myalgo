package org.example.traceback;

/**
 * 数独
 */
public class Sudoku {
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
}
