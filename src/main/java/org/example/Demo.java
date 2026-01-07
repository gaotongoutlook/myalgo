package org.example;

import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        int number = 1112225678;
        ArrayList<String> result = new Demo().translateNumList1(number);
        System.out.println(result.size());
        for(String string : result) {
            System.out.println(string);
        }
    }

    public ArrayList<String> translateNumList1(int num) {
        ArrayList<String> result = new ArrayList<>();
        if(num <= 0) {
            return result;
        }

        if(num <= 9) {
            result.add(String.valueOf(getLetter(num)));
            return result;
        }

        char[] cs = String.valueOf(num).toCharArray();

        mybacktrace(cs, 0, new StringBuilder(), result);

        return result;
    }

    private void mybacktrace(char[] cs, int index, StringBuilder current, ArrayList<String> result) {
        if(cs.length == index) {
            result.add(current.toString());
            return;
        }

        // 选择 单个下标元素
        int number = cs[index]-'0';
        if(number>=1 && number<=9) {
            char letter = getLetter(number);
            current.append(letter);
            mybacktrace(cs, index+1, current, result);
            // 不选择 单个下标元素
            current.deleteCharAt(current.length()-1);
        }

        // 不选择 多个下标元素
        if(index+1 < cs.length && isValidNum(cs[index], cs[index+1])) {
            number = number*10 + (cs[index+1]-'0');
            current.append(getLetter(number));
            mybacktrace(cs, index+2, current, result);
            // 不选择 多个下标元素
            current.deleteCharAt(current.length()-1);
        }
    }

    public ArrayList<String> translateNumList(int num) {
        ArrayList<String> result = new ArrayList<>();
        if (num < 0) return result;

        if (num == 0) {
            // 0可以映射为空或者特殊字符，这里映射为空
            result.add("");
            return result;
        }

        // 将数字转换为字符串，方便处理
        String numStr = String.valueOf(num);
        int n = numStr.length();

        // 使用回溯法生成所有可能的组合
        backtrack(numStr, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String numStr, int index, StringBuilder current, ArrayList<String> result) {
        // 到达字符串末尾，将当前组合加入结果
        if (index == numStr.length()) {
            result.add(current.toString());
            return;
        }

        // 情况1: 处理单个数字（1-9）
        // 注意：数字0不能单独映射为字母
        char digitChar = numStr.charAt(index);
        int singleDigit = digitChar - '0';

        if (singleDigit >= 1 && singleDigit <= 9) {
            // 获取对应的字母
            char letter = getLetter(singleDigit);
            current.append(letter);
            backtrack(numStr, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }

        // 情况2: 处理两个数字的组合（10-26）
        if (index + 1 < numStr.length()) {
            // 检查是否是两位数
            String twoDigitsStr = numStr.substring(index, index + 2);
            int twoDigits = Integer.parseInt(twoDigitsStr);

            // 两位数必须在10-26之间（包括10和26）
            if (twoDigits >= 10 && twoDigits <= 26) {
                char letter = getLetter(twoDigits);
                current.append(letter);
                backtrack(numStr, index + 2, current, result);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

    // 获取数字对应的字母（1->a, 2->b, ..., 26->z）
    private char getLetter(int num) {
        if (num >= 1 && num <= 26) {
            return (char) ('a' + num - 1);
        }
        return '?'; // 不应该发生
    }

    // 您原来的backtrace方法修改版本
    public void backtrace(int[] digits, int start, int n, ArrayList<String> result, ArrayList<Integer> temp) {
        if (start == n) {
            // 将temp中的数字组合转换为字符串
            StringBuilder sb = new StringBuilder();
            for (int num : temp) {
                if (num >= 1 && num <= 26) {
                    sb.append(getLetter(num));
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
            return;
        }

        // 处理单个数字（1-9）
        int digit = digits[start];
        if (digit >= 1 && digit <= 9) {
            temp.add(digit);
            backtrace(digits, start + 1, n, result, temp);
            temp.remove(temp.size() - 1);
        }

        // 处理两个数字的组合（10-26）
        if (start + 1 < n) {
            int twoDigits = digits[start] * 10 + digits[start + 1];
            if (twoDigits >= 10 && twoDigits <= 26) {
                temp.add(twoDigits);
                backtrace(digits, start + 2, n, result, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    // 增强版：支持0的处理
    public ArrayList<String> translateNumListWithZero(int num) {
        ArrayList<String> result = new ArrayList<>();
        if (num < 0) return result;

        String numStr = String.valueOf(num);
        backtrackWithZero(numStr, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrackWithZero(String numStr, int index, StringBuilder current, ArrayList<String> result) {
        if (index == numStr.length()) {
            result.add(current.toString());
            return;
        }

        char digitChar = numStr.charAt(index);
        int singleDigit = digitChar - '0';

        // 处理单个数字
        if (singleDigit == 0) {
            // 0可以映射为空格或特殊处理，这里映射为空
            backtrackWithZero(numStr, index + 1, current, result);
        } else if (singleDigit >= 1 && singleDigit <= 9) {
            char letter = getLetter(singleDigit);
            current.append(letter);
            backtrackWithZero(numStr, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }

        // 处理两个数字
        if (index + 1 < numStr.length()) {
            String twoDigitsStr = numStr.substring(index, index + 2);
            int twoDigits = Integer.parseInt(twoDigitsStr);

            // 处理两位数的情况
            if (twoDigits >= 10 && twoDigits <= 26) {
                char letter = getLetter(twoDigits);
                current.append(letter);
                backtrackWithZero(numStr, index + 2, current, result);
                current.deleteCharAt(current.length() - 1);
            } else if (twoDigitsStr.startsWith("0")) {
                // 处理以0开头的两位数，如"01"到"09"
                int secondDigit = twoDigitsStr.charAt(1) - '0';
                if (secondDigit >= 1 && secondDigit <= 9) {
                    char letter = getLetter(secondDigit);
                    current.append(letter);
                    backtrackWithZero(numStr, index + 2, current, result);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }

    private boolean isValidNum(int a, int b) {
        return (a==1) || (a==2 && b>=0 && b<=6);
    }

}
