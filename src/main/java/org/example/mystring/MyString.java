package org.example.mystring;

import java.util.HashMap;
import java.util.Map;

public class MyString {

    /**
     * 最长无重复字符串
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> hashMap = new HashMap<>();
        char[] chars = s.toCharArray();
        int left = 0;
        int length = 0;
        for(int right = 0; right < chars.length; right++) {
            char c = chars[right];
            if(hashMap.containsKey(c)) {
                hashMap.put(c, hashMap.get(c) + 1);
                // 变更最长
                left = hashMap.get(c) + 1;
                length = Math.max(length, right - left + 1);
                continue;
            }
            hashMap.put(c, right);
            length++;
        }

        return length;
    }

    /**
     * 反转字符串
     */
    public String solve (String str) {
        if(str == null || str.length() <= 1) {
            return str;
        }

        int left = 0;
        int right = str.length() - 1;
        char[] chars = str.toCharArray();
        while(left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }




}
