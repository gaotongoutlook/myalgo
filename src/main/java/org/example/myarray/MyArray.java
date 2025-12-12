package org.example.myarray;

import java.util.HashMap;

/**
 * 数组相关
 */
public class MyArray {

    /**
     * 数组循环移动K位
     * 给定一个长度为 n 的字符串，将其向右循环移动 k 位，求循环移动之后的字符串
     */
    public String rotate(String s, int k) {
        if(k==0 || s==null || s.length()==k){
            return s;
        }
        if(k > s.length()){
            k = k % s.length();
        }

        int len = s.length();
        char[] cs = new char[len];
        int dest = -1;
        for(int i=0; i<len; i++){
            // 确定是倒数的第几个和他换位置
            char temp = s.charAt(i);
            dest = (i+k) % len;
            cs[dest] = temp;
        }

        return new String(cs);
    }

    /**
     * 最长无重复子数组
     * 可以使用HashMap来存储对应的数字和下标
     */
    public int maxLength (int[] arr) {
        int maxLength = 0;
        if(arr==null || arr.length==0) {
            return maxLength;
        }

        int left = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int right=0; right<arr.length; right++) {
            int key = arr[right];
            if(hashMap.containsKey(key) && hashMap.get(key)>=left) {
                left = hashMap.get(key) + 1;
            }
            hashMap.put(key, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


}
