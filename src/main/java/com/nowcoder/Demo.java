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
        if(s == null || s.length()==0 || t == null || t.length()==0 || s.length() < t.length()) {
            return "";
        }

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

    /**
     *
     */
    public String LCS (String s1, String s2) {
        if(s1==null || s1.length()==0 || s2==null || s2.length()==0) {
            return "";
        }

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        if(dp[n][m]<=0) {
            return "-1";
        }

        return buildLCS(s1, s2, dp);
    }

    private String buildLCS(String s1, String s2, int[][] dp) {
        int i = s1.length();
        int j = s2.length();

        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(s1.charAt(i-1)==s2.charAt(j-1)) {
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            }else{
                j--;
            }
        }

        return sb.reverse().toString();
    }

    /**
     * 编辑距离
     */
    public int minDistance(String word1, String word2) {
        if(word1==null || word1.length()==0) {
            return word2==null ? 0 : word2.length();
        }
        if(word2==null || word2.length()==0) {
            return word1.length();
        }

        int n = word1.length();
        int m = word2.length();
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<=m; i++) {
            dp[0][i] = i;
        }
        for(int i=0; i<=n; i++) {
            dp[i][0] = i;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(w1[i-1]==w2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int result = 0;
        for(int i=0; i<n; i++) {
            if(dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    /**
     * 兑换零钱(一)
     * 可以使用重复钱币
     */
    public int minMoney (int[] arr, int aim) {
        if(arr==null || arr.length==0) {
            return -1;
        }
        Arrays.sort(arr);
        if(aim < arr[0]) {
            return -1;
        }
        int n = arr.length;
        int[] dp = new int[aim+1];
        for(int i=0; i<=aim; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i=1; i<=aim; i++) {
            for(int j=0; j<arr.length; j++) {
                if(i-arr[j]>=0 && dp[i]!=Integer.MAX_VALUE && dp[i-arr[j]]+1<dp[i]) {
                    dp[i] = dp[i-arr[j]]+1;
                }
            }

        }

        if(dp[aim]==Integer.MAX_VALUE) {
            return -1;
        }

        return dp[aim];
    }


}
