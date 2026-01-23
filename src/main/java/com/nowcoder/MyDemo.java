package com.nowcoder;

import org.example.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDemo {

    /**
     * 1.组合
     * 2.子集
     * 3.子集（重复数字）
     * 4.全排列
     * 5.全排列（重复数字）
     * 6.组合总和
     * 7.组合总和（多次使用）
     * 8.组合总和（重复数字）
     */

    private static final MyDemo example = new MyDemo();

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[]{1,2,3,4};

        // 1.组合
        System.out.println("---------- 1.组合 ----------");
        int n = 4;
        int k = 2;
        result = example.one(n, k);
        PrintUtils.printString(result);
        result.clear();

        // 2.子集
        System.out.println("---------- 2.子集 ----------");
        nums = new int[]{1,2,3};
        result = example.two(nums);
        PrintUtils.printString(result);
        result.clear();

        // 3.子集（重复数字）
        System.out.println("---------- 3.子集（重复数字） ----------");
        nums = new int[]{1,2,2};
        result = example.three(nums);
        PrintUtils.printString(result);
        result.clear();
    }

    /**
     * 组合 存在n个数按照k个数一组的组合有多少
     */
    public List<List<Integer>> one(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        oneBackTrace(n, k, 0, path, result);

        return result;
    }

    private void oneBackTrace(int n, int k, int step, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=step; i<n; i++) {
            path.add(i+1);
            oneBackTrace(n, k, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 子集
     */
    public List<List<Integer>> two(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        twoBackTrace(nums, 0, path, result);

        return result;
    }

    private void twoBackTrace(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == step) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 找到原因为什么这么写 我写的就不对呢 画图分析
        // result.add(new ArrayList<>(path));

        for(int i=step; i<nums.length; i++) {
            path.add(nums[i]);
            twoBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }

    }

    /**
     * 子集（重复数字）
     */
    private List<List<Integer>> three(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);
        threeBackTrace(nums, used, 0, path, result);

        return result;
    }

    private void threeBackTrace(int[] nums, boolean[] used, int step, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        // 子集和组合的区别 整清楚
        for(int i=step; i<nums.length; i++) {
            if(i>step && nums[i-1]==nums[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i]=true;
            threeBackTrace(nums, used, i+1, path, result);
            path.remove(path.size()-1);
            used[i]=false;
        }
    }


}
