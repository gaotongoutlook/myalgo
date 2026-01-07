package org.example.traceback;

/**
 * 正则表达式
 */
public class RegularExpression {

    private boolean matched = false;
    public boolean match(char[] text, char[] pattern) {
        backtrace(text, pattern, 0, 0);
        return matched;
    }

    // pj 相当于阶段
    // 路径不需要记录(pattern中的每个字符怎么匹配的)
    // 可选列表由: pattern[pj] text ti 推导出来
    private void backtrace(char[] text, char[] pattern, int ti, int pj) {
        // 结束条件
        if(pj == pattern.length) { // 正则表达式到结尾了
            if(ti == text.length) { // 可行解
                matched = true; // 文本串也到结尾了
            }
            return;
        }

        // 做选择(根据pattern中当前考察字符的具体情况)
        if(pattern[pj] == '*') { // *匹配任意个字符
            for(int k=0; k<=text.length-ti; k++) {
                backtrace(text, pattern, ti+k, pj+1);
            }
        } else if(pattern[pj] == '?') {
            backtrace(text, pattern, ti, pj+1);
            if(ti < text.length) {
                backtrace(text, pattern, ti+1, pj+1);
            }
        } else if(ti<text.length && pattern[pj]==text[ti]) {
            backtrace(text, pattern, ti+1, pj+1);
        }
        // 撤销选择，因为没有全局变量，局部变量会在递归返回时自动恢复
    }

}
