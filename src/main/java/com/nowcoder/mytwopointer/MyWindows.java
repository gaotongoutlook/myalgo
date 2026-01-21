package com.nowcoder.mytwopointer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyWindows {

    /**
     * 和为S的连续正数序列
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();

        int p=1;
        int q=2;
        int sum=3;
        while(p < q) {
            if(sum == target) {
                int[] arr = new int[q-p+1];
                for(int i=p; i<=q; i++) {
                    arr[i-p] = i;
                }
                result.add(arr);
                sum-=p;
                p++;
                q++;
            }else if(sum>target) {
                sum-=p;
                p++;
            }else {
                q++;
                sum+=q;
            }
        }

        int[][] resultArr = new int[result.size()][];
        for(int i=0; i<result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        return resultArr;
    }

    /**
     * 最长不含重复字符的子字符串
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n==0) {
            return 0;
        }

        int p=0;
        int q=0;
        int maxLen=0;
        Set<Character> set = new HashSet<>();
        while(q < n) {
            char c = s.charAt(q);
            if(!set.contains(c)) {
                set.add(c);
                q++;
                if(q-p > maxLen) {
                    maxLen = q-p;
                }
                continue;
            }
            while(set.contains(c)) {
                set.remove(s.charAt(p));
                p++;
            }
        }

        return maxLen;
    }

}
