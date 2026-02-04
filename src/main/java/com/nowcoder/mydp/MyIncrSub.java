package com.nowcoder.mydp;

/**
 * 最长递增子序列
 */
public class MyIncrSub {

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


}
