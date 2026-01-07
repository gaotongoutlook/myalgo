package org.example;

import org.example.utils.PrintUtils;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Test {

    /**
     * 非重复数组非重复选择
     */
    public List<List<Integer>> selectOnce(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        selectOnceBackTrace(nums, 0, 0, target, path, result);
        return result;
    }

    // nums 可选列表
    // k 阶段
    // path 路径
    private void selectOnceBackTrace(int[] nums, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 结束条件
        if(k == nums.length) {
            if(sum==target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        // 不做选择
        selectOnceBackTrace(nums, k+1, sum, target, path, result);

        // 选择
        path.add(nums[k]);
        // 回溯
        selectOnceBackTrace(nums, k+1, sum+nums[k], target, path, result);
        // 撤销选择
        path.remove(path.size()-1);
    }


    /**
     * 非重复数组重复选择 不能够存在某一个数字不选择，而是可能选择多次
     */
    public List<List<Integer>> selectOnceRepeat(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        selectOnceRepeatBackTrace(nums, 0, 0, target, path, result);
        return result;
    }

    // nums 可选列表
    // k 阶段
    // path 路径
    private void selectOnceRepeatBackTrace(int[] nums, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 结束条件 当都必须选择的时候，就不存在阶段信息了
        if(sum==target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=k; i<nums.length; i++) {
            if(sum + nums[i] > target) { // 剪枝
                break;
            }
            //  选择
            path.add(nums[i]);
            // 回溯 可能重复选择多次，所以阶段也会重复多次
            selectOnceRepeatBackTrace(nums, i, sum+nums[i], target, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};
        int target = 9;
        List<List<Integer>> result = new Test().selectOnce(nums, target);
        PrintUtils.printString(result);

        System.out.println();
        result.clear();
        result = new Test().selectOnceRepeat(nums, target);
        PrintUtils.printString(result);

        System.out.println();
        result.clear();
        int n = 4;
        int k = 2;
        result = new Test().testplzh(n, k);
        PrintUtils.printString(result);
    }

    public List<List<Integer>> testplzh(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = i+1;
        }
        testplzhbacktrace(nums, 0, k, path, result);
        return result;
    }

    private void testplzhbacktrace(int[] nums, int k, int count, List<Integer> path, List<List<Integer>> result) {
        if(k == count) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=k; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            testplzhbacktrace(nums, k+1, count, path, result); // 阶段什么时候用i呢
            path.remove(path.size()-1);
        }
    }

}
