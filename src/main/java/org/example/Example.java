package org.example;

import org.example.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {

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

    private static final Example example = new Example();

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

        // 4.全排列
        System.out.println("---------- 4.全排列 ----------");
        nums = new int[]{1,2,3};
        result = example.four(nums);
        PrintUtils.printString(result);
        result.clear();

        // 5.全排列（重复数字）
        System.out.println("---------- 5.全排列（重复数字） ----------");
        nums = new int[]{1,2,2};
        result = example.five(nums);
        PrintUtils.printString(result);
        result.clear();
    }

    public List<List<Integer>> one(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<nums.length; i++) {
            nums[i] = i+1;
        }
        oneBackTrace(nums, 0, k, path, result);
        return result;
    }

    private void oneBackTrace(int[] nums, int start, int k, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            oneBackTrace(nums, i+1, k, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> two(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        twoBackTrace(nums, 0, path, result);
        return result;
    }

    private void twoBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for(int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            twoBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> three(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        threeBackTrace(nums, 0, path, result);
        return result;
    }

    private void threeBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for(int i=start; i<nums.length; i++) {
            if(i>start && nums[i]==nums[i-1]) {
                continue;
            }

            path.add(nums[i]);
            threeBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> four(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        fourBackTrace(nums, 0, path, result);
        return result;
    }

    private void fourBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            fourBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> five(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        //fiveBackTrace(nums, 0, path, result);
        return result;
    }

    private void fiveBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            if(i>start && nums[i]==nums[i-1]) {
                continue;
            }

            path.add(nums[i]);
            fiveBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }


}
