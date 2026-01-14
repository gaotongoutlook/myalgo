package org.example.dfs;

import java.util.LinkedList;

public class MyGraph {

    // 顶点的个数
    public int vertices;
    // 邻接表
    public LinkedList<Integer> adjList[];

    public MyGraph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for(int i=0; i<vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adjList[s].add(t);
        // 无向图一条边两次
        adjList[t].add(s);
    }

}
