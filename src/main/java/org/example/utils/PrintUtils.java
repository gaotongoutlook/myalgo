package org.example.utils;

import java.util.List;

public class PrintUtils {

    public static void printString(List<List<Integer>> result) {
        System.out.println("数量: "+ result.size());
        for(List<Integer> list : result) {
            StringBuilder sb = new StringBuilder("[ ");
            for(Integer integer : list) {
                sb.append(integer).append(" , ");
            }
            if(sb.length()>2) {
                sb.replace(sb.length()-2, sb.length(), "]");
            } else {
                sb.append("]");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    public static void printStringResult(List<List<String>> result) {
        System.out.println("数量: "+ result.size());
        for(List<String> list : result) {
            StringBuilder sb = new StringBuilder("[ ");
            for(String integer : list) {
                sb.append(integer).append(" , ");
            }
            if(sb.length()>2) {
                sb.replace(sb.length()-2, sb.length(), "]");
            } else {
                sb.append("]");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    public static void printArr(int[] arr) {
        int n = arr.length;
        System.out.println();
        System.out.print("[");
        for(int i=0; i<n; i++) {
            System.out.print(" "+arr[i]+" ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static void printArr(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        System.out.println();
        System.out.println("[");
        for(int i=0; i<n; i++) {
            System.out.print("[");
            for(int j=0; j<m; j++) {
                System.out.print(" "+arr[i][j]+" ");
            }
            System.out.println("]");
        }
        System.out.print("]");
        System.out.println();
    }

}
