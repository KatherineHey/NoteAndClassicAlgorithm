# Dijkstra

```java
public int assignBikes(int[][] workers, int[][] bikes) {
    Queue<Node> pq = new PriorityQueue<>(1,(a,b)->(a.cost-b.cost));
    Set<String> seen = new HashSet<>();
    pq.offer(new Node(0,0,0));
    while (!pq.isEmpty()){
        Node curr = pq.poll();
        String key = "$"+curr.worker+"$"+curr.mask;
        // reason - you can skip if you have already seen this mask
        // is because this is a PQ - and lower cost has already been seen
        // with this exact mask (i.e., those bikes used in some order)
        // then there is no point to consider a higher cost one 
        if (seen.contains(key))
            continue;
        seen.add(key);
        // all workers have a bike if this is true
        if (curr.worker == workers.length)
            return curr.cost;
        // scan all bikes - and create new nodes into the PQ for next worker.
        for(int j = 0; j < bikes.length; j++){
            if ( (curr.mask & (1<<j)) == 0){
                pq.offer( new Node(curr.worker+1, curr.mask | (1 << j), 
                                   curr.cost + getDist(bikes[j], workers[curr.worker]) ));
            }
        }
    }
    return -1;
}
private int getDist(int[] bikepos,int[] wpos){
    return Math.abs(bikepos[0]-wpos[0]) + Math.abs(bikepos[1]-wpos[1]);
}
static class Node {
    int worker;
    int mask;
    int cost;
    public Node(int w,int m,int cost){
        this.worker = w;
        this.mask = m;
        this.cost = cost;
    }
}
```

