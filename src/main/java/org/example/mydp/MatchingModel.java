package org.example.mydp;

/**
 * 匹配模型
 */
public class MatchingModel {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        int[][] dp = new int[n+1][m+1];
        for(int j=0; j<=m; j++) {
            dp[0][j] = 0;
        }
        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(t1[i-1] == t2[j-1]) {
                    // 三种比较最大值，匹配了增加一，不匹配的两种情况，取最大值
                    dp[i][j] = Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]+1);
                } else {
                    // 没有匹配*
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

}
