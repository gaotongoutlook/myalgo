package org.example.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class CanReachJump {

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        boolean reached = false;
        canReachDfs(arr, start, visited, reached);
        return reached;
    }

    private void canReachDfs(int[] arr, int curi, boolean[] visited, boolean reached) {
        if(reached) {
            return;
        }
        if(arr[curi]==0) {
            reached = true;
            return;
        }
        visited[curi] = true;
        int moveToLeft = curi - arr[curi];
        if(moveToLeft>=0 && moveToLeft<arr.length && !visited[moveToLeft]) {
            canReachDfs(arr, moveToLeft, visited, reached);
        }
        int moveToRight = curi + arr[curi];
        if(moveToRight>=0 && moveToRight<arr.length && !visited[moveToRight]) {
            canReachDfs(arr, moveToRight, visited, reached);
        }
    }

    // BFS解法
    public boolean canReachBfs(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int index = queue.poll();

            // 如果找到值为0的位置
            if(arr[index] == 0) {
                return true;
            }

            // 向右跳
            int right = index + arr[index];
            if(right < n && !visited[right]) {
                visited[right] = true;
                queue.offer(right);
            }

            // 向左跳
            int left = index - arr[index];
            if(left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.offer(left);
            }
        }

        return false;
    }

}
