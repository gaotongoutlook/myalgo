package com.nowcoder.mydp;

/**
 * 匹配模型
 */
public class MyMatch {

    public static void main(String[] args) {
        String text1 = "1A2C3D4B56";
        String text2 = "B1D23A456A";
        int result = new MyMatch().longestCommonSubSequence(text1, text2);
        System.out.println(result);
    }

    public int longestCommonSubSequence1(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return -1;
        }

        int m = text1.length();
        int n = text2.length();

        // 创建DP表
        int[][] dp = new int[m + 1][n + 1];

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 回溯构建LCS字符串
        return dp[m][n];
    }

    /**
     * 最长公共子序列
     */
    public String longestCommonSubSequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return "";
        }

        int m = text1.length();
        int n = text2.length();

        // 创建DP表
        int[][] dp = new int[m + 1][n + 1];

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 回溯构建LCS字符串
        return buildLCSString(text1, text2, dp);
    }

    private String buildLCSString(String text1, String text2, int[][] dp) {
        int i = text1.length();
        int j = text2.length();
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                // 当前字符属于LCS
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 向上移动
                i--;
            } else {
                // 向左移动
                j--;
            }
        }

        return lcs.reverse().toString();
    }

    public int longestCommonSubSequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }
        for(int j=0; j<=m; j++) {
            dp[0][j] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(t1[i-1]==t2[j-1]) {
                    sb.append(t1[i-1]);
                    dp[i][j] = max3(dp[i-1][j-1]+1, dp[i-1][j], dp[i][j-1]);
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(sb.toString());

        return dp[n][m];
    }

    private int max3(int a, int b, int c) {
        int maxv = a;
        if(maxv < b) {
            maxv = b;
        }
        if(maxv < c) {
            maxv = c;
        }
        return maxv;
    }

    /**
     * 编辑距离
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if(n==0 || m==0) {
            return m==0 ? n : m;
        }

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[][] dp = new int[n+1][m+1];
        for(int j=0; j<=m; j++) {
            dp[0][j] = j; // 把字符全部清除为空需要多少次操作
        }
        for(int i=0; i<=n; i++) {
            dp[i][0] = i; // 把字符全部清除为空需要多少次操作
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(w1[i-1]==w2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]; // 字符相同不需要操作
                }else {
                    // 字符不同 增加 删除 或者替换 转换到当前操作
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }

        return dp[n][m];
    }

    private int min3(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


}
