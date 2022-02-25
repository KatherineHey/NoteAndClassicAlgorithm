# Campus bike II

1066\. Campus Bikes IIMedium

On a campus represented as a 2D grid, there are `N` workers and `M` bikes, with `N <= M`. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points `p1` and `p2` is `Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|`.

Return _the minimum possible sum of Manhattan distances between each worker and their assigned bike_.

```java
public int assignBikes(int[][] workers, int[][] bikes) {
    int[] used = new int[1<<bikes.length];
    int res = dfs(workers, 0, bikes, 0, used);
    return res;
}

//dfs returns the smallest Manhattan distance for the rest of the unassigned workers, given the current state of visited bikes.
public int dfs(int[][] workers, int cnt, int[][] bikes, int usedMask, int[] used) {
    if (cnt == workers.length) return 0;
    if (used[usedMask] != 0) return used[usedMask];
    
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < bikes.length; i++) {
        if ((usedMask & (1<<i)) == 0) {
            usedMask |= (1<<i); // set the bike as used in the bit
            min = Math.min(min, manhattanDistance(workers[cnt], bikes[i]) + dfs(workers, cnt+1, bikes, usedMask, used));
            usedMask &= ~(1<<i); // unset the bike as unused in the bit
        }
    }
    
    used[usedMask] = min;
    return min;
}

public int manhattanDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
}
```
