package com.nowcoder.regularity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规律
 */
public class MyRegularity {

    /**
     * 喝啤酒
     * 3个空瓶子换一瓶啤酒
     * 7个空瓶盖换一瓶啤酒
     * 能喝啤酒的最大数
     */
    private int drink(int x) {
        int count = x;
        int k1 = x;
        int k2 = x;
        while(k1>=3 || k2>=7) {
            while(k1>=3) {
                int change = k1/3;
                count += change;
                k1 %= 3;
                k1 += change;
                k2 += change;
            }
            while(k2>=7) {
                int change = k2/7;
                count += change;
                k2 %= 7;
                k1 += change;
                k2 += change;
            }
        }
        return count;
    }

    /**
     * 零矩阵
     * https://leetcode.cn/problems/zero-matrix-lcci/description/
     */
    public void setZeroes(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j]==0 && !visited[i][j]) {
                    visited[i][j] = true;
                    clearNumber(matrix, visited, i, j, n, m);
                }
            }
        }
    }

    private void clearNumber(int[][] matrix, boolean[][] visited, int i, int j, int n, int m) {
        for(int k=0; k<m; k++) {
            if(matrix[i][k]!=0) {
                matrix[i][k]=0;
                visited[i][k]=true;
            }
        }
        for(int k=0; k<n; k++) {
            if(matrix[k][j]!=0) {
                matrix[k][j]=0;
                visited[k][j]=true;
            }
        }
    }

    /**
     * 零矩阵
     * https://leetcode.cn/problems/zero-matrix-lcci/description/
     */
    public void setZeroes1(int[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0) {
            return;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        boolean[] zeroRows = new boolean[n];
        boolean[] zeroCols = new boolean[m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j]==0) {
                    zeroRows[i]=true;
                    zeroCols[j]=true;
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(zeroRows[i] || zeroCols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 扑克牌中的顺子
     */
    public boolean isStraight(int[] nums) {
        boolean[] dup = new boolean[14];

        int min = 100;
        int max = -1;
        for(int i=0; i<5; i++) {
            if(nums[i]!=0) {
                if(dup[nums[i]]) {
                    return false;
                }else {
                    dup[nums[i]] = true;
                }
                if(nums[i] < min) {
                    min = nums[i];
                }
                if(nums[i] > max) {
                    max = nums[i];
                }
            }
        }

        return (max-min) < 5;
    }

    /**
     * 跳水板
     * https://leetcode.cn/problems/diving-board-lcci/description/
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k<=0) {
            return new int[]{};
        }

        // 长短相同长度
        if(shorter==longer) {
            return new int[]{k*shorter};
        }

        int index = 0;
        int[] result = new int[k+1];
        for(int i=0; i<=k; i++) {
            result[index] = i*shorter + (k-i)*longer;
            index++;
        }

        return result;
    }

    /**
     * 一次编辑
     * https://leetcode.cn/problems/one-away-lcci/description/
     */
    public boolean oneEditAway(String first, String second) {
        if(first==null && second==null) {
            return true;
        }
        if(first==null || second==null) {
            return false;
        }
        if(first.length()==0 && second.length()==0) {
            return true;
        }
        if(Math.abs(second.length()-first.length())>1) {
            return false;
        }

        // 增删
        if(first.length() > second.length()) {
            return onceUpdateEquals(first, second);
        }else if(first.length() < second.length()) {
            return onceUpdateEquals(second, first);
        }

        // 改
        return onceUpdateEquals(first, second);
    }

    private boolean onceUpdateEquals(String bigger, String lower) {
        boolean flag = true;

        int i=0;
        int j=0;
        boolean once = true;
        while(j<lower.length()) {
            if(bigger.charAt(i)==lower.charAt(j)) {
                i++;
                j++;
            }else {
                if(once) {
                    once = false;
                }else {
                    flag = false;
                    break;
                }
                i++;
                if(bigger.length()==lower.length()) {
                    j++;
                }
            }
        }

        return flag;
    }

    /**
     * 珠玑妙算
     * https://leetcode.cn/problems/master-mind-lcci/description/
     */
    public int[] masterMind(String solution, String guess) {
        int correctGuess = 0;
        int otherGuess = 0;

        Map<Character,Integer> correctMap = new HashMap<>();
        Map<Character,Integer> otherMap = new HashMap<>();

        for(int i=0; i<solution.length(); i++) {
            Character sKey = solution.charAt(i);
            Character gKey = guess.charAt(i);

            if(sKey == gKey) {
                correctGuess++;
                continue;
            }

            if(correctMap.containsKey(sKey)) {
                correctMap.put(sKey, correctMap.get(sKey)+1);
            }else {
                correctMap.put(sKey, 1);
            }

            if(otherMap.containsKey(gKey)) {
                otherMap.put(gKey, otherMap.get(gKey)+1);
            }else {
                otherMap.put(gKey, 1);
            }
        }

        for(Map.Entry<Character,Integer> entry : correctMap.entrySet()) {
            if(otherMap.containsKey(entry.getKey())) {
                otherGuess += otherMap.get(entry.getKey());
            }
        }

        // 先测试完全猜中，同时生成伪猜中个数
        return new int[]{correctGuess, otherGuess};
    }

    /**
     * 井字游戏
     * https://leetcode.cn/problems/tic-tac-toe-lcci/description/
     */
    public String tictactoe(String[] board) {
        // 转换为二维数组
        boolean hasSpace = false;
        int n = board.length;
        char[][] boards = new char[n][n];
        for(int i=0; i<n; i++) {
            boards[i] = board[i].toCharArray();
        }

        boolean determined = false;

        // 检查行
        for(int i=0; i<n; i++) {
            if(boards[i][0]==' ') {
                hasSpace = true;
                continue;
            }
            determined = true;
            for(int j=1; j<n; j++) {
                if(boards[i][j]!=boards[i][0]) {
                    determined = false;
                    break;
                }
            }
            if(determined) {
                return ""+boards[i][0];
            }
        }

        // 检查列
        for(int j=0; j<n; j++) {
            if (boards[0][j] == ' ') {
                hasSpace = true;
                continue;
            }
            determined = true;
            for (int i = 1; i < n; i++) {
                if (boards[i][j] != boards[0][j]) {
                    determined = false;
                    break;
                }
            }
            if (determined) {
                return "" + boards[0][j];
            }
        }

        // 检查对角线 左上右下
        if(boards[0][0]!=' ') {
            int i=1;
            int j=1;
            determined=true;
            while(i<n && j<n) {
                if(boards[i][j]!=boards[0][0]) {
                    determined=false;
                    break;
                }
                i++;
                j++;
            }
            if(determined) {
                return ""+boards[0][0];
            }
        }

        // 检查对角线 左下右上
        if(boards[n-1][0]!=' ') {
            int i=n-2;
            int j=1;
            determined=true;
            while(i>=0 && j<n) {
                if(boards[i][j]!=boards[n-1][0]) {
                    determined=false;
                    break;
                }
                i--;
                j++;
            }
            if(determined) {
                return ""+boards[n-1][0];
            }
        }

        if(hasSpace) {
            return "Pending";
        }

        return "Draw";
    }

    /**
     * 跳跃游戏 基础跳跃 从最开始元素看看途径点的元素 能否到达最后一个位置
     * https://leetcode.cn/problems/jump-game/description/
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for(int i=0; i<nums.length; i++) {
            if(i>maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i+nums[i]);
        }
        return true;
    }

    /**
     * 跳跃游戏
     * 保证能跳跃到最后
     */
    public int canJumpTimes(int[] nums) {
        int times = 0;
        int max = 0;
        int lastIndex = 0;
        for(int i=0; i<nums.length; i++) {
            max = Math.max(max, i+nums[i]);
            if(i == lastIndex) {
                times++;
                lastIndex = max;
            }
        }
        return times;
    }

    /**
     * 跳跃游戏
     * 是否可以到达数组位置为0的地方
     */
    public boolean canReachZero(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        boolean reached = false;
        canReachZeroHandle(arr, visited, start, reached);
        return reached;
    }

    public void canReachZeroHandle(int[] arr, boolean[] visited, int index, boolean reached) {
        if(reached) {
            return;
        }
        if(arr[index] == 0) {
            reached = true;
            return;
        }

        visited[index] = true;

        int left = index - arr[index];
        if(left >=0 && left < arr.length && !visited[left]) {
            canReachZeroHandle(arr, visited, left, reached);
        }

        int right = index + arr[index];
        if(right >=0 && right < arr.length && !visited[right]) {
            canReachZeroHandle(arr, visited, right, reached);
        }
    }

    /**
     * 旋转图像
     * https://leetcode.cn/problems/rotate-image/
     */
    public void rotate(int[][] matrix) {
        // 旋转之后的位置变化，元素位置变化





    }

    /**
     * 螺旋矩阵
     * https://leetcode.cn/problems/spiral-matrix/description/
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

    /**
     * 搜索二维矩阵II
     * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean flag = false;
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return flag;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m-1;
        while(i<n && j>=0) {
            if(matrix[i][j]==target) {
                flag = true;
                break;
            }else if(matrix[i][j]<target) {
                i++;
            }else {
                j--;
            }
        }

        return flag;
    }

}
