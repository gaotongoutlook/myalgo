package org.example.traceback;

import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_USM;
import org.example.Test;
import org.example.utils.PrintUtils;

import java.util.*;

/**
 * 排列组合相关题目
 */
public class Combination {

    /**
     * 完全排列，返回数组nums的所有组合情况
     */
    private List<List<Integer>> result = new ArrayList<>();
    /**
     * 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backtrace(nums, 0, path);
        return result;
    }

    // 路径：记录在path中
    // 决策阶段： k
    // 可选列表： nums中除掉存在于path中的数据
    private void backtrace(int[] nums, int k, List<Integer> path) {
        // 结束条件
        if(k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 回溯 递归
            backtrace(nums, k+1, path);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }













    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        trace(result, res,candidates,target,0,0);
        return result;
    }

    public static void trace(List<List<Integer>> result, List<Integer> res, int[] candidates, int target, int curr, int index) {
        if (curr == target) {
            //得到预期目标
            result.add(new ArrayList<>(res));
        }
        Set<Integer> visit = new HashSet<>();
        for (int j = index; j < candidates.length; j++) {
            if (visit.contains(candidates[j])) {
                continue;
            } else {
                visit.add(candidates[j]);
            }
            if (curr + candidates[j] > target){
                //此路不通，后路肯定也不通
                break;
            }
            //继续试
            res.add(candidates[j]);
            trace(result, res,candidates,target,curr+candidates[j],j+1);
            res.remove(res.size()-1);
        }
    }

    public List<List<Integer>> test(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        testbacktrace(nums, 0, 0, target, path, result);
        return result;
    }

    private void testbacktrace(int[] nums, int k, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        // 结束条件
        if(sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> visited = new HashSet<>(); // 每次回溯进来都新添加一个 在同一次循环中，不存在重复值 多余的数字会在使用完，达到或者没有达到要求，都会被遗弃
        for(int i=k; i<nums.length; i++) {
            if(visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);

            if(sum+nums[i]>target) {
                break;
            }
            // 选择
            path.add(nums[i]);
            // 回溯
            testbacktrace(nums, i+1, sum+nums[i], target, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }


    public static void main(String[] args) {
        /*int[] nums = new int[]{2, 3, 6, 7};
        int target = 9;
        List<List<Integer>> result = new MyTraceBack().test4(nums, target);
        System.out.println("数量: "+ result.size());
        for(List<Integer> list : result) {
            for(Integer integer : list) {
                System.out.print(integer + " ,");
            }
            System.out.println();
        }*/
        int[] nums = new int[]{2,5,2,1,2,3,3,4};
        int target = 9;
        List<List<Integer>> result = new Combination().test(nums, target);
        PrintUtils.printString(result);
    }



}
