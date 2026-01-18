package org.example.dfs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 课程表 是否可以完成课程 课程之间存在依赖关系
 */
public class CourseCanFinish {

    /**
     * 总共需要修几门课程
     * 修课程之间的依赖关系，前边的课程没修完，后边的课程不能修
     */
    public boolean courseCanFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adjs = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++) {
            adjs[i] = new ArrayList<Integer>();
        }

        int[] indegrees = new int[numCourses];
        for(int i=0; i<prerequisites.length; i++) {
            // 修1课程之前，必须修0课程 数组表示修完1可成之后，别的依赖的可成还可以修哪些
            adjs[prerequisites[i][1]].add(prerequisites[i][0]);
            // 当前课程需要依赖几门别的课程
            indegrees[prerequisites[i][0]]++;
        }

        LinkedList<Integer> zeroInDegress = new LinkedList<>();
        for(int i=0; i<indegrees.length; i++) {
            // 开始修没有依赖的可成
            if(indegrees[i] == 0) {
                zeroInDegress.offer(i);
            }
        }

        int zeroInDegreesCount = 0;
        while(!zeroInDegress.isEmpty()) {
            // 没有依赖的可成出来
            int coursei = zeroInDegress.remove();
            zeroInDegreesCount++;
            for(Integer coursej : adjs[coursei]) {
                indegrees[coursej]--;
                // 别的依赖课程都修完了 当前课程也可以修
                if(indegrees[coursej] == 0) {
                    zeroInDegress.add(coursej);
                }
            }
        }

        return zeroInDegreesCount == numCourses;
    }

}
