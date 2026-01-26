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

        // 6.组合总和
        System.out.println("---------- 6.组合总和 ----------");
        nums = new int[]{2,4,5,6,7};
        int target = 9;
        result = example.six(nums, target);
        PrintUtils.printString(result);
        result.clear();

        // 7.组合总和（多次使用）
        System.out.println("---------- 7.组合总和（多次使用） ----------");
        nums = new int[]{2,4,5,6,7};
        target = 9;
        result = example.seven(nums, target);
        PrintUtils.printString(result);
        result.clear();

        // 8.组合总和（重复数字）
        System.out.println("---------- 8.组合总和（重复数字） ----------");
        nums = new int[]{1,2,2,4,4,5,6,7};
        target = 9;
        Arrays.sort(nums);
        result = example.eight(nums, target);
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
        result.add(new ArrayList<>(path));

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

        Arrays.sort(nums);
        threeBackTrace(nums, 0, path, result);

        return result;
    }

    private void threeBackTrace(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        // 子集和组合的区别 整清楚
        for(int i=step; i<nums.length; i++) {
            if(i>step && nums[i-1]==nums[i]) {
                continue;
            }
            path.add(nums[i]);
            threeBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 全排列
     */
    public List<List<Integer>> four(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        fourBackTrace(nums, 0, path, result);

        return result;
    }

    private void fourBackTrace(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        if(step == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            fourBackTrace(nums, step+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 全排列(重复数字)
     */
    public List<List<Integer>> five(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);
        fiveBackTrace(nums, used, 0, path, result);

        return result;
    }

    private void fiveBackTrace(int[] nums, boolean[] used, int step, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) { //这儿找下原因是什么
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(used[i]) {
                continue;
            }
            if(i>0 && nums[i]==nums[i-1] && !used[i-1]) { // 此处注意下为什么这样
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            fiveBackTrace(nums, used, step+1, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    /**
     * 组合总和
     */
    public List<List<Integer>> six(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        sixBackTrace(nums, target, 0, path, result);

        return result;
    }

    private void sixBackTrace(int[] nums, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if(0==target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(target<nums[i]) {
                break;
            }
            path.add(nums[i]);
            sixBackTrace(nums, target-nums[i], i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 组合总和(重复使用数字)
     */
    public List<List<Integer>> seven(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        sevenBackTrace(nums, target, 0, path, result);

        return result;
    }

    private void sevenBackTrace(int[] nums, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if(0==target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(target<nums[i]) {
                break;
            }
            path.add(nums[i]);
            sevenBackTrace(nums, target-nums[i], i, path, result);
            path.remove(path.size()-1);
        }
    }

    /**
     * 组合总和(重复数字)
     */
    public List<List<Integer>> eight(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);
        eightBackTrace(nums, used, target, 0, path, result);

        return result;
    }

    private void eightBackTrace(int[] nums, boolean[] used, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if(0==target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(target<nums[i]) {
                break;
            }
            if(used[i]) {
                continue;
            }
            if(i>0 && nums[i-1]==nums[i] && !used[i-1]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            eightBackTrace(nums, used, target-nums[i], i+1, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

}
