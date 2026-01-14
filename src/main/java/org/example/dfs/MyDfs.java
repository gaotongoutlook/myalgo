package org.example.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyDfs {

    private int v;
    private boolean[] visited = new boolean[v];
    private List<Integer> result = new ArrayList<>();
    // 邻接表
    private LinkedList<Integer> adj[] = new LinkedList[v];
    {
        for(int i=0; i<v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public List<Integer> dfs(int s, int t) {
        dfs_r(s, t, new ArrayList<>());
        return result;
    }

    private void dfs_r(int s, int t, List<Integer> path) {
        if(s==t) {
            result = new ArrayList<>(path);
            return;
        }
        visited[s] = true;
        path.add(s);
        for(int i=0; i<adj[s].size(); i++) {
            int q = adj[s].get(i);
            if(!visited[q]) {
                dfs_r(q, t, path);
            }
        }
        path.remove(path.size()-1);
    }

}
