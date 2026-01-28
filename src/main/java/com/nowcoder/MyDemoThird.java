package com.nowcoder;

import java.util.HashSet;
import java.util.Set;

public class MyDemoThird {

    /**
     * 最长无重复
     */
    public int maxLength (int[] arr) {
        if(arr==null || arr.length==0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        set.add(arr[0]);
        int maxLength = 1;
        int n = arr.length;
        int i = 0;
        // 如果递增，统计递增的数量 如果存在一个不递增，下标直接切换到不递增的坐标
        for(int k=1; k<n; ) {
            int key = arr[k];
            if(set.contains(key)) {
                set.remove(arr[i]);
                i++;
            }else {
                set.add(key);
                maxLength = Math.max(maxLength, set.size());
                k++;
            }
        }

        return maxLength;
    }

    /**
     * 接雨水
     */
    public long maxWater (int[] arr) {
        if(arr==null || arr.length==0) {
            return 0;
        }

        int n = arr.length;

        // 左边最大值数组 右边最大值数组
        int leftMax = Integer.MIN_VALUE;
        int[] leftArr = new int[n];
        for(int i=0; i<n; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            leftArr[i] = leftMax;
        }

        int rightMax = Integer.MIN_VALUE;
        int[] rightArr = new int[n];
        for(int i=n-1; i>=0; i--) {
            rightMax = Math.max(rightMax, arr[i]);
            rightArr[i] = rightMax;
        }

        int result = 0;
        for(int i=1; i<n-1; i++) {
            result += Math.min(leftArr[i], rightArr[i]) - arr[i];
        }

        return result;
    }

    /**
     * 盛水最多的容器
     */
    public int maxArea (int[] height) {
        int maxArea = 0;
        if(height==null || height.length<=1) {
            return maxArea;
        }

        int i = 0;
        int j = height.length-1;
        while(i < j) {
            int w = j - i;
            int h = Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, w*h);

            if(height[i] < height[j]) {
                i++;
            }else {
                j--;
            }
        }

        return maxArea;
    }

    public int maxArea1 (int[] height) {
        return 0;
    }

    /**
     * 最长公共前缀
     */
    public String longestCommonPrefix1 (String[] strs) {
        if(strs==null || strs.length==0) {
            return "";
        }
        if(strs.length==1) {
            return strs[0];
        }
        for(String str : strs) {
            if(str==null || str.length()==0) {
                return "";
            }
        }

        String prefix = strs[0];
        for(int i=1; i<strs.length; i++) {
            while(strs[i].indexOf(prefix)!=0) {
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    /**
     * 字符串变形
     */
    public String trans1 (String s, int n) {
        if(s==null || s.length()==0 || s.length()!=n) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        String[] arr = s.split(" ");
        for(int i=arr.length-1; i>=0; i--) {
            String caseString = changeCase(arr[i]);
            if(i==0 && !"".equals(caseString)) {
                sb.append(caseString);
            }else if(!"".equals(caseString)) {
                sb.append(caseString).append(" ");
            }else {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    private String changeCase(String s) {
        if(s.length()==0) {
            return "";
        }

        char[] result = new char[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            if(c >= 'a' && c <= 'z') {
                result[i] = (char) (c - 32);
            }else if(c >= 'A' && c <= 'Z') {
                result[i] = (char) (c + 32);
            }
            i++;
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String result = new MyDemoThird().trans1("This is a sample",16);
        System.out.println(result);
        result = new MyDemoThird().trans1(" h i",4);
        System.out.println(result);
        System.out.println("---------------");
        String[] s = " h i".split(" ");
        System.out.println(s.length);
        for(String sp : s) {
            System.out.println(sp);
        }
        System.out.println("---------------");
    }


    /**
     * 字符串变形
     */
    public String trans(String s, int n) {
        if(s==null || n==0) {
            return s;
        }

        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        int i = n-1;
        int j = n-1;
        while(i >= 0) {
            // 如果最后边存在空格情况，优先先处理空格
            while (i >= 0 && cs[i] == ' ') {
                sb.append(' ');
                i--;
            }
            j = i;

            // 找到单词的开始位置
            while(i>=0 && cs[i]!=' ') {
                i--;
            }

            // 处理单词 i+1 到 j
            for(int k=i+1; k<=j; k++) {
                char ch = cs[k];
                if(Character.isUpperCase(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }else if(Character.isLowerCase(ch)) {
                    sb.append(Character.toUpperCase(ch));
                }else {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }


    /**
     * 字符串变形
     */
    public String trans2(String s, int n) {
        if(s == null || n == 0) {
            return s;
        }

        String[] words = s.split(" ", -1);

        StringBuilder sb = new StringBuilder();
        for(int i = words.length-1; i >= 0; i--) {
            String word = words[i];
            // 最后一个不加空格 想想为啥 如果最后不是空格结尾,则这点代码有用 如果最后一段为空格结尾时候,则空转一回,返回来添加一个空格
            if(i < words.length - 1) {
                sb.append(" ");
            }

            // 处理大小写转换
            char[] cs = word.toCharArray();
            for(char ch : cs) {
                if(Character.isUpperCase(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }else if (Character.isLowerCase(ch)) {
                    sb.append(Character.toUpperCase(ch));
                }else {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 分糖果
     */
    public int candy (int[] arr) {
        if(arr==null || arr.length==0) {
            return 0;
        }

        int n = arr.length;
        int[] candies = new int[n];

        // 初始化每个孩子分配一个糖果
        for(int i=0; i<n; i++) {
            candies[i] = 1;
        }

        // 从左往右确定评分高的孩子分的糖果多
        for(int i=1; i<n; i++) {
            if(candies[i] > candies[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        // 从右往左确定评分高的孩子分的糖果多
        for(int i=n-2; i>=0; i--) {
            if(candies[i] > candies[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }

        int total = 0;
        for(int candy : candies) {
            total += candy;
        }

        return total;
    }

    /**
     * 大数加法
     */
    public String solve (String s, String t) {
        if (s == null || s.isEmpty() || "0".equals(s)){
            return t;
        }
        if (t == null || t.isEmpty() || "0".equals(t)){
            return s;
        }

        String result = "";

        boolean sBigZero = s.charAt(0)!='-';
        if(!sBigZero || s.charAt(0)=='+') {
            s = s.substring(1, s.length()-1);
        }

        boolean tBigZero = t.charAt(0)!='-';
        if(!tBigZero || t.charAt(0)=='+') {
            t = t.substring(1, t.length()-1);
        }
        while(t.charAt(0)=='0') {
            t = t.substring(1, t.length()-1);
        }

        // 首先判断，是否包含正好和负号
        if(sBigZero == tBigZero) {
            // 相加操作 然后最后添加符号
            result = addSolve(sBigZero, s, t);
        }else if(s.length() > t.length()) {
            // 都是长的减去短的，之后保留长的符号
            result = addSolve(sBigZero, s, t);
        }else if(s.length() < t.length()) {
            // 都是长的减去短的，之后保留长的符号
            result = addSolve(tBigZero, t, s);
        }else {
            result = compareAndCal(sBigZero, s, t);
        }

        return result;
    }

    private String addSolve(boolean flag, String a, String b) {
        StringBuilder result = new StringBuilder();

        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;

        while(i>=0 && j>=0) {
            int numa = a.charAt(i)-'0';
            int numb = b.charAt(j)-'0';
            int sum = numa + numb + carry;
            if(sum > 9) {
                sum = sum - 10;
                carry = 1;
            }else {
                carry = 0;
            }
            result.append(sum);
            i--;
            j--;
        }

        while(i >= 0) {
            int numa = a.charAt(i)-'0';
            int sum = numa + carry;
            if(sum > 9) {
                sum = sum - 10;
                carry = 1;
            }else {
                carry = 0;
            }
            result.append(sum);
            i--;
        }

        while(j >= 0) {
            int numb = b.charAt(j)-'0';
            int sum = numb + carry;
            if(sum > 9) {
                sum = sum - 10;
                carry = 1;
            }else {
                carry = 0;
            }
            result.append(sum);
            j--;
        }

        if(carry>0) {
            result.append(carry);
            carry = 0;
        }

        if(!flag) {
            result.append("-");
        }

        return result.reverse().toString();
    }

    private String subSolve(boolean flag, String a, String b) {
        StringBuilder result = new StringBuilder();

        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;

        while(i>=0 && j>=0) {
            int numa = a.charAt(i)-'0';
            int numb = b.charAt(j)-'0';
            int sum = numa - carry - numb;

            if(sum >= 0) {
                carry = 0;
            }else {
                sum = sum + 10;
                carry = 1;
            }
            result.append(sum);
            i--;
            j--;
        }

        int last = 0;
        while(i>=0) {
            int numa = a.charAt(i)-'0';
            int sum = numa - carry;
            if(sum >= 0) {
                carry = 0;
            }else {
                sum = sum + 10;
                carry = 1;
            }
            last = sum;
            result.append(sum);
            i--;
        }

        if(last==0) {
            result.deleteCharAt(result.length()-1);
        }

        if(!flag) {
            result.append("-");
        }

        return result.reverse().toString();
    }

    private String compareAndCal(boolean flag, String a, String b) {
        if(a.equals(b)) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        while(i>=0 && j>=0) {
            int numa = a.charAt(i)-'0';
            int numb = b.charAt(j)-'0';
            int sum = numa - carry - numb;

            if(sum >= 0) {
                carry = 0;
            }else {
                sum = sum + 10;
                carry = 1;
            }
            result.append(sum);
            i--;
            j--;
        }

        // 前边的小 前边的是正数 则返回负数 或者前边的大 前边是负数 则返回负数
        if((carry>0 && flag) || (carry==0 && !flag)) {
            result.append("-");
        }

        return result.reverse().toString();
    }

}
