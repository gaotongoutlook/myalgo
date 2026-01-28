package com.nowcoder.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 贪心
 *  贪心是一种在每一步选择中都采取当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法
 *  问题的整体最优解可以通过一系列局部最优选择得到
 *  问题的最优解包含其子问题的最优解。这是动态规划和贪心算法都需要的性质
 * 特点：
 *  局部最优选择：每一步都做出当前最优的选择
 *  不可回退：一旦做出选择，就不会改变
 *  高效性：通常时间复杂度较低
 *  不一定得到全局最优解：这是最重要的限制
 * 常用分类：
 *  哈夫曼编码（数据压缩）
 * 	 贪心思想：每次合并频率最小的两个节点
 *  活动选择问题
 * 	 贪心策略：每次选择结束时间最早的活动
 *  找零钱问题（某些币制下）
 * 	 贪心策略：每次选择面值最大的硬币
 *  最小生成树（Prim和Kruskal算法）
 * 	 Kruskal算法：每次选择权重最小的边，且不形成环
 *  单源最短路径（Dijkstra算法）
 * 	 贪心策略：每次选择距离起点最近的未访问节点
 * 常用问题：
 *  哈夫曼编码（数据压缩）
 *  活动选择问题
 *  找零钱问题（某些币制下）
 *  最小生成树（Prim和Kruskal算法）
 *  单源最短路径（Dijkstra算法）
 *  区间调度问题（最多不重叠区间）
 *  跳跃游戏（能否到达终点/最少跳跃次数）
 *  加油站问题（环形路上能否跑完一圈）
 *  分发饼干（满足尽可能多的孩子）
 *  任务调度器（CPU任务调度）
 */
public class MyGreedy {

    /**
     * 分糖果
     * 每个孩子不管得分多少，起码分到一个糖果
     * 评分更高的孩子必须比相邻的孩子获得更多的糖果
     * 第一次从左到右遍历：确保右边评分更高的孩子比左边孩子多一颗糖果
     * 第二次从右到左遍历：确保左边评分更高的孩子比右边孩子多一颗糖果
     * 对每个位置取两次遍历中的最大值
     */
    public int candy (int[] arr) {
        if(arr==null || arr.length==0) {
            return 0;
        }

        int n = arr.length;
        int[] candies = new int[n];

        // 初始化每个孩子分配一个糖果
        for(int i=0; i<n; i++) {
            candies[i] = 1;
        }

        // 从左往右确定评分高的孩子分的糖果多
        for(int i=1; i<n; i++) {
            if(arr[i] > arr[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        // 从右往左确定评分高的孩子分的糖果多
        for(int i=n-2; i>=0; i--) {
            if(arr[i] > arr[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
        }

        int total = 0;
        for(int candy : candies) {
            total += candy;
        }

        return total;
    }

    /**
     * 计算成功举办活动需要多少名主持人
     * @param n int整型 有n个活动
     * @param startEnd int整型二维数组 startEnd[i][0]用于表示第i个活动的开始时间，startEnd[i][1]表示第i个活动的结束时间
     */
    public int minmumNumberOfHost (int n, int[][] startEnd) {
        if(startEnd==null || startEnd.length==0 || startEnd.length!=n) {
            return 0;
        }

        List<int[]> events = new ArrayList<>();
        for(int i=0; i<n; i++) {
            events.add(new int[]{startEnd[i][0], 1});
            events.add(new int[]{startEnd[i][1], -1});
        }
        events.sort((a, b) -> {
            if(a[0] == b[0]) {
                // 倒序排列
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int maxHosts = 0;
        int currentHosts = 0;
        for(int[] event : events) {
            currentHosts += event[1];
            maxHosts = Math.max(maxHosts, currentHosts);
        }

        return maxHosts;
    }

    public int minmumNumberOfHost1 (int n, int[][] startEnd) {
        if(startEnd==null || startEnd.length==0 || startEnd.length!=n) {
            return 0;
        }

        Arrays.sort(startEnd, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // 优先级队列使用到堆 构造小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            int start = startEnd[i][0];
            int end = startEnd[i][1];

            if(!minHeap.isEmpty() && start>=minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(end);
        }

        return minHeap.size();
    }

    public int minmumNumberOfHost2 (int n, int[][] startEnd) {
        if(startEnd==null || startEnd.length==0 || startEnd.length!=n) {
            return 0;
        }

        int[] startTime = new int[n];
        int[] endTime = new int[n];
        for(int i=0; i<n; i++) {
            startTime[i] = startEnd[i][0];
            endTime[i] = startEnd[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int hosts = 0;
        int endIndex = 0;
        for(int i=0; i<n; i++) {
            if(startTime[i] >= endTime[endIndex]) {
                // 当前活动已经结束，可以继续主持下一个活动，否则需要新添加主持人
                endIndex++;
            }else {
                hosts++;
            }
        }

        return hosts;
    }

}
