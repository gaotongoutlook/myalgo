package com.nowcoder.mytwopointer;

import java.util.*;

public class MyWindows {

    /**
     * 和为S的连续正数序列
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();

        int p=1;
        int q=2;
        int sum=3;
        while(p < q) {
            if(sum == target) {
                int[] arr = new int[q-p+1];
                for(int i=p; i<=q; i++) {
                    arr[i-p] = i;
                }
                result.add(arr);
                sum-=p;
                p++;
                q++;
            }else if(sum>target) {
                sum-=p;
                p++;
            }else {
                q++;
                sum+=q;
            }
        }

        int[][] resultArr = new int[result.size()][];
        for(int i=0; i<result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }

    /**
     * 最长不含重复字符的子字符串
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n==0) {
            return 0;
        }

        int p=0;
        int q=0;
        int maxLen=0;
        Set<Character> set = new HashSet<>();
        while(q < n) {
            char c = s.charAt(q);
            if(!set.contains(c)) {
                set.add(c);
                q++;
                if(q-p > maxLen) {
                    maxLen = q-p;
                }
                continue;
            }
            while(set.contains(c)) {
                // 重复数字之前存储到集合中的字母都没用了
                set.remove(s.charAt(p));
                p++;
            }
        }

        return maxLen;
    }

    /**
     * 找到字符串中所有字母的异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if(m>n) {
            return result;
        }
        // 初始化频率数组
        int[] needs = new int[26];
        for(int i=0; i<m; i++) {
            needs[p.charAt(i)-'a']++;
        }
        // 记录当前窗口中每个字母的出现次数
        int[] matched = new int[26];

        int start=0;
        int end=0;
        // 构建第一个长度为m的窗口
        while(end<m) {
            matched[s.charAt(end)-'a']++;
            end++;
        }
        if(same(needs, matched)) {
            result.add(start);
        }
        while(end<n && start<n) {
            matched[s.charAt(start)-'a']--;
            matched[s.charAt(end)-'a']++;
            start++;
            end++;
            if(same(needs, matched)) {
                result.add(start);
            }
        }

        return result;
    }

    private boolean same(int[] needs, int[] matched) {
        for(int i=0; i<needs.length; i++) {
            if(needs[i] != matched[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        int minSize = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = -1;
        Map<Character, Integer> tmap = new HashMap<>(); // 模式串
        Map<Character, Integer> wmap = new HashMap<>(); // 滑动窗口

        for(int i=0; i<t.length(); i++) {
            int count = 1;
            if(tmap.containsKey(t.charAt(i))) {
                count += tmap.get(t.charAt(i));
            }
            tmap.put(t.charAt(i), count);
        }

        int n=s.length();
        int l=0;
        int r=-1;
        while(r<n) {
            while(!match(wmap, tmap)) {
                r++;
                if(r+1 > n-1) {
                    break;
                }
                char c = s.charAt(r);
                if(tmap.containsKey(c)) {
                    int count = 1;
                    if(wmap.containsKey(c)) {
                        count += wmap.get(c);
                    }
                    wmap.put(c, count);
                }
            }
            if(match(wmap, tmap)) {
                if(minSize > r-l+1) {
                    minSize = r-l+1;
                    minStart = l;
                    minEnd = r;
                }
                char c = s.charAt(l);
                if(tmap.containsKey(c)) {
                    int count = wmap.get(c);
                    if(count-1==0) {
                        wmap.remove(c);
                    }else {
                        wmap.put(c, count-1);
                    }
                }
            }
        }

        if(minStart==-1) {
            return "";
        }
        return s.substring(minStart, minEnd+1);
    }

    private boolean match(Map<Character, Integer> wmap, Map<Character, Integer> tmap) {
        for(Map.Entry<Character, Integer> entry : tmap.entrySet()) {
            Character key = entry.getKey();
            if(!wmap.containsKey(key)) {
                return false;
            }
            if(wmap.get(key) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }




}
