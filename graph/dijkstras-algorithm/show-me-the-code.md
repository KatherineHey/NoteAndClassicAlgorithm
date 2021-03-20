---
description: '1786. Number of Restricted Paths From First to Last Node, Dijkstra + DFS'
---

# Show me the code :\)

```java
public int countRestrictedPaths(int n, int[][] edges) {
        if (n == 1) return 0;
        List<int[]>[] graph = new List[n+1];
        
        // node1, [weight, node2]
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] e: edges) {
            graph[e[0]].add(new int[] {e[2], e[1]});
            graph[e[1]].add(new int[] {e[2], e[0]});
        }
        
        int[] dist = dijstra(n, graph);
        
        return dfs(1, n, graph, dist, new Integer[n+1]);
    }
    
    // find shortest path from last node to others
    public int[] dijstra(int n, List<int[]>[] graph) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[] {0, n});
        
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int d = cur[0];
            int u = cur[1];
            
            // !!!dijstra looks for the shortest edge in unvisited vertex
            if (d != dist[u]) continue; 
            for (int[] neighbor: graph[u]) {
                int w = neighbor[0];
                int v = neighbor[1];
                if (dist[v] > w+dist[u]) { //!!!
                    dist[v] = w+dist[u];
                    minHeap.offer(new int[] {dist[v], v});
                }
            }
        }
        
        return dist;
    }
    
    // memo memorize number of path to reach n from src
    public int dfs(int src, int n, List<int[]>[] graph, int[] dist, Integer[] memo) {
        if (memo[src] != null) return memo[src];
        if (src == n) return 1; // Found a path to reach to destination
        
        int ans = 0;
        for (int[] neighbor: graph[src]) {
            int w = neighbor[0];
            int u = neighbor[1];
            
            if (dist[src] > dist[u]) {
                ans = (ans + dfs(u, n, graph, dist, memo)) % 1000000007;
            }
        }
        
        return memo[src] = ans; //!!!!
    }
```

