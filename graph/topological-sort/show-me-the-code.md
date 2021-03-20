---
description: 207. Course Schedule
---

# Show me the code :\)

BFS

```java
public boolean canFinish(int n, int[][] prerequisites) {
    ArrayList<Integer>[] G = new ArrayList[n];
    int[] degree = new int[n];
    ArrayList<Integer> bfs = new ArrayList();
    for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
    for (int[] e : prerequisites) {
        G[e[1]].add(e[0]);
        degree[e[0]]++;
    }
    for (int i = 0; i < n; ++i) if (degree[i] == 0) bfs.add(i);
    for (int i = 0; i < bfs.size(); ++i)
        for (int j: G[bfs.get(i)])
            if (--degree[j] == 0) bfs.add(j);
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
        
        if(visited[eligibleCourse] == 1) return false; // has been visited while visiting its children - cycle !!!!
        if(visited[eligibleCourse]  == 0) { // not visited
           if (!dfs(eligibleCourse,courses, visited)) return false; 
        }

    }
    
    visited[course] = 2; // mark it done visiting
    return true;
}
```

