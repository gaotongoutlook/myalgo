package org.example.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 邻接表实现DFS
 * DFS特点：
 *  使用栈（递归或显式栈）
 *  一条路径走到底再回溯
 *  适合检查环、拓扑排序
 *  可以检测连通分量
 */
public class MyDfsLinkedList {

    // 递归实现DFS
    public static void dfsRecursive(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        System.out.println("DFS递归遍历结果:");
        dfsRecursiveHelper(graph, startVertex, visited);
        System.out.println();
    }

    private static void dfsRecursiveHelper(MyGraph graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbor : graph.adjList[vertex]) {
            if (!visited[neighbor]) {
                dfsRecursiveHelper(graph, neighbor, visited);
            }
        }
    }

    // 迭代实现DFS（使用栈）
    public static void dfsIterative(MyGraph graph, int startVertex) {
        boolean[] visited = new boolean[graph.vertices];
        Stack<Integer> stack = new Stack<>();

        System.out.println("DFS迭代遍历结果:");
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");

                // 注意：为了与递归版本结果一致，需要逆序压入栈
                List<Integer> neighbors = new ArrayList<>(graph.adjList[current]);
                Collections.reverse(neighbors);
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    // 检查图中是否有环（有向图）
    public static boolean hasCycle(MyGraph graph) {
        int vertices = graph.vertices;
        boolean[] visited = new boolean[vertices];
        boolean[] recursionStack = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (hasCycleUtil(graph, i, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycleUtil(MyGraph graph, int vertex, boolean[] visited, boolean[] recursionStack) {
        if (recursionStack[vertex]) return true;
        if (visited[vertex]) return false;

        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int neighbor : graph.adjList[vertex]) {
            if (hasCycleUtil(graph, neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack[vertex] = false;
        return false;
    }

    // 拓扑排序（针对有向无环图）
    public static List<Integer> topologicalSort(MyGraph graph) {
        int vertices = graph.vertices;
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void topologicalSortUtil(MyGraph graph, int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int neighbor : graph.adjList[vertex]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(graph, neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }


}
