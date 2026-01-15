package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        int result = new MyTest().Nqueen(9);
        System.out.println(result);
    }


    public int Nqueen(int n) {
        if (n <= 1) {
            return n;
        }

        List<char[][]> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = '*';
            }
        }

        NqueenBackTrace(0, n, path, result);
        return result.size();
    }

    private void NqueenBackTrace(int row, int n, char[][] path, List<char[][]> result) {
        if (row == n) {
            // 使用深拷贝保存当前棋盘状态
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
        for (int i = 0; i < row; i++) {
            if (path[i][col] == 'Q') {
                return false;
            }
        }

        // 检查左上对角线是否有冲突
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (path[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        // 检查右上对角线是否有冲突
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < n) {
            if (path[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

}
