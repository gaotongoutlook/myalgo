package org.example.mydp;

/**
 * 股票买卖
 */
public class StockTradingModel {

    /**
     * 买卖股票的最佳时机含手续费
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 第i天后持有股票，手中利润的最大值
        dp[0][0] = -prices[0];
        // 第i天后不持有股票，手中利润的最大值
        dp[0][1] = 0;
        for(int i=1; i<n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);  // 保持不操作或者买入股票
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]+prices[i]-fee); //保持不操作或者卖出股票
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    /**
     * 最佳买卖股票时机含冷冻期
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][4];

        // dp[i][0]表示第i天持有股票时的利润
        // dp[i][1]表示第i天持有股票时的利润（当天刚卖掉）
        // dp[i][2]表示第i天持有股票时的利润（冷冻期），昨天刚卖了股票
        // dp[i][1]表示第i天持有股票时的利润（非冷冻期），昨天也没持有股票
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        for(int i=1; i<n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][2]-prices[i], dp[i-1][3]-prices[i])); //前一天一样持有股票，或者前一天刚卖股票（冷冻期）或者前一天为非冷冻期，今天买入股票
            dp[i][1] = dp[i-1][0]+prices[i]; //今天刚卖掉股票获益
            dp[i][3] = dp[i-1][1]; // 昨天刚卖掉股票，今天不操作，利润为前一天卖到股票的利润
            dp[i][3] = Math.max(dp[i-1][2], dp[i-1][3]); // 昨天也是非冷冻期，或者昨天为冷冻期
        }

        return Math.max(Math.max(dp[n-1][0], dp[n-1][1]), Math.max(dp[n-1][2], dp[n-1][3]));
    }

}

