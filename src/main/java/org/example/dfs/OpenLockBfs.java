package org.example.dfs;

import java.util.*;

/**
 * 最短路径(BFS)解锁
 */
public class OpenLockBfs {

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();
        for(String d : deadends) {
            deadSet.add(d);
        }
        if(deadSet.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int depth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int k = 0;
            while(k < size) {
                String node = queue.poll();
                k++;
                if(node.equals(target)) {
                    return depth;
                }
                List<String> newNodes = getNewNode(node);
                for(String newNode : newNodes) {
                    if(visited.contains(newNode) || deadSet.contains(newNode)) {
                        continue;
                    }
                    queue.add(newNode);
                    visited.add(newNode);
                }
            }
            depth++;
        }
        return -1;
    }

    private List<String> getNewNode(String node) {
        List<String> newNodes = new ArrayList<>();
        int[] change = {-1, 1};
        for(int i=0; i<4; i++) {
            for(int k=0; k<2; k++) {
                char[] newNode = new char[4];
                for(int j=0; j<i; j++) {
                    newNode[j] = node.charAt(j);
                }
                for(int j=i+1; j<4; j++) {
                    newNode[j] = node.charAt(j);
                }
                String newC = (((node.charAt(i)-'0') + change[k] + 10) % 10) + "";
                newNode[i] = newC.charAt(0);
                newNodes.add(new String(newNode));
            }
        }
        return newNodes;
    }

}
