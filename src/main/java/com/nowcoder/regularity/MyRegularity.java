package com.nowcoder.regularity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规律
 */
public class MyRegularity {

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
     * 扑克牌中的顺子
     */

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
        if(first==null || first.length()==0) {
            return false;
        }
        if(second==null || Math.abs(second.length()-first.length())>1) {
            return false;
        }

        // 删
        if(first.length()-1==second.length() && first.contains(second)) {
            return true;
        }
        // 增
        if(first.length()+1==second.length() && second.contains(first)) {
            return true;
        }

        // 改
        boolean flag = true;
        int i=0;
        int j=0;
        while(i<first.length()) {
            if(first.charAt(i)!=second.charAt(j)) {
                if(!flag) {
                    return false;
                }
                flag = false;
            }
            i++;
            j++;
        }

        return true;
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
        int n = board.length;

        char succ = ' ';
        int count = 0;
        boolean space = false;

        // 横行位置 竖行位置 两个对角线
        for(int i=0; i<n; i++) {
            char ch = board[i].charAt(0);
            count = 1;
            for(int j=1; j<n; j++) {
                if(ch == board[i].charAt(j)) {
                    count++;
                }
                if(ch==' ' && !space) {
                    space = true;
                }
            }
            if(count==4) {
                succ = ch;
                break;
            }else {
                count=1;
            }
        }

        if(succ!=' ') {
            return String.valueOf(succ);
        }

        if(space) {
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
        return false;
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
        return false;
    }

    /**
     *
     */

}
