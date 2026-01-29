package com.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 双指针
 */
public class MyDemoTwoPointer {

    /**
     * 合并两个有序的数组
     */
    public void merge(int A[], int m, int B[], int n) {
        int i = n-1;
        int j = m-1;

        for(int k = A.length-1 ;k>=0; k--) {
            if(A[i] >= B[j]) {
                A[k] = A[i];
                i--;
            }else {
                A[k] = B[j];
                j--;
            }
        }
    }

    /**
     * 判断是否回文字符串
     */
    public boolean judge (String str) {
        if(str==null || str.length()==0) {
            return false;
        }

        int i = 0;
        int j = str.length()-1;
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /**
     * 反转字符串
     */
    public String solve (String str) {
        if(str==null || str.length()==0) {
            return str;
        }

        int i = 0;
        int j = str.length()-1;
        char[] cs = str.toCharArray();

        while(i < j) {
            char temp = cs[i];
            cs[i] = cs[j];
            cs[j] = temp;

            i++;
            j--;
        }

        return String.valueOf(cs);
    }

    /**
     * 数对和
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length==0) {
            return result;
        }

        Arrays.sort(nums);
        int i=0;
        int j=nums.length-1;
        while(i<j) {
            int sum = nums[i] + nums[j];
            if(sum==target) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                result.add(list);
                i++;
                j--;
            }else if(sum<target) {
                i++;
            }else {
                j--;
            }
        }

        return result;
    }

    /**
     * 移动零 将数组中的0都移动到后边去
     */
    public void moveZeroes(int[] nums) {
        if(nums==null || nums.length==0) {
            return;
        }

        int i=0;
        int j=nums.length-1;
        while(i<j) {
            while(nums[i]!=0) {
                i++;
            }
            while(nums[j]==0) {
                j--;
            }
            // 交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 最小差 给定两个数组求两个数组中元素的差的绝对值最小
     */
    public int smallestDifference(int[] a, int[] b) {
        if(a==null || a.length==0 || b==null || b.length==0) {
            return -1;
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int i=0;
        int j=0;
        long minDiff = Long.MAX_VALUE;
        while(i<a.length && j<b.length) {
            if(a[i] <= b[j]) {
                minDiff = Math.min(a[i]-b[j], minDiff);
                i++;
            }else{
                minDiff = Math.min(b[j]-a[i], minDiff);
                j++;
            }
        }

        return (int)minDiff;
    }

    /**
     * 调整数组顺序使奇数位于偶数前边
     */
    public int[] exchange(int[] nums) {
        if(nums==null || nums.length==0) {
            return nums;
        }

        int i=0;
        int j=nums.length-1;
        while(i<j) {
            while(nums[i]%2==0) {
                i++;
            }
            while(nums[j]%2==1) {
                j--;
            }
            // 交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        return nums;
    }

    /**
     * 颜色分类 将三种颜色分开
     */
    public void sortColors(int[] nums) {

    }

    /**
     * 单词距离 给定两个单词求两个单词在字符串数组中最近的位置
     */
    public int findClosest(String[] words, String word1, String word2) {
        return 0;
    }

    /**
     * 三数之和
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(numbers==null || numbers.length<=2) {
            return result;
        }

        Arrays.sort(numbers);
        for(int i=0; i<numbers.length; i++) {
            int num = numbers[0];
            if(num>0) {
                break;
            }

            int j=i+1;
            int k=numbers.length-1;
            while(j<k) {
                int sum = num + numbers[j] + numbers[k];
                if(sum==0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(numbers[j]);
                    list.add(numbers[k]);
                    result.add(list);
                    j++;
                    k--;
                }else if(sum<0) {
                    j++;
                }else {
                    k--;
                }
            }
        }

        return result;
    }

    /**
     * 两数之和
     */
    public int[] twoSum(int[] numbers, int target) {
        if(numbers==null || numbers.length==0) {
            return numbers;
        }

        int n = numbers.length;
        int[][] nums = new int[n][2];
        for(int i=0; i<n; i++) {
            nums[i][0] = numbers[i];
            nums[i][1] = i;
        }
        Arrays.sort(nums, (a, b) -> a[0]-b[0]);

        int i=0;
        int j=n-1;
        while(i<j) {
            int sum = nums[i][0] + nums[j][0];
            if(sum==target) {
                return new int[]{nums[i][1], nums[j][1]};
            }else if(sum<target) {
                i++;
            }else {
                j--;
            }
        }

        return new int[]{-1, -1};
    }

}
