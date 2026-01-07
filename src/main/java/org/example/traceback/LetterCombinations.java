package org.example.traceback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 手机号码转换为字母
 */
public class LetterCombinations {

    private List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) {
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

        char[] path = new char[digits.length()];
        backtrace(mappings, digits, 0, path);
        return result;
    }

    // k表示阶段
    // path路径
    // digits[k]+mappings确定当前阶段的可选列表
    private void backtrace(String[] mappings, String digits, int k, char[] path) {
        if(k==digits.length()) {
            result.add(new String(path));
            return;
        }
        String mapping = mappings[digits.charAt(k)-'0'];
        for(int i=0; i<mapping.length(); i++) {
            path[k] = mapping.charAt(i);
            backtrace(mappings, digits, k+1, path);
        }
    }

}
