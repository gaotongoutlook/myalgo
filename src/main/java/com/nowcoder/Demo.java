package com.nowcoder;

import java.util.*;

public class Demo {

    /**
     * 和为S的连续正数序列
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();

        int i=1;
        int j=2;
        int sum=i+j;
        while(i<j) {
            if(sum==target) {
                int[] arr = new int[j-i+1];
                for(int k=i; k<=j; k++) {
                    arr[k-i] = k;
                }
                result.add(arr);
                sum -= i;
                i++;
                j++;
            }else if(sum<target) {
                j++;
                sum+=j;
            }else {
                sum-=i;
                i++;
            }
        }

        int n = result.size();
        int[][] resultArr = new int[n][];
        for(int k=0; k<n; k++) {
            resultArr[k] = result.get(k);
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

        int i=0;
        int j=0;
        int maxLen=0;
        Set<Character> set = new HashSet<>();
        while(i<s.length() && j<s.length()) {
            char c = s.charAt(j);
            if(!set.contains(c)) {
                set.add(c);
                j++;
                if(j-i>maxLen) {
                    maxLen=j-i;
                }
                continue;
            }
            while(set.contains(c)) {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return maxLen;
    }

    /**
     * 找到字符串中所有字母的异位词
     * 测试看看是否写的正确
     */
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s==null || s.length()==0 || p==null || p.length()==0 || s.length()<p.length()) {
            return result;
        }

        int m = s.length();
        int n = p.length();
        int i=0;
        int j=0;
        Set<Character> set = new HashSet<>();
        Set<Character> basicSet = new HashSet<>();
        for(char c : p.toCharArray()) {
            basicSet.add(c);
        }

        while(i<m && j<m) {
            char c = s.charAt(j);
            if(!basicSet.contains(c)) {
                j++;
                i = j;
                set.clear();
                continue;
            }

            if(!set.contains(c) && set.size()<n) {
                set.add(c);

                if(set.size()==n) {
                    // 表示当前正好凑满了
                    result.add(i);
                    set.remove(s.charAt(i));
                    i++;
                }
                j++;
            }else if(set.contains(c) && set.size()<n) {
                // 将重复这个清除出去
                while(set.contains(c)) {
                    set.remove(s.charAt(i));
                    i++;
                }
            }
        }

        return result;
    }




















    /**
     * 找到字符串中所有字母的异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s==null || s.length()==0 || p==null || p.length()==0 || s.length()<p.length()) {
            return result;
        }

        // 频率数组
        int[] needs = new int[26];
        for(int i=0; i<p.length(); i++) {
            needs[p.charAt(i)-'a']++;
        }

        int[] matched = new int[26];
        int sl = s.length();
        int pl = p.length();
        int start = 0;
        int end = 0;
        while(end < pl) {
            matched[s.charAt(end)-'a']++;
            end++;
        }
        if(same(needs, matched)) {
            result.add(start);
        }

        while(end<sl && start<sl) {
            // 开始滑动 已经移动
            matched[s.charAt(start)-'a']--;
            matched[s.charAt(end)-'a']++;
            start++;
            end--;
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
            char key = t.charAt(i);
            if(tmap.containsKey(key)) {
                count += tmap.get(key);
            }
            tmap.put(key, count);
        }

        int n=s.length();
        int left=0;
        int right=0;
        while(right<n) {
            while(!match(wmap, tmap)) {
                if(right>=n) {
                    break;
                }
                char c = s.charAt(right);
                if(tmap.containsKey(c)) {
                    int count = 1;
                    if(wmap.containsKey(c)) {
                        count += wmap.get(c);
                    }
                    wmap.put(c, count);
                }
                right++;
            }
            if(match(wmap, tmap)) {
                if(minSize > right-left+1) {
                    minSize = right-left+1;
                    minStart = left;
                    minEnd = right;
                }
                char c = s.charAt(left);
                // 窗口滑动
                if(tmap.containsKey(c)) {
                    int count = wmap.get(c);
                    if(count-1==0) {
                        wmap.remove(c);
                    }else {
                        wmap.put(c, count-1);
                    }
                }
                left++;
            }
        }

        if(minSize>0) {
            return s.substring(minStart, minEnd+1);
        }

        return "";
    }

    /**
     * tmap 模式串
     * wmap 滑动窗口
     */
    private boolean match(Map<Character, Integer> wmap, Map<Character, Integer> tmap) {
        for(Map.Entry<Character, Integer> entry : tmap.entrySet()) {
            Character key = entry.getKey();
            // 字符对不上
            if(!wmap.containsKey(key)) {
                return false;
            }
            // 数量对不上
            if(wmap.get(key) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int minSize = Integer.MAX_VALUE;
        int minStart = -1;

        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> wmap = new HashMap<>();

        // 统计t中字符频率
        for (char c : t.toCharArray()) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }

        int n = s.length();
        int left = 0, right = 0;

        while (right < n) {
            // 扩大右边界
            char rc = s.charAt(right);

            // 只处理t中存在的字符
            if (tmap.containsKey(rc)) {
                wmap.put(rc, wmap.getOrDefault(rc, 0) + 1);
            }

            right++;

            // 当窗口满足条件时，尝试收缩左边界
            while (match(wmap, tmap)) {
                // 更新最小窗口
                if (right - left < minSize) {
                    minSize = right - left;
                    minStart = left;
                }

                // 收缩左边界
                char lc = s.charAt(left);
                if (tmap.containsKey(lc)) {
                    int count = wmap.get(lc);
                    if (count == 1) {
                        wmap.remove(lc);
                    } else {
                        wmap.put(lc, count - 1);
                    }
                }
                left++;
            }
        }

        return minStart == -1 ? "" : s.substring(minStart, minStart + minSize);
    }

}
