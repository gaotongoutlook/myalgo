package org.example.dfs;

public class MyGraphMatrix {

    // 顶点的个数
    private int v;
    // 邻接矩阵
    private int matrix[][];

    public MyGraphMatrix(int v) {
        this.v = v;
        // 默认都为false
        matrix = new int[v][v];
    }

    public void addEdge(int s, int t) {
        matrix[s][t] = 1;
        matrix[t][s] = 1;
    }

}
