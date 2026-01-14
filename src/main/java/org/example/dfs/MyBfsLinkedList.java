package org.example.dfs;

import java.util.*;

/**
 * 邻接表实现BFS
 * BFS特点：
 *  使用队列实现
 *  按层级遍历
 *  能找到最短路径
 *  适合无权图的最短路径问题
 */
public class MyBfsLinkedList {

    /**
     * 基本的BFS遍历
     */
    public static void bfsTraversal(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.println("BFS遍历结果:");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : graph.adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * 带层级的BFS，可以计算最短路径
     */
    public static void bfsWithLevel(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        int[] distance = new int[graph.vertices];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        distance[startVertex] = 0;
        queue.add(startVertex);

        System.out.println("\nBFS（带层级信息）:");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.printf("顶点 %d (距离: %d)\n", current, distance[current]);

            for (int neighbor : graph.adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * 查找从起点到目标的最短路径
     */
    public static List<Integer> bfsShortestPath(MyGraph graph, int start, int target) {
        if (start == target) {
            return Arrays.asList(start);
        }

        boolean[] visited = new boolean[graph.vertices];
        int[] parent = new int[graph.vertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int current = queue.poll();

            for (int neighbor : graph.adjList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.add(neighbor);

                    if (neighbor == target) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // 重建路径
        if (found) {
            List<Integer> path = new ArrayList<>();
            for (int at = target; at != -1; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);
            return path;
        }

        return Collections.emptyList(); // 没有找到路径
    }

}
