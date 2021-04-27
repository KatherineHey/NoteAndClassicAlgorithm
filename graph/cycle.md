# Cycle

To detect a cycle in a directed graph, **we'll use a variation of** _**DFS**_ **traversal:**

* Pick up an unvisited vertex _v_ and mark its state as _beingVisited_
* For each neighboring vertex _u_ of _v,_ check:
  * If _u_ is already in the _beingVisited_ state, it clearly means **there exists a backward edge and so a cycle has been detected**
  * If _u_ is yet in an unvisited state, we'll recursively visit _u_ in a depth-first manner
* Update the vertex _v_‘s _beingVisited_ flag to _false_ and its _visited_ flag to _true_

Note that **all the vertices of our graph are initially in an unvisited state as both their** _**beingVisited**_ **and** _**visited**_ **flags are initialized with** _**false**_**.** 

```java
public boolean hasCycle(Vertex sourceVertex) {
    sourceVertex.setBeingVisited(true);

    for (Vertex neighbor : sourceVertex.getAdjacencyList()) {
        if (neighbor.isBeingVisited()) {
            // backward edge exists
            return true;
        } else if (!neighbor.isVisited() && hasCycle(neighbor)) {
            return true;
        }
    }

    sourceVertex.setBeingVisited(false);
    sourceVertex.setVisited(true);
    return false;
}
```

