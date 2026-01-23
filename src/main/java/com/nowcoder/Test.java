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

}
