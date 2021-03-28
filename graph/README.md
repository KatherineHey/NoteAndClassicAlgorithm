# Graph

Dijkstra's algorithm

* shortest path between 2 nodes
* 从固定起点出发的最短路径树

```text
 1  function Dijkstra(Graph, source):
 2
 3      create vertex set Q
 4
 5      for each vertex v in Graph:            
 6          dist[v] ← INFINITY                 
 7          prev[v] ← UNDEFINED                
 8          add v to Q                     
 9      dist[source] ← 0                       
10     
11      while Q is not empty:
12          u ← vertex in Q with min dist[u]   
13                                             
14          remove u from Q
15         
16          for each neighbor v of u:           // only v that are still in Q
17              alt ← dist[u] + length(u, v)
18              if alt < dist[v]:              
19                  dist[v] ← alt
20                  prev[v] ← u
21
22      return dist[], prev[]
```





Kruskal's algorithm 

* smallest spanning tree
* 按照边的权重顺序（从小到大）将边加入生成树中，但是若加入该边会与生成树形成环则不加入该边。直到树中含有![V-1](https://wikimedia.org/api/rest_v1/media/math/render/svg/cf99922c8908cda9655d87b4a60c0cf3923e0e12)条边为止。这些边组成的就是该图的最小生成树。



