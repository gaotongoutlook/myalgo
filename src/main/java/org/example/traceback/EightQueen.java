package org.example.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 */
public class EightQueen {
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
}
