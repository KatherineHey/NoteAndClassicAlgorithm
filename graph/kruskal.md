---
description: finds a minimum spanning forest of an undirected edge-weighted graph
---

# Kruskal

\(From Wiki\)

Kruskal's algorithm finds a minimum spanning forest of an **undirected** edge-weighted graph. If the grap is connected, it finds a minimum spanning tree. \(A minimum spanning tree of a connected graph is a subset of the edges that forms a tree that includes every vertext, where the sum of the weights of all the edges in the tree is minimized. For a disconnected graph, a minimum spanning forest is composed of a minimum spanning tree for each connected component.\) It is a greedy algorithm in graph theory as in each step it adds the next lowest-weight edge that will not form a cycle to the minimum spanning forest. 



```text
algorithm Kruskal(G) is
    F:= ∅
    for each v ∈ G.V do
        MAKE-SET(v)
    for each (u, v) in G.E ordered by weight(u, v), increasing do
        if FIND-SET(u) ≠ FIND-SET(v) then
            F:= F ∪ {(u, v)}
            UNION(FIND-SET(u), FIND-SET(v))
    return F
```

For a graph with E edges and V vertices, Kruskal's algorithm can be shown to run in [O](https://en.wikipedia.org/wiki/Big-O_notation)\(E [log](https://en.wikipedia.org/wiki/Binary_logarithm) E\) time, or equivalently, O\(E log V\) time, all with simple data structures. These running times are equivalent because:

* E is at most![V^{2}](https://wikimedia.org/api/rest_v1/media/math/render/svg/dc8bf8999387969208f85073f17c00954a131160) and![{\displaystyle \log V^{2}=2\log V\in O\(\log V\)}](https://wikimedia.org/api/rest_v1/media/math/render/svg/a654a11335f727f6506afda8b9bcc26ddf3bf16d).
* Each isolated vertex is a separate component of the minimum spanning forest. If we ignore isolated vertices we obtain V ≤ 2E, so log V is![{\displaystyle O\(\log E\)}](https://wikimedia.org/api/rest_v1/media/math/render/svg/debcb763866bfd9ef59bcd947fb637f07a006f34).

We can achieve this bound as follows: first sort the edges by weight using a [comparison sort](https://en.wikipedia.org/wiki/Comparison_sort) in O\(E log E\) time; this allows the step "remove an edge with minimum weight from S" to operate in constant time. Next, we use a [disjoint-set data structure](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) to keep track of which vertices are in which components. We place each vertex into its own disjoint set, which takes O\(V\) operations. Finally, in worst case, we need to iterate through all edges, and for each edge we need to do two 'find' operations and possibly one union. Even a simple disjoint-set data structure such as disjoint-set forests with union by rank can perform O\(E\) operations in O\(E log V\) time. Thus the total time is O\(E log E\) = O\(E log V\).



```java
package graph;

import java.util.PriorityQueue;

/**
 * Kruskal's algorithm finds a minimum spanning forest of an undirected edge-weighted graph.
 * If the graph is connected, it finds a minimum spanning tree.
 * It is a greedy algorithm in graph theory as in each step it adds the next lowest-weight edge that will not 
 * form a cycle to the minimum spanning forest
 * @author Katherine
 *
 */
public class MST_Kruskal {
    int[] parents;
    int islands;

    public int minCostConnectPoints(int[][] points) {
        // 1. get and save all the weight of all the edges in points
        int v = points.length;
        int[][] edges = new int[v][v];
        islands = v;
        parents = new int[v];
        int cost = 0;

        // initialize uf
        for (int i = 0 ; i < v; i++) parents[i] = i;

        // acsending order of edge weight
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->edges[a[0]][a[1]] - edges[b[0]][b[1]]);

        for (int i = 0 ; i < v; i++) {
            for (int j = i+1; j < v; j++) {
                //edge of the point i, j
                int edge = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[i][j] = edge;edges[j][i] = edge;
                pq.add(new int[] {i, j});
            }
        }

        // Kruskal algorithm

        while(!pq.isEmpty() && islands > 1) {
            int[] edge = pq.poll();

            int a = edge[0];
            int b = edge[1];
            if (union(a, b)) {
                islands--;
                cost+= edges[a][b];
            }
        }

        return cost;
    }

    /*
     *   Returns true when two nodes 'a' and 'b' are initially in different
     *   components. Otherwise returns false if they are in the same set.
     */
    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;

        parents[pa] = pb;

        return true;
    }

    public int find(int a) {
        while (parents[a] != a) {
            a = parents[a];
        }

        return a;
    }

    public static void main(String[] args) {
        int[][] points =  {{-14,-14},{-18,5},{18,-10},{18,18},{10,-2}};

        MST_Kruskal mk = new MST_Kruskal();
        System.out.println(mk.minCostConnectPoints(points));
    }
}
```

