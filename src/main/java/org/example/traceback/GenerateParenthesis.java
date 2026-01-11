package org.example.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 */
public class GenerateParenthesis {

    private List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        char[] path = new char[2*n];
        backtrace(n, 0, 0, 0, path);
        return result;
    }

    private void backtrace(int n, int leftUsed, int rightUsed, int k, char[] path) {
        if(k==2*n) {
            result.add(String.valueOf(path));
            return;
        }
        if(leftUsed<n) {
            path[k] = '(';
            backtrace(n, leftUsed+1, rightUsed, k+1, path);
        }
        if(leftUsed>rightUsed && rightUsed<n) {
            path[k] = ')';
            backtrace(n, leftUsed, rightUsed+1, k+1, path);
        }
    }

}
