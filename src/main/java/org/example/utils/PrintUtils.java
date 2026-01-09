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

}
