package org.example.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 */
public class RestoreIpAddresses {
    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrace(s, 0, 0, new ArrayList<>());
        return result;
    }

    private void backtrace(String s, int k, int step, ArrayList<Integer> path) {
        if(k==s.length() && step==4) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<3; i++) {
                sb.append(path.get(i)+'.');
            }
            sb.append(path.get(3));
            result.add(sb.toString());
            return;
        }

        if(step>=4 || k>=s.length()) {
            return;
        }

        int val = 0;
        // 1位数
        if(k<s.length()) {
            val = val*10 + (s.charAt(k)-'0');
            if(val <= 255) {
                path.add(val);
                backtrace(s, k+1, step+1, path);
                path.remove(path.size()-1);
            }
        }

        if(s.charAt(k) == '0') {
            return;
        }

        // 2位数
        if(k+1<s.length()) {
            val = val*10 + (s.charAt(k+1)-'0');
            if(val<=255) {
                path.add(val);
                backtrace(s, k+2, step+1, path);
                path.remove(path.size()-1);
            }
        }

        // 3位数
        if(k+2<s.length()) {
            val = val*10 + (s.charAt(k+2)-'0');
            if(val<=255) {
                path.add(val);
                backtrace(s, k+3, step+1, path);
                path.remove(path.size()-1);
            }
        }
    }
}
