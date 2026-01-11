package org.example;

import org.example.utils.PrintUtils;

import java.awt.font.NumericShaper;
import java.util.*;

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

        System.out.println();
        nums = new int[]{1,2,3};
        result.clear();
        result = new Test().subSets(nums);
        PrintUtils.printString(result);

        System.out.println();
        nums = new int[]{1,2,2};
        result.clear();
        result = new Test().subSetsWithDup(nums);
        PrintUtils.printString(result);

        System.out.println();
        nums = new int[]{1,2,2};
        result.clear();
        result = new Test().subSetsWithDup1(nums);
        PrintUtils.printString(result);

        System.out.println();
        new Test().mytest();
    }

    public List<List<Integer>> testplzh(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = i+1;
        }
        testplzhbacktrace(nums, 0, 0, k, path, result);
        return result;
    }

    private void testplzhbacktrace(int[] nums, int start, int step, int k, List<Integer> path, List<List<Integer>> result) {
        if(k == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            testplzhbacktrace(nums, i+1, step+1, k, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> subSets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subSetsBackTrace(nums, 0, 0, path, result);
        return result;
    }

    private void subSetsBackTrace(int[] nums, int start, int step, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == step) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            // 不选择
            subSetsBackTrace(nums, i+1,step+1, path, result);
            if(path.contains(nums[i])) { // 剪枝
                continue;
            }
            // 选择
            path.add(nums[i]);
             // 回溯
            subSetsBackTrace(nums, i+1,step+1, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> subSetsWithDup1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        subSetsWithDupBackTrace1(nums, 0, 0, path, result);
        return result;
    }

    private void subSetsWithDupBackTrace1(int[] nums, int start, int step, List<Integer> path, List<List<Integer>> result) {
        if(nums.length == step) {
            result.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for(int i=start; i<nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 不选择
            subSetsWithDupBackTrace1(nums, i+1,step+1, path, result);
            /*if(path.contains(nums[i])) { // 剪枝
                continue;
            }*/
            /*if(visited.contains(nums[i])) {
                continue;
            }
            if(!path.contains(nums[i])) {
                visited.add(nums[i]);
            }*/

            // 选择
            path.add(nums[i]);
            // 回溯
            subSetsWithDupBackTrace1(nums, i+1,step+1, path, result);
            // 撤销选择
            path.remove(path.size()-1);
        }
    }


    public List<List<Integer>> subSetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);  // 关键步骤：必须先排序
        subSetsWithDupBackTrace(nums, 0, path, result);
        return result;
    }

    private void subSetsWithDupBackTrace(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));  // 添加当前子集到结果集

        for (int i = start; i < nums.length; i++) {
            // 跳过重复元素：如果当前元素与前一个元素相同，且不是第一个，则跳过
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 选择当前元素
            path.add(nums[i]);

            // 递归探索下一层
            subSetsWithDupBackTrace(nums, i + 1, path, result);

            // 回溯，撤销选择
            path.remove(path.size() - 1);
        }
    }





    private void backtrackBinarySearch(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        // 到达决策树的叶子节点
        if (step == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 情况1：选择当前元素
        path.add(nums[step]);
        backtrackBinarySearch(nums, step + 1, path, result);
        path.remove(path.size() - 1);

        // 跳过重复元素（对于重复情况）
        // 普通情况下直接递归到不选择分支

        // 情况2：不选择当前元素
        // 对于重复元素，需要跳过所有相同的
        int nextIndex = step + 1;
        while (nextIndex < nums.length && nums[nextIndex] == nums[step]) {
            nextIndex++;
        }
        backtrackBinarySearch(nums, nextIndex, path, result);
    }

    public List<List<Integer>> mytest() {
        int[] nums = new int[]{1,2,2};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrackStandard(nums, 0, path, result);
        PrintUtils.printString(result);

        System.out.println();

        result.clear();
        path.clear();
        nums = new int[]{1,2,3};
        backtrackStandard(nums, 0, path, result);
        PrintUtils.printString(result);


        System.out.println();
        System.out.println("----------------------");
        System.out.println();
        permute(nums);
        //PrintUtils.printString(result);

        result.clear();
        path.clear();
        System.out.println();
        nums = new int[]{1,1,2};
        Arrays.sort(nums);
        permute(nums);
        //PrintUtils.printString(result);

        return result;
    }

    private void backtrackStandard(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // 收集结果（所有路径都是子集）
        result.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            // 去重条件（对于重复元素）
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // 做选择
            path.add(nums[i]);

            // 递归到下一层
            backtrackStandard(nums, i + 1, path, result);

            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        permuteBackTrace(nums, 0, path, result);
        PrintUtils.printString(result);
        return result;
    }

    private void permuteBackTrace(int[] nums, int step, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }
            // 去重条件（对于重复元素）
            if (i > 0 && nums[i] == nums[i - 1] && !path.contains(nums[i-1])) {
                continue;
            }

            // 做选择
            path.add(nums[i]);

            // 递归到下一层
            permuteBackTrace(nums, step+1, path, result);

            // 撤销选择
            path.remove(path.size() - 1);
        }
    }


}
