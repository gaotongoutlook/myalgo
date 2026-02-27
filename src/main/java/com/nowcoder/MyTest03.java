package com.nowcoder;

import com.sun.xml.internal.bind.CycleRecoverable;
import javafx.scene.input.Mnemonic;
import org.example.pojo.ListNode;
import org.example.pojo.TreeNode;
import sun.plugin.dom.exception.BrowserNotSupportedException;

import javax.swing.*;
import java.util.*;

public class MyTest03 {

    /**
     * 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length==0) {
            return new int[]{-1, -1};
        }

        int n = nums.length;
        int[][] numbers = new int[n][2];
        for(int i=0; i<n; i++) {
            numbers[i][0] = nums[i];
            numbers[i][1] = i;
        }

        Arrays.sort(numbers);
        int i=0;
        int j=n-1;
        while(i<j) {
            int sum = numbers[i][0] + numbers[j][0];
            if(sum==target) {
                return new int[]{numbers[i][1], numbers[j][1]};
            }else if(sum<target) {
                i++;
            }else {
                j--;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * 翻转链表
     */
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null) {
            ListNode next = cur.next;
            cur.next = pre;
            cur = next;
            pre = cur;
        }

        return pre;
    }

    /**
     * 无重复子串的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1) {
            return s.length();
        }

        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        int i=0;
        int j=0;
        int maxLen = 0;
        while(j<s.length()) {
            char c = s.charAt(j);
            if(!map.containsKey(c)) {
                map.put(c, j);
                j++;
                maxLen = Math.max(maxLen, j-i);
                continue;
            }
            while(map.containsKey(c)) {
                map.remove(s.charAt(i));
                i++;
            }
        }

        return maxLen;
    }

    /**
     * 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null || l2==null) {
            return l1==null ? l2 : l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        int carry = 0;
        while(l1!=null || l2!=null) {
            int sum = carry;
            if(l1!=null) {
                sum += l1.val;
            }
            if(l2!=null) {
                sum += l2.val;
            }

            if(sum>9) {
                carry = 1;
                sum %= 10;
            }else {
                carry = 0;
            }

            cur.next = new ListNode(sum);
            cur = cur.next;
            if(l1!=null) {
                l1 = l1.next;
            }
            if(l2!=null) {
                l2 = l2.next;
            }
        }

        if(carry>0) {
            cur.next = new ListNode(carry);
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 有效的括号
     */
    public boolean isValid(String s) {
        if(s==null || s.length()==0) {
            return true;
        }
        if(s.length()%2==1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c=='(') {
                stack.push(')');
            }else if(c=='{') {
                stack.push('}');
            }else if(c=='[') {
                stack.push(']');
            }else {
                // 如果都是右括号
                if(stack.isEmpty()) {
                    return false;
                }
                char p = stack.pop();
                if(p!=c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 最长回文子串
     */
    public String longestPalindrome(String s) {

        return null;
    }

    /**
     * 买卖股票的最佳时机
     */
    public int maxProfit(int[] prices) {
        return 0;
    }

    /**
     * 爬楼梯
     */
    public int climbStairs(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 最大子序和
     */
    public int maxSubArray(int[] nums) {
        return 0;
    }

    /**
     * 删除链表的倒数第N个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null || n<=0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count = n;
        ListNode nth = dummy;


        return dummy.next;
    }

    /**
     * 合并两个有序链表
     */
    public ListNode mergeTowLists(ListNode l1, ListNode l2) {
        if(l1==null || l2==null) {
            return l1==null ? l2 : l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1!=null && l2!=null) {
            if(l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }

        while(l1!=null) {
            cur.next = new ListNode(l1.val);
            cur = cur.next;
            l1 = l1.next;
        }

        while(l2!=null) {
            cur.next = new ListNode(l2.val);
            cur = cur.next;
            l2 = l2.next;
        }

        return dummy.next;
    }

    /**
     * 移动零
     */
    public void moveZeroes(int[] nums) {

    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            int mid = left + (right-left)>>1;
            if(nums[mid]==target) {
                return findSearchRange(nums, target, mid);
            }else if(nums[mid]<target) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return new int[]{-1, -1};
    }

    private int[] findSearchRange(int[] nums, int target, int mid) {
        int left = mid-1;
        while(left>=0 && nums[left]==target) {
            left--;
        }

        int right = mid+1;
        while(right<nums.length && nums[right]==target) {
            right++;
        }

        return new int[]{left+1, right-1};
    }

    /**
     * 盛水最多的容器
     */
    public int maxArea(int[] height) {
        return 0;
    }

    /**
     * 电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        return null;
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    /**
     * 环形链表
     */
    public boolean hasCycle(ListNode head) {
        return false;
    }

    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        return 0;
    }

    /**
     * 括号生成
     */
    public List<String> generateParenthesis(int n) {
        return null;
    }

    /**
     * 接雨水
     */
    public int trap(int[] height) {
        return 0;
    }

    /**
     * 二叉树的层次遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        return null;
    }

    /**
     * 打家劫舍
     */
    public int rob(int[] nums) {
        return 0;
    }

    /**
     * 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }

    /**
     * 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        return false;
    }

    /**
     * 不同路径
     */
    public int uniquePaths(int m, int n) {
        return -1;
    }

    /**
     * 合并区间
     */
    public int[][] merge(int[][] intervals) {
        return null;
    }

    /**
     * 多数元素
     */
    public int majorityElement(int[] nums) {
        return -1;
    }

    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return false;
    }

    /**
     * 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        return -1;
    }

    /**
     * 只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        return -1;
    }

    /**
     * 合并K个升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

    /**
     * 二叉树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        return null;
    }

    /**
     * 最小路径和
     */
    public int minPathSum(int[][] grid) {
        return -1;
    }

    /**
     * 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        return -1;
    }

    /**
     * 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        return false;
    }

    /**
     * 岛屿数量
     */
    public int numIslands(char[][] grid) {
        return -1;
    }

    /**
     * 旋转图像
     */
    public void rotate(int[][] matrix) {

    }

    /**
     * 验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return false;
    }

    /**
     * 子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        return null;
    }

    /**
     * 颜色分类
     */
    public void sortColors(int[] nums) {

    }

    /**
     * 组合总和
     */
    public void tranverse(int[] array, Stack stack) {

    }

    /**
     * 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        return null;
    }

    /**
     * 下一个排列
     */
    public void nextPermutation(int[] nums) {

    }

    /**
     * 零钱兑换
     */
    public int coinChange(int[] coins, int amount) {
        return -1;
    }

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        return -1;
    }

    /**
     * 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return null;
    }

    /**
     * 环形链表II
     */
    public ListNode detectCycle(ListNode head) {
        return null;
    }

    /**
     * 每日温度
     */
    public int[] dailyTemperatures(int[] T) {
        return null;
    }

    /**
     * 前K个高频元素
     */
    public int[] topKFrequent(int[] nums, int k) {
        return null;
    }

    /**
     * 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        return null;
    }

    /**
     * 最长有效括号
     */
    public int longestValidParentheses(String s) {
        return -1;
    }

    /**
     * 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return -1;
    }

    /**
     * 单词搜索
     */
    public boolean exist(char[][] board, String word) {
        return false;
    }

    /**
     * 不同的二叉搜索树
     */
    public int numTrees(int n) {
        return -1;
    }

    /**
     * 滑动窗口最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        return null;
    }

    /**
     * LRU缓存机制
     */

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        return null;
    }

    /**
     * 二叉树展开为链表
     */
    public void flatten(TreeNode root) {

    }

    /**
     * 柱状图中最大的矩阵
     */
    public int largestRectangleArea(int[] heights) {
        return -1;
    }

    /**
     * 排序链表
     */
    public ListNode sortList(ListNode start) {
        return null;
    }

    /**
     * 合并二叉树
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return null;
    }

    /**
     * 寻找重复数
     */

    /**
     * 乘积最大子数组
     */
    public int maxProduct(int[] nums) {
        return -1;
    }

    /**
     * 编辑距离
     */
    public int minDistance(String word1, String word2) {
        return -1;
    }

    /**
     * 单词拆分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    /**
     * 最小覆盖子串
     */
    public String minWindow(String s, String t) {
        return null;
    }

    /**
     * 二叉树中的最大路径和
     */
    public int maxPathSum(TreeNode root) {
        return -1;
    }

    /**
     * 汉明距离
     */
    public int hammingDistance(int x, int y) {
        return -1;
    }

    /**
     * 最长连续序列
     */
    public int longestConsecutive(int[] nums) {
        return -1;
    }

    /**
     * 回文子串
     */
    public int countSubstrings(String s) {
        return -1;
    }

    /**
     * 打家劫舍III
     */
    public int rob(TreeNode root) {
        return -1;
    }

    /**
     * 除自身以外数组的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        return null;
    }

    /**
     * 课程表
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    /**
     * 最佳买卖股票时机含冷冻期
     */
    public int maxProfit1(int[] prices) {
        return -1;
    }

    /**
     * 分割等和子串
     */
    public boolean canPartition(int[] nums) {
        return false;
    }

    /**
     * 和为K的子数组
     */
    public int subarraySum(int[] nums, int k) {
        return -1;
    }

    /**
     * 找到所有数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        return null;
    }

    /**
     * 路径总和III
     */
    public int pathSum(TreeNode root, int sum) {
        return -1;
    }

    /**
     * 比特位计数
     */
    public int[] countBits(int num) {
        return null;
    }

    /**
     * 根据身高重建队列
     */
    public int[][] reconstructQueue(int[][] people) {
        return null;
    }

    /**
     * 把二叉搜索树转换为累加树
     */
    public TreeNode convertBST(TreeNode root) {
        return null;
    }

    /**
     * 二叉树的序列化和反序列化
     */
    public String serialize(TreeNode root) {
        return null;
    }

    public TreeNode deserialize(String str) {
        return null;
    }

    /**
     * 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {
        return null;
    }

    /**
     * 搜索二维矩阵II
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        return false;
    }

    /**
     * 目标和
     */
    public int findTargetSumWays(int[] nums, int S) {
        return -1;
    }

    /**
     * 任务调度器
     */
    public int leastInterval(char[] tasks, int n) {
        return -1;
    }

    /**
     * 最短无序连续子数组
     */
    public int findUnsortedSubarray(int[] nums) {
        return -1;
    }

}
