package com.nowcoder.mymatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩阵
 */
public class MyMatrix {

    /**
     * 旋转矩阵
     * 螺旋打印矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix==null || matrix.length==0 || matrix[0].length==0) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = m-1;
        while(left<=right && top<=bottom) {
            for(int j=left; j<=right; j++) {
                result.add(matrix[top][j]);
            }
            for(int i=top+1; i<=bottom; i++) {
                result.add(matrix[i][right]);
            }
            if(top != bottom) {
                for(int k=right-1; k>=left; k--) {
                    result.add(matrix[bottom][k]);
                }
            }
            if(left != right) {
                for(int h=bottom-1; h>top; h--) {
                    result.add(matrix[h][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return result;
    }

    /**
     * 旋转矩阵
     * 用反转代替旋转
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先上下翻转
        for(int i=0; i<n/2; i++) {
            for(int j=0; j<n; j++) {
                swap(matrix, i, j, n-i-1, j);
            }
        }

        // 再对角翻转（左上-右下）
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void swap(int[][] matrix, int i, int j, int p, int q) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[p][q];
        matrix[p][q] = tmp;
    }

    /**
     * 旋转数组
     * @param n int整型 数组长度
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     */
    public int[] solve (int n, int m, int[] a) {
        if(a==null || a.length<=1 || m%n==0) {
            return a;
        }
        if(m>n) {
            m = m % n;
        }
        for(int i=0; i<n; i++) {
            int index = i+m>n ? i+m-n : i+m;
        }

        //6-2 = 4 123456 561234 原地翻转 i i+m 5+2-6 1 n-m 0 2 4 6 6+2-7=1 1234567 6712345
        // 偶数 0 和 1 一直交换 直到交换回来
        // 奇数一直交换 直到交换回来
        return null;
    }


}
