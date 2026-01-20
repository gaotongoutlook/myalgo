package com.nowcoder.myhash;

import java.util.ArrayList;
import java.util.Arrays;

public class MyHash {

    /**
     * 数组中出现一次的两个数字 异或之后找到两个数字位不同的一个位置
     */
    public int[] FindNumsAppearOnce (int[] nums) {
        if(nums==null || nums.length==0) {
            return new int[]{};
        }

        int xor = nums[0];
        for(int i=1; i<nums.length; i++) {
            xor ^= nums[i];
        }

        // 找到异或和中间为1的位
        int mask = 1;
        while((xor & mask) == 0) {
            mask <<= 1;
        }

        int num1 = 0;
        int num2 = 0;
        for(int i=0; i<nums.length; i++) {
            if((nums[i] & mask) == 0) {
                num1 ^= nums[i];
            }else {
                num2 ^= nums[i];
            }
        }

        if(num1 > num2) {
            return new int[]{num2, num1};
        }

        return new int[]{num1, num2};
    }

    /**
     * 数组中找出超过一半以上的数字
     */
    public int MoreThanHalfNum_Solution (int[] numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length/2];
    }

    /**
     * 确实的第一个正整数
     */
    public int minNumberDisappeared (int[] nums) {
        if(nums==null || nums.length==0) {
            return 1;
        }
        Arrays.sort(nums);
        int number = 1;
        int left = 0;
        if(nums[left] <= 0) {
            while(nums[left] <= 0) {
                left++;
            }
        }
        // 第一种情况 nums[left]>number 不成立直接返回number
        // 第二种开始相等中间不等 也满足
        // 第三种一直相等 也满足
        while(left<nums.length && nums[left]==number) {
            left++;
            number++;
        }

        return number;
    }

    /**
     * 三数之和
     */
    public ArrayList<ArrayList<Integer>> threeSum (int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length<3) {
            return result;
        }
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++) {
            if(nums[i]>0) {
                break;
            }
            if(i>0 && nums[i-1]==nums[i]) {
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    while(left<right && nums[left]==nums[left+1]) {
                        left++;
                    }
                    while(left<right && nums[right]==nums[right-1]) {
                        right--;
                    }

                    left++;
                    right--;
                }else if(sum < 0) {
                    left++;
                }else {
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * 两数之和
     */
    public int[] twoSum(int[] numbers, int target) {
        if(numbers==null || numbers.length<2) {
            return new int[]{-1, -1};
        }

        int n = numbers.length;
        int[][] nums = new int[n][2];
        for(int i=0; i<n; i++) {
            nums[i][0] = numbers[i];
            nums[i][1] = i;
        }

        Arrays.sort(nums, (a, b) -> a[0] - b[0]);
        for(int left=0; left<n; left++) {
            for(int right=n-1; right>left; right--) {
                if(nums[left][0]+nums[right][0]==target) {
                    if(nums[left][1] > nums[right][1]) {
                        return new int[]{nums[right][1]+1, nums[left][1]+1};
                    }
                    return new int[]{nums[left][1]+1, nums[right][1]+1};
                }
            }
        }

        return new int[]{-1, -1};
    }

}
