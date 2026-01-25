package com.nowcoder.mytwopointer;

/**
 * 前缀后缀统计
 */
public class MyPrefixSuffix {

    /**
     * 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] max = new int[n];
        int curMax = 0;
        for(int i=n-1; i>=0; i--) {
            max[i] = curMax;
            if(prices[i] > curMax) {
                curMax = prices[i];
            }
        }
        int result = 0;
        for(int i=0; i<n; i++) {
            if(result < max[i] - prices[i]) {
                result = max[i] - prices[i];
            }
        }
        return result;
    }

    /**
     * 除自身以外数组的乘积
     */
    public int[] productExceptSelf(int[] nums) {
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
            if(i-1 >= 0) {
                result[i] *= leftProducts[i-1];
            }
            if(i+1 < n) {
                result[i] *= rightProducts[i+1];
            }
        }
        return result;
    }

    /**
     * 除自身以外数组的乘积1
     */
    public int[] productExceptSelf1(int[] nums) {
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

    /**
     * 翻转数位
     */
    public int reverseBits(int num) {
        if(num == 0) {
            return 1;
        }

        int[] nums = new int[32];
        for(int i=0; i<32; i++) {
            nums[i] = (num&1);
            num >>= 1;
        }

        int[] leftOneCounts = new int[32];
        int count = 0;
        for(int i=0; i<32; i++) {
            leftOneCounts[i] = count;
            if(nums[i]==1) {
                count++;
            }else {
                count = 0;
            }
        }

        int[] rightOneCounts = new int[32];
        count = 0;
        for(int i=31; i>=0; i--) {
            rightOneCounts[i] = count;
            if(nums[i]==1) {
                count++;
            }else {
                count = 0;
            }
        }

        int ret = 0;
        for(int i=0; i<32; i++) {
            if(ret < leftOneCounts[i] + rightOneCounts[i] + 1) {
                ret = leftOneCounts[i] + rightOneCounts[i] + 1;
            }
        }

        return ret;
    }

    /**
     * 接雨水
     */
    public int trap(int[] height) {
        int n = height.length;

        int max = 0;
        int[] leftMax = new int[n];
        for(int i=0; i<n; i++) {
            leftMax[i] = Math.max(max, height[i]);
            max = leftMax[i];
        }

        max = 0;
        int[] rightMax = new int[n];
        for(int i=n-1; i>=0; i--) {
            rightMax[i] = Math.max(max, height[i]);
            max = rightMax[i];
        }

        int result = 0;
        for(int i=1; i<n-1; i++) {
            result += Math.min(leftMax[i], rightMax[i]-height[i]);
        }

        return result;
    }

    /**
     * 最大子序和
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int sum = nums[0];

        for(int i=1; i<n; i++) {
            if(sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            if(sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    /**
     * 最大子序和1
     */
    public int maxSubArray1(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        int[] sum = new int[nums.length];
        int[] max = new int[nums.length];
        int curSum = 0;
        for(int i=0; i<nums.length; i++) {
            curSum += nums[i];
            sum[i] = curSum;
        }

        int curMax = Integer.MIN_VALUE;
        for(int i=sum.length-1; i>=0; i--) {
            if(curMax < sum[i]) {
                curMax = sum[i];
                max[i] = curMax;
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            if(i==0 && result<max[0]) {
                result = max[0];
            }
            if(i!=0 && result<max[i]-sum[i-1]) {
                result = max[i] - sum[i-1];
            }
        }

        return result;
    }

}
