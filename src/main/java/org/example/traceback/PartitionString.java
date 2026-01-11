package org.example.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 */
public class PartitionString {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrace(s, 0, new ArrayList<>());
        return result;
    }

    private void backtrace(String s, int k, ArrayList<String> path) {
        if(k==s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int end=k; end<s.length(); end++) {
            String subStr = s.substring(k, end+1);
            if(isPalindrome(subStr)) {
                path.add(subStr);
                backtrace(s, end+1, path);
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

}
