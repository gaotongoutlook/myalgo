package org.example.dfs;

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

}
