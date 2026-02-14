package com.nowcoder;

import java.util.*;

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

    /**
     * 最长公共子序列
     */
    public String longestCommonSubSequence(String text1, String text2) {
        if(text1==null || text2==null || text1.length()==0 || text2.length()==0) {
            return "";
        }

        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];
        // 初始化 什么时候多初始化一个数字
        dp[0][0] = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(text1.charAt(i-1)==text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return buildString(text1, text2, dp);
    }

    private String buildString(String text1, String text2, int[][] dp) {
        int i = text1.length();
        int j = text2.length();

        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(text1.charAt(i-1)==text2.charAt(j-1)) {
                sb.append(text1.charAt(i-1));
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            }else {
                j--;
            }
        }

        return sb.reverse().toString();
    }

    /**
     * 编辑距离
     */
    public int editDistance (String word1, String word2) {
        if(word1==null) {
            return word2==null ? 0 : word2.length();
        }
        if(word2==null) {
            return word1.length();
        }

        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        dp[0][0] = 0;
        for(int i=1; i<=n; i++) {
            dp[i][0] = i;
        }
        for(int j=1; j<=m; j++) {
            dp[0][j] = j;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
                }
            }
        }

        return dp[n][m];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++) {
            for(String word : wordDict) {
                int len = word.length();
                int startup = i-len;
                if(startup>=0 && s.startsWith(word, startup) && dp[startup]) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        int[] dp = new int[n+1];
        for(int i=0; i<=n; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        dp[0] = 0;
        for(int i=1; i<=n; i++) {
            for(String word : wordDict) {
                int len = word.length();
                int startup = i-len;
                if(startup>=0 && s.startsWith(word, startup) && dp[startup]!=Integer.MIN_VALUE) {
                    dp[i] = dp[startup]+1;
                }
            }
        }

        if(dp[n]==0) {
            return result;
        }

        System.out.println(dp[n]);

        return buildWordBreakString(s, wordDict, dp);
    }

    private List<String> buildWordBreakString(String s, List<String> wordDict, int[] dp) {
        List<String> result = new ArrayList<>();

        int n = s.length();
        while(dp[n]>0) {
            StringBuilder sb = new StringBuilder();
            int i = n;
            for(int j=n-1; j>=0; ) {
                if(dp[j]>0) {
                    String word = s.substring(j, i);
                    if(wordDict.contains(word)) {
                        sb.append(word).append(" ");
                    }
                    i = j;
                    dp[j]--;
                }else {
                    j--;
                }
            }
            if(sb.length()>0) {
                sb.deleteCharAt(sb.length()-1);
                result.add(sb.toString().trim());
                dp[n]--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        wordDict.add("applepen");
        wordDict.add("pine");
        wordDict.add("pineapple");
        List<String> result = new Test().wordBreak3(s, wordDict);
        System.out.println();
        for(String r : result) {
            System.out.println(r);
        }
    }

    public List<String> wordBreak3(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict); // 加速查找
        int n = s.length();

        // 1. 预处理：dp[i] 表示 s[0..i-1] 是否可分割（Word Break I）
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 2. 整体不可分割，直接返回空列表
        if (!dp[n]) {
            return new ArrayList<>();
        }

        // 3. 记忆化回溯：key = 起始位置，value = 从该位置开始的所有句子
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(s, 0, dict, dp, memo);
    }

    private List<String> backtrack(String s, int start, Set<String> dict,
                                   boolean[] dp, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add(""); // 递归基，表示一条有效路径
            return res;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (dict.contains(word) && dp[end]) { // dp[end] 保证剩余部分可分割
                List<String> subList = backtrack(s, end, dict, dp, memo);
                for (String sub : subList) {
                    if (sub.isEmpty()) {
                        res.add(word);
                    } else {
                        res.add(word + " " + sub);
                    }
                }
            }
        }

        memo.put(start, res);
        return res;
    }

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        return -1;
    }

    /**
     * 最后一块石头的重量2
     */
    public int lastStoneWeightII(int[] stones) {
        return -1;
    }

    /**
     * 一和零
     */
    public int findMaxForm(String[] strs, int m, int n) {
        return -1;
    }

    /**
     * 无重复字符的最长子串
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {
        return -1;
    }

    /**
     * 长度最小的子数组
     * https://leetcode.cn/problems/2VG8Kg/
     */
    public int minSubArrayLen(int target, int[] nums) {
        return -1;
    }

    /**
     * 滑动窗口最大值
     * https://leetcode.cn/problems/sliding-window-maximum/
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }

    /**
     * 字符串的排列
     * https://leetcode.cn/problems/MPnaiL/
     */
    public boolean checkInclusion(String s1, String s2) {
        return false;
    }

    /**
     * 找到字符串中所有字母异位词
     * https://leetcode.cn/problems/VabMRr/description/
     */
    public List<Integer> findAnagrams(String s, String p) {
        return null;
    }

    /**
     * 最小覆盖子串
     * https://leetcode.cn/problems/M1oyTv/description/
     */
    public String minWindow1(String s, String t) {
        return "";
    }

    /**
     * 替换后的最长重复字符
     * https://leetcode.cn/problems/longest-repeating-character-replacement/description/
     */
    public int characterReplacement(String s, int k) {
        return -1;
    }

    /**
     * 尽可能使字符串相等
     * https://leetcode.cn/problems/get-equal-substrings-within-budget/description/
     */
    public int equalSubstring(String s, String t, int maxCost) {
        return -1;
    }

    /**
     * 删掉一个元素以后全为 1 的最长子数组
     * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
     */
    public int longestSubarray(int[] nums) {
        return -1;
    }

    /**
     * K 个不同整数的子数组
     * https://leetcode.cn/problems/subarrays-with-k-different-integers/
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return -1;
    }

    /**
     * 绝对差不超过限制的最长连续子数组
     * https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
     */
    public int longestSubarray(int[] nums, int limit) {
        return -1;
    }

    /**
     * 爱生气的书店老板
     * https://leetcode.cn/problems/grumpy-bookstore-owner/description/
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        return -1;
    }

    /**
     * 将 x 减到 0 的最小操作数
     * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     */
    public int minOperations(int[] nums, int x) {
        return -1;
    }

    /**
     * 每种字符至少取 K 个
     * https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/description/
     */
    public int takeCharacters(String s, int k) {
        return -1;
    }

    /**
     * 乘积小于K的子数组
     * https://leetcode.cn/problems/ZVAVXX/description/
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        return -1;
    }

}
