---
description: Find the shortest path between two nodes.
---

# Dijkstra's algorithm 两点之间最短路径

I probably can't provide a better explanation than wiki, so here is wiki :)

Note:&#x20;

1. if you are looking for the shortest path from a to b, **AND all the weights are non-negative**, then you can end early as soon as you reach the target node b.
2. Dijkstra provides the shortest path with a **fixed starting node**. If you need minimum spanning tree, then Kruskal fits better.

| Dijkstra's algorithm to find the shortest path between a and b. It picks the unvisited vertex with the lowest distance, calculates the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller. Mark visited (set to red) when done with neighbors. |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |

|                                                                                                                                            |                                                                                                                                                                                                                                                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Class                                                                                                                                      | <p><a href="https://en.wikipedia.org/wiki/Search_algorithm">Search algorithm</a><br><a href="https://en.wikipedia.org/wiki/Greedy_algorithm">Greedy algorithm</a><br><a href="https://en.wikipedia.org/wiki/Dynamic_programming">Dynamic programming</a><a href="https://en.wikipedia.org/wiki/Dijkstra&#x27;s_algorithm#cite_note-1">[1]</a></p> |
| Data structure                                                                                                                             | <p><a href="https://en.wikipedia.org/wiki/Graph_(data_structure)">Graph</a><br>Usually used with <a href="https://en.wikipedia.org/wiki/Priority_queue">Priority queue</a>/<a href="https://en.wikipedia.org/wiki/Heap_(data_structure)">Heap</a> for optimization</p>                                                                            |
| [Worst-case](https://en.wikipedia.org/wiki/Best,\_worst\_and\_average\_case) [performance](https://en.wikipedia.org/wiki/Time\_complexity) | Θ(\|E\|+\|V\|log⁡\|V\|)![{\displaystyle \Theta (\|E\|+\|V\|\log \|V\|)}](https://wikimedia.org/api/rest\_v1/media/math/render/svg/e22162be85d06b346f3b7f7aad9746da0c1019c9)                                                                                                                                                                       |

```java
package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class MST_Dijkstra {
    class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return node1.cost - node2.cost;
        }
    }

    private int distance[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node>> adj;

    public MST_Dijkstra(int V) {
        this.V = V;
        distance = new int[V];
        pq = new PriorityQueue<Node>(V, new Node());
        settled = new HashSet<>();
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adj, int source_node) {
        this.adj = adj;

        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        settled.add(source_node);
        distance[source_node] = 0;

        pq.add(new Node(source_node, 0));

        while (settled.size() != V) {
            int n = pq.poll().node;
            settled.add(n);
            relaxAllNeighbors(n);
        }
    }

    // Releax all neighbors of n
    public void relaxAllNeighbors(int u) {
        int editDistance = -1;
        int newDistance = -1;

        List<Node> allNeighbors = adj.get(u);
        for (int i = 0; i < allNeighbors.size(); i++) {
            Node v = allNeighbors.get(i);
            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                editDistance = v.cost;
                newDistance = distance[u] + editDistance;

                if (newDistance < distance[v.node])
                    distance[v.node] = newDistance;

                pq.add(v);
            }
        }
    }

    // Driver code
    public static void main(String arg[]) {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<List<Node>>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Calculate the single source shortest path
        MST_Dijkstra dpq = new MST_Dijkstra(V);

        // Inputs for the DPQ graph
        adj.get(0).add(dpq.new Node(1, 9));
        adj.get(0).add(dpq.new Node(2, 6));
        adj.get(0).add(dpq.new Node(3, 5));
        adj.get(0).add(dpq.new Node(4, 3));

        adj.get(2).add(dpq.new Node(1, 2));
        adj.get(2).add(dpq.new Node(3, 4));

        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.distance.length; i++)
            System.out.println(source + " to " + i + " is " + dpq.distance[i]);
    }
}

```
