package org.example.dfs;

import com.sun.org.apache.bcel.internal.generic.LADD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 水域大小
 * 上下左右以及斜线区域，乳沟都为0则代表同一块儿水域
 */
public class PoodSizes {

    private int count = 0;
    private int n;
    private int m;

    public int[] pondSizes(int[][] land) {
        n = land.length;
        m = land[0].length;
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(land[i][j]==0) {
                    count = 0;
                    pondSizesDfs(land, i, j);
                    result.add(count);
                }
            }
        }
        int[] resultArr = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        Arrays.sort(resultArr);
        return resultArr;
    }

    private void pondSizesDfs(int[][] land, int curi, int curj) {
        count++;
        land[curi][curj] = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1},
                        {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        for(int d=0; d<dirs.length; d++) {
            int newi = curi + dirs[d][0];
            int newj = curj + dirs[d][1];
            if(newi>=0 && newi<n && newj>=0 && newj<m && land[newi][newj]==0) {
                pondSizesDfs(land, newi, newj);
            }
        }
    }

}
