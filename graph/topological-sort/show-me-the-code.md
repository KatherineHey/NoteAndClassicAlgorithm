---
description: 207. Course Schedule
---

# Show me the code :\)

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

* For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return `true` if you can finish all courses. Otherwise, return `false`.

**Example 1:**

```text
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
```



BFS

```java
public boolean canFinish(int n, int[][] prerequisites) {
    ArrayList<Integer>[] G = new ArrayList[n];
    int[] indegree = new int[n];
    ArrayList<Integer> bfs = new ArrayList();
    for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
    for (int[] e : prerequisites) {
        G[e[1]].add(e[0]);
        indegree[e[0]]++;
    }
    for (int i = 0; i < n; ++i) if (indegree[i] == 0) bfs.add(i);
    for (int i = 0; i < bfs.size(); ++i)
        for (int j: G[bfs.get(i)])
            if (--indegree[j] == 0) bfs.add(j);
    return bfs.size() == n;
}
```

DFS

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(numCourses == 0 || prerequisites == null || prerequisites.length == 0) return true; //??
    
    // create the array lists to represent the courses
    List<List<Integer>> courses = new ArrayList<List<Integer>>(numCourses);
    for(int i=0; i<numCourses; i++) {
        courses.add(new ArrayList<Integer>());
    }
    
    // create the dependency graph
    for(int i=0; i<prerequisites.length; i++) {
        courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
    }

    int[] visited = new int[numCourses]; 
    
    // dfs visit each course
    for(int i=0; i<numCourses; i++) {
           if (!dfs(i,courses, visited)) return false; 
    }
    
    return true;
}

private boolean dfs(int course, List<List<Integer>> courses, int[] visited) {    
    visited[course] = 1; // mark it being visited
    List<Integer> eligibleCourses = courses.get(course); // get its children
    
    // dfs its children
    for(int i=0; i<eligibleCourses.size(); i++) {
        int eligibleCourse = eligibleCourses.get(i).intValue();
        
        // has been visited while visiting its children - cycle !!!!     
        if(visited[eligibleCourse] == 1) return false; 
   
        if(visited[eligibleCourse]  == 0) { // not visited
           if (!dfs(eligibleCourse,courses, visited)) return false; 
        }

    }
    
    visited[course] = 2; // mark it done visiting
    return true;
}
```

