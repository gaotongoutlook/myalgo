package org.example.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯
 */
public class MyTraceBack {


    public int knapasck(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i-1][j]) {
                   dp[i][j] = true;
                    if (j+weight[i] <= w) {
                        dp[i][j+weight[i]] = true;
                    }
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]){
                return i;
            }
        }

        return 0;
    }

    /**
     * 物品的总价值最大
     * @return
     */
    private int maxV = Integer.MIN_VALUE;
    private int[] weight = new int[]{};
    private int[] value = new int[]{};
    int n = 5;
    private int w = 9;

    public void bigValue(int i, int cw, int cv) {
        if(cw == w || i == n) {
            if(cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 不选择第i个物品
        bigValue(i+1, cw, cv);
        if(weight[i] + cw <= w) {
            // 选择第i个物品
            bigValue(i+1, cw+weight[i], cv+value[i]);
        }
    }



    public int knapasck2(int[] weight, int[] value, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; i++) {
            for(int j=0; j<=w; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = value[0];
        }

        for(int i=1; i<n; i++) {
            for(int j=0; j<=w; j++) {
                if(dp[i][j] == Integer.MIN_VALUE) {
                    continue;
                }
                // 不选择
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
                // 选择
                if(j+weight[i]<=w) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + value[i]);
                }
            }
        }

        // 在结果集中求出最大价值的数
        int res = Integer.MIN_VALUE;
        for(int i=w; i>=0; i++) {
            if(dp[n-1][w] > res) {
                res = dp[n-1][w];
            }
        }

        return res;
    }

    public int knapsack4(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        dp[0][0] = 1;
        if(weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]];
                }
            }

        }

        return dp[n-1][w];
    }

    public int knapsack3(int[] weight, int n, int w) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        if(weight[0] <= w) {
            dp[0][weight[0]] = 1;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(j-weight[i] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-weight[i]]+1);
                }
            }
        }

        if(dp[n-1][w] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[n-1][w];
    }

    public boolean knapsack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n-1][w];
    }

    public int knapsack1(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[n][w+1];
        dp[0][0] = true;
        if(weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        for(int i=1; i<n; ++i) {
            for(int j=0; j<=w; ++j) {
                if(dp[i-1][j] || (j-weight[i]>=0 && dp[i-1][j-weight[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i=w; i>=0; i--) {
            if(dp[n-1][i]) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new MyTraceBack().combine(4, 2);
        System.out.println();
        for (List<Integer> temp : result) {
            for (Integer num : temp) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backtrace(list, new ArrayList<>(), 1, n, k);
        return list;
    }

    public void backtrace(List<List<Integer>> list, List<Integer> temp, int step, int n, int k) {
        // 结束条件
        if(temp.size() == k) {
            List<Integer> result = new ArrayList<>(temp);
            list.add(result);
            return;
        }

        for(int i=step; i<=n; i++) {
            if(temp.contains(i)){
                continue;
            }
            // 选择
            temp.add(i);
            // 回溯
            backtrace(list, temp, n, step+1, k);
            // 不选择
            temp.remove(temp.size()-1);
        }
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return null;
    }































}
