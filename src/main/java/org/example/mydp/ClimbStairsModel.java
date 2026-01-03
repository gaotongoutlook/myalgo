package org.example.mydp;

import com.sun.deploy.panel.ITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬楼梯模型
 *
 */
public class ClimbStairsModel {

    /**
     * 爬楼梯 普通爬楼梯 一次爬一步或者两步
     */
    public int climbStairs(int n) {
        if(n <= 2) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * 零钱兑换 完全背包问题
     */
    public int coinChange(int[] coins, int amount) {
        int k = coins.length;
        int[] dp = new int[amount+1];
        for(int i=0; i<=amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i=1; i<=amount; i++) {
            for(int j=0; j<k; j++) {
                if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE && dp[i-coins[j]]+1<dp[i]) {
                    dp[i] = dp[i-coins[j]]+1;
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[amount];
    }

    /**
     * 零钱兑换 总共有多少种兑换方式
     */
    public int coinChangeTotal(int[] coins, int amount) {
        int k = coins.length;
        int[] dp = new int[amount+1];
        for(int i=0; i<=amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        for(int i=1; i<=amount; i++) {
            for(int j=0; j<k; j++) {
                if(i-coins[j]>=0 && dp[i-coins[j]]!=Integer.MAX_VALUE) {
                    dp[i] += dp[i-coins[j]];
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[amount];
    }

    /**
     * 剪绳子
     */
    public int cuttingRope(int n) {
        if(n==1 || n==2) {
            return 1;
        }
        if(n==3) {
            return 2;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=i; j++) {
                if(dp[i] < j*dp[i-j]) {
                    dp[i] = j*dp[i-j];
                }
            }
        }

        return dp[n];
    }

    /**
     * 把数字翻译成字符串 多少种不同的翻译方法
     */
    public int translateNum(int num) {
        if(num<=9) {
            return 1;
        }

        List<Integer> digitlist = new ArrayList<>();
        while (num!=0) {
            digitlist.add(num%10); // 数字从后边往前边添加
            num /= 10;
        }

        int n = digitlist.size();
        int[] digits = new int[n];
        for(int i=0; i<n; i++) {
            digits[i] = digitlist.get(n-i-1); // 从后边往前边复制
        }

        int[] dp = new int[n];
        dp[0] = 1;
        if(isValidNum(digits[0], digits[1])) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }
        for(int i=2; i<n; i++) {
            dp[i] = dp[i-1];
            if(isValidNum(digits[i-1], digits[i])) {
                dp[i] += digits[i-2];
            }
        }

        return dp[n-1];
    }

    private boolean isValidNum(int a, int b) {
        return (a==1) || (a==2 && b>=0 && b<=5);
    }

    /**
     * 单词拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int m = wordDict.size();

        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++) {
            for(String word : wordDict) {
                int len = word.length();
                int startp = i - len;
                if(startp>=0 && s.startsWith(word, startp) && dp[i-len]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }


}
