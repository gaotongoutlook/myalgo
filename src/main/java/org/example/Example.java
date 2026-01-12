package org.example;

import org.example.traceback.RestoreIpAddresses;
import org.example.utils.PrintUtils;
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

        List<List<String>> stringResult = new ArrayList<>();

        // 9.字符串回文结构
        System.out.println("---------- 9.字符串回文结构 ----------");
        String s = new String("abcba");
        stringResult = example.partition(s);
        PrintUtils.printStringResult(stringResult);
        stringResult.clear();
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

    /**
     * 括号数量
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] path = new char[2*n];
        generateParenthesisBackTrace(n, 0, 0, 0, path, result);
        return result;
    }

    private void generateParenthesisBackTrace(int n, int leftUsed, int rightUsed, int step, char[] path, List<String> result) {
        if(step == 2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if(leftUsed < n) {
            path[step] = '(';
            generateParenthesisBackTrace(n, leftUsed+1, rightUsed, step+1, path, result);
        }
        if(leftUsed > rightUsed && rightUsed < n) { // 右括号数量不能比左括号多，否则不匹配了
            path[step] = ')';
            generateParenthesisBackTrace(n, leftUsed, rightUsed+1, step+1, path, result);
        }
    }

    /**
     * 字符串回文结构
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionBackTrace(s, 0, path, result);
        return result;
    }

    private void partitionBackTrace(String s, int step, List<String> path, List<List<String>> result) {
        if(step == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int end=step; end<s.length(); end++) {
            String subStr = s.substring(step, end+1);
            // System.out.println("第"+step+"步，字符串为： "+subStr+" ，是不是回文串 "+isPalindrome(subStr));
            if(isPalindrome(subStr)) {
                path.add(subStr);
                partitionBackTrace(s, end+1, path, result);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(i<=j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 手机号对应字母的集合
     */
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return Collections.emptyList();
        }

        String[] mappings = new String[10];
        mappings[2] = "abc";
        mappings[3] = "efg";
        mappings[4] = "ghi";
        mappings[5] = "jkl";
        mappings[6] = "mno";
        mappings[7] = "pqrs";
        mappings[8] = "tuv";
        mappings[9] = "wxyz";

        List<String> result = new ArrayList<>();
        char[] path = new char[digits.length()];
        letterCombinationsBackTrace(mappings, digits, 0, path, result);
        return result;
    }

    private void letterCombinationsBackTrace(String[] mappings, String digits, int step, char[] path, List<String> result) {
        if(digits.length()==step) {
            result.add(String.valueOf(path));
            return;
        }

        String mapping = mappings[digits.charAt(step)-'0'];
        for(int i=0; i<mapping.length(); i++) {
            path[step] = mapping.charAt(i);
            letterCombinationsBackTrace(mappings, digits, step+1, path, result);
        }
    }

    /**
     * N皇后问题
     */
    public List<char[][]> numberQueue(int n) {
        List<char[][]> result = new ArrayList<>();
        char[][] path = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                path[i][j] = '*';
            }
        }
        numberQueueBackTrace(0, n, path, result);
        return result;
    }

    private void numberQueueBackTrace(int row, int n, char[][] path, List<char[][]> result) {
        if(row == n) {
            char[][] snapshot = new char[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    snapshot[i][j] = path[i][j];
                }
            }
            result.add(snapshot);
            return;
        }

        for(int col=0; col<n; col++) {
            if(isOk(path, n, row, col)) {
                path[row][col] = 'Q';
                numberQueueBackTrace(row+1, n, path, result);
                path[row][col] = '*';
            }
        }
    }

    private boolean isOk(char[][] path, int n, int row, int col) {
        // 检查列是否有冲突
        for(int i=0; i<row; i++) {
            if(path[i][col] == 'Q') {
                return false;
            }
        }

        // 检查右上对角线是否有冲突
        int i = row-1;
        int j = col+1;
        while (i>=0 && j<n) {
            if(path[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }

        // 检查左上对角线是否有冲突
        i = row - 1;
        j = col - 1;
        while (i>=0 && j>=0) {
            if(path[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }

        return true;
    }



}
