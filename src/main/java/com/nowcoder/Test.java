package com.nowcoder;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public String minWindow(String s, String t) {
        if(s==null || s.length()==0 || t==null || t.length()==0 || s.length()<t.length()) {
            return "";
        }

        int minSize = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = 0;
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> wmap = new HashMap<>();

        for(int i=0; i<t.length(); i++) {
            char key = t.charAt(i);
            tmap.put(key, tmap.getOrDefault(key, 0)+1);
        }

        int n=s.length();
        int left=0;
        int right=0;
        while(right<n) {
            char rc = s.charAt(right);
            if(tmap.containsKey(rc)) {
                wmap.put(rc, wmap.getOrDefault(rc, 0)+1);
            }
            right++;
            while(match(wmap, tmap)) { // while的作用是什么
                if(minSize > right-left) {
                    minSize = right - left;
                    minStart = left;
                    minEnd = right;
                }
                char lc = s.charAt(left);
                if(tmap.containsKey(lc)) {
                    int count = wmap.get(lc);
                    if(count==1) {
                        wmap.remove(lc);
                    }else {
                        wmap.put(lc, count-1);
                    }
                }
                left++;
            }
        }

        return minStart==-1 ? "" : s.substring(minStart, minEnd);
    }

    private boolean match(Map<Character, Integer> wmap, Map<Character, Integer> tmap) {
        for(Map.Entry<Character, Integer> entry : tmap.entrySet()) {
            if(!wmap.containsKey(entry.getKey())) {
                return false;
            }
            if(wmap.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 除自身以外的数组乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int leftProduct = 1;
        for(int i=0; i<n; i++) {
            result[i] *= leftProduct;
            leftProduct *= nums[i];
        }

        int rightProduct = 1;
        for(int i=n-1; i>=0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }

    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];

        int product = 1;
        for(int i=0; i<n; i++) {
            product *= nums[i];
            leftProducts[i] = product;
        }

        product = 1;
        for(int i=n-1; i>=0; i--) {
            product *= nums[i];
            rightProducts[i] = product;
        }

        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            result[i] = 1;
            if(i>0) {
                result[i] *= leftProducts[i];
            }
            if(i<n-1) {
                result[i] *= rightProducts[i];
            }
        }

        return result;
    }

}
