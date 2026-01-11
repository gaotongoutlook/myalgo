package org.example;

import org.example.utils.PrintUtils;
import sun.security.ec.SunEC;

import java.util.*;

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

        System.out.println();
        n = 3;
        example.generateParenthesis(n);
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

        for(int i=0; i<nums.length; i++) {
            if(path.contains(nums[i])) {
                continue;
            }

            path.add(nums[i]);
            fourBackTrace(nums, i+1, path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> five(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        fiveBackTrace(nums, used, path, result);
        return result;
    }

    private void fiveBackTrace(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if(path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(used[i]) {
                continue;
            }

            if(i>0 && nums[i]==nums[i-1] && !used[i-1]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);
            fiveBackTrace(nums, used, path, result);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }

    public List<List<Integer>> six(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        sixBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void sixBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            sixBackTrace(nums, i+1, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> seven(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        sevenBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void sevenBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            sevenBackTrace(nums, i, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> eight(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        eightBackTrace(nums, 0, target, path, result);
        return result;
    }

    private void eightBackTrace(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if(target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for(int i=start; i<nums.length; i++) {
            if(nums[i] > target) {
                break;
            }
            if(visited.contains(nums[i])) {
                continue;
            }
            visited.add(nums[i]);

            path.add(nums[i]);
            eightBackTrace(nums, i+1, target-nums[i], path, result);
            path.remove(path.size()-1);
        }
    }








    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 先排序，方便去重
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backtrack(nums, used, path, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字已经使用过，跳过
            if (used[i]) {
                continue;
            }

            // 去重关键：如果当前数字和前一个数字相同，且前一个数字没有被使用，跳过
            // 这样可以保证相同的数字按顺序使用，避免重复排列
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            path.add(nums[i]);

            backtrack(nums, used, path, result);

            // 回溯
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisBackTrace(n, 0, new LinkedList<String>(), result);
        System.out.println("len :" + result.size());
        for(String str : result){
            System.out.println(str);
        }
        System.out.println();
        return result;
    }

    private void generateParenthesisBackTrace(int n, int step, LinkedList<String> linkedList, List<String> result) {
        if(n == step) {
            StringBuilder sb = new StringBuilder();
            int len = 0;
            while(len < linkedList.size()) {
                sb.append(linkedList.get(len));
                len++;
            }
            result.add(sb.toString());
            return;
        }

        linkedList.addFirst("(");
        linkedList.addLast(")");
        generateParenthesisBackTrace(n, step+1, linkedList, result);
        linkedList.pollFirst();
        linkedList.pollLast();

        linkedList.addFirst(")");
        linkedList.addFirst("(");
        generateParenthesisBackTrace(n, step+1, linkedList, result);
        linkedList.pollFirst();
        linkedList.pollFirst();


    }

}
