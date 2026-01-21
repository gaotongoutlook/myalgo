package com.nowcoder.mytwopointer;

import org.example.pojo.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTwoPointer {

    /**
     * 合并两个有序的数组
     */
    public void merge(int A[], int m, int B[], int n) {
        if(n==0) {
            return;
        }
        if(m==0) {
            int index = 0;
            while(index < n) {
                A[index] = B[index];
                index++;
            }
        }
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        while(k>=0) {
            if(i < 0) {
                while(k>=0 && j>=0) {
                    A[k--] = B[j--];
                }
                continue;
            }
            if(j<0) {
                while(k>=0 && i>=0) {
                    A[k--] = A[i--];
                }
                continue;
            }
            if(A[i] >= B[j]) {
                A[k--] = A[i--];
            }else {
                A[k--] = B[j--];
            }
        }
    }

    /**
     * 判断是否回文字符串
     */
    public boolean judge (String str) {
        boolean flag = true;
        if(str==null || str.length()==0) {
            return !flag;
        }

        int i = 0;
        int j = str.length()-1;
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) {
                flag = false;
                break;
            }
            i++;
            j--;
        }

        return flag;
    }

    public ArrayList<Interval> merge (ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        if(intervals==null || intervals.size()==0) {
            return result;
        }
        // 排序
        intervals.sort((a, b) -> a.start - b.start);

        Interval current = intervals.get(0);
        result.add(current);

        for(int i=1; i<intervals.size(); i++) {
            Interval next = intervals.get(i);
            if(next.start <= current.end) {
                current.end = Math.max(current.end, next.end);
            }else {
                current = next;
                result.add(current);
            }
        }

        return result;
    }

    /**
     * 反转字符串
     */
    public String solve (String str) {
        if(str==null || str.length()==0) {
            return str;
        }

        int i=0;
        int j = str.length()-1;
        char[] cs = str.toCharArray();
        while(i<j) {
            char temp = cs[i];
            cs[i] = cs[j];
            cs[j] = temp;
            i++;
            j--;
        }

        return new String(cs);
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
            if(target == sum) {
                result.add(Arrays.asList(nums[i], nums[j]));
                i++;
                j--;
            }else if(sum < target) {
                i++;
            }else {
                j--;
            }
        }

        return result;
    }

    /**
     * 移动零
     */
    public void moveZeroes(int[] nums) {
        if(nums==null || nums.length==0) {
            return;
        }
        int i=0; // 非0
        int j=0; // 0
        while(j<nums.length) {
            if(nums[j]!=0){
                j++;
                continue;
            }
            if(nums[i]==0){
                i++;
                continue;
            }
            if(i<j) {
                i++;
            }else {
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void moveZeroes1(int[] nums) {
        int p = -1;
        int q = 0;
        while(q < nums.length) {
            if(nums[q] == 0) {
                q++;
                continue;
            }
            if(nums[q] != 0) {
                swap(nums, p+1, q);
                p++;
                q++;
            }
        }
    }

    /**
     * 最小差
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        int m = b.length;
        long minRet = Long.MAX_VALUE;
        int i=0;
        int j=0;
        while(i<n && j<m) {
            if(a[i] >= b[j]) {
                minRet = Math.min(minRet, (long)a[i]-b[j]);
                j++;
            }else {
                minRet = Math.min(minRet, (long)b[j]-a[i]);
                i++;
            }
        }

        return (int) minRet;
    }

}
