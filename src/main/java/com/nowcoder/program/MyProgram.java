package com.nowcoder.program;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 编程题
 */
public class MyProgram {

    /**
     * 两数之和
     * https://leetcode.cn/problems/two-sum/
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length==0) {
            return new int[]{};
        }

        int n = nums.length;
        int[][] numbers = new int[n][2];
        for(int i=0; i<n; i++) {
            numbers[i][0] = nums[i];
            numbers[i][1] = i;
        }

        Arrays.sort(numbers);

        int i = 0;
        int j = n-1;
        while(i<j) {
            int sum = numbers[i][0]+numbers[j][0];
            if(target == sum) {
                return new int[]{numbers[i][1], numbers[j][1]};
            }else if(sum < target) {
                i++;
            }else {
                j--;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * IP地址无效化
     * https://leetcode.cn/problems/defanging-an-ip-address/description/
     */
    public String defangIPaddr(String address) {
        if(address==null || address.length()==0) {
            return address;
        }
        return address.replaceAll("\\.", "[.]");
    }

    /**
     * 反转字符串
     * https://leetcode.cn/problems/reverse-string/description/
     */
    public void reverseString(char[] s) {
        if(s==null || s.length==0) {
            return;
        }

        int i=0;
        int j=s.length-1;
        while(i<j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 字符串中的单词反转
     * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/description/
     */
    public String reverseMessage(String message) {
        if(message==null || message.length()==0) {
            return message;
        }

        StringBuilder result = new StringBuilder();
        String[] msgArr = message.trim().split(" ");
        for(String msg : msgArr) {
            if(!msg.isEmpty()) {
                result.append(msg);
            }
        }

        return result.reverse().toString();
    }

    /**
     * 验证回文串
     * https://leetcode.cn/problems/valid-palindrome/description/
     */
    public boolean isPalindrome(String s) {
        if(s==null || s.length()==0) {
            return false;
        }

        int i=0;
        int j=s.length()-1;
        while(i<j) {
            if(s.charAt(i)!=s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /**
     * 回文数
     * https://leetcode.cn/problems/palindrome-number/description/
     */
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        String number = Integer.toString(x);
        String reversed = new StringBuffer(number).reverse().toString();
        return number.equals(reversed);
    }

    /**
     * 回文数
     */
    public boolean isPalindrome1(int x) {
        if(x<0 || (x%10==0 && x!=0)) {
            return false;
        }

        int reversed = 0;
        int original = x;
        while(x>0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return reversed==original;
    }

    /**
     * 回文数
     */
    public boolean isPalindrome2(int x) {
        if(x < 0) {
            return false;
        }

        char[] cs = Integer.toString(x).toCharArray();
        int left = 0;
        int right = cs.length - 1;

        while(left < right) {
            if(cs[left] != cs[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 回文数
     */
    public boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        while(x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        return x==reversed || x==reversed/10;
    }

    /**
     * 最后一个单词的长度
     * https://leetcode.cn/problems/length-of-last-word/description/
     */
    public int lengthOfLastWord(String s) {
        if(s==null || s.length()==0) {
            return 0;
        }

        int len = 0;
        s = s.trim();
        for(int i=s.length()-1; i>=0; i--) {
            char ch = s.charAt(i);
            if(ch == ' ') {
                break;
            }
            if(ch >= 'a' && ch <= 'z') {
                len++;
            }
            if(ch >= 'A' && ch <= 'Z') {
                len++;
            }
        }

        return len;
    }

    /**
     * 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        int n = nums.length;
        int count = 1;
        int i=1;
        for(int k=1; k<n; k++) {
            if(nums[k-1]!=nums[k]) {
                nums[i] = nums[k];
                count++;
                i++;
            }
        }

        return count;
    }

    /**
     * 把字符串转换成整数
     * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/
     */
    public int myAtoi1(String str) {
        if(str==null || str.length()==0) {
            return 0;
        }
        str = str.trim();

        // 去除前导零
        int index = 0;
        while(str.charAt(index)=='0') {
            index++;
        }
        if(index>0) {
            str = str.substring(index, str.length());
        }

        boolean bigZero = true;
        if(str.charAt(0)=='+' || str.charAt(0)=='-') {
            bigZero = str.charAt(0) != '-';
            str = str.substring(1, str.length());
        }

        // 去除前导零
        index = 0;
        while(str.charAt(index)=='0') {
            index++;
        }
        if(index>0) {
            str = str.substring(index, str.length());
        }

        int n = str.length();
        StringBuilder result = new StringBuilder();
        if(!bigZero) {
            result.append("-");
        }

        // 最开始为非数字的相关字符
        for(int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                break;
            }
        }

        for(int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if(ch == ' ') {
                continue;
            }else if(ch >= '0' && ch <= '9') {
                result.append(ch);
            }else {
                break;
            }
        }

        String r = result.toString();
        if(r.length()==0) {
            return 0;
        }

        BigInteger bigInteger = new BigInteger(r);
        BigInteger maxInteger = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger minInteger = BigInteger.valueOf(Integer.MIN_VALUE);

        while(bigInteger.compareTo(maxInteger) > 0) {
            bigInteger = bigInteger.subtract(maxInteger);
        }

        while(bigInteger.compareTo(minInteger) < 0) {
            bigInteger = bigInteger.add(minInteger);
        }

        return Integer.parseInt(r);
    }

    /**
     * 替换空格
     */

    /**
     * 左旋转字符串
     * 旋转字符串
     * https://leetcode.cn/problems/rotate-string/
     */
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()) {
            return false;
        }
        if(s.equals(goal)) {
            return true;
        }
        StringBuilder sb = new StringBuilder(goal);
        sb.append(goal);
        return sb.toString().contains(s);
    }

    public static void main(String[] args) {
        String str = "words and 987";
        int result = new MyProgram().myAtoi(str);
        System.out.println(result);

        str = "4193 with words";
        result = new MyProgram().myAtoi(str);
        System.out.println(result);

        str = "-91283472332";
        result = new MyProgram().myAtoi(str);
        System.out.println(result);
    }

    public int myAtoi(String str) {
        if(str==null || str.length()==0) {
            return 0;
        }

        // 字符串在前 空格在前 多余的0在前 正负号在前 字符串在后 空格在后 空格在中间 数字超限
        // 如果字符串在前 则返回0 如果字符串在后 则返回字符串前边
        boolean positive = true;
        boolean decimalPoint = false;
        boolean hasNumber = false;
        boolean onceSymbol = false;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            // 没有数字之前 出现空格没事 出现数字没事 出现字母或者小数点 直接返回为0
            // 没有数字之前 出现正好和负号 需要做处理 正号忽略 负号添加
            if(!hasNumber && ch == ' ') {
                continue;
            }else if(!hasNumber && (ch == '+' || ch == '-') && !onceSymbol) {
                positive = ch != '-';
                onceSymbol = true;
                if(!positive) {
                    sb.append("-");
                }
            }
            else if(!hasNumber && (ch < '0' || ch > '9')) {
                return 0;
            }else if(hasNumber && ch == ' ') {
                break;
            }else if(hasNumber && ch == '.' && decimalPoint) {
                break;
            }

            if(ch >= '0' && ch <= '9') {
                sb.append(ch);
                hasNumber = true;
            }else if(ch == '.') {
                decimalPoint = true;
                sb.append(".");
            }
        }

        // 注意拼接字符串最后一个字符为小数点
        if(sb.length()>0 && sb.charAt(sb.length()-1)=='.') {
            sb.deleteCharAt(sb.length()-1);
            decimalPoint = false;
        }

        // 先处理越界问题
        BigDecimal bigInteger = new BigDecimal(sb.toString());
        BigDecimal maxValue = BigDecimal.valueOf(Integer.MAX_VALUE);
        BigDecimal minValue = BigDecimal.valueOf(Integer.MIN_VALUE);
        if(bigInteger.compareTo(minValue) < 0) {
            return Integer.MIN_VALUE;
        }else if(bigInteger.compareTo(maxValue) > 0) {
            return Integer.MAX_VALUE;
        }

        return bigInteger.intValue();
    }
}
