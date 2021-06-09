# Enum

class Solution { private static final int BLOCKED = -1; private static final int UNEXPLORED = 0; private static final int PATH = 1; private static final int TARGET = 2; private static final int START = 3;

```java
public int findShortestPath(GridMaster master) {
    if (master.isTarget()) return 0;

    int m = 500;
    int[][] grid = new int[1000][1000];
    int[] target = findTarget(master, grid, m, m);
    grid[m][m] = START;
    return target == null? -1: bfs(grid, new int[]{m,m});
}

public int[] findTarget(GridMaster master, int[][] grid, int r, int c) {
    if (master.isTarget()) {
        grid[r][c] = TARGET;
        return new int[]{r, c};
    }

    int[] res = null;

    for (Direction dir: Direction.values()) {
        if (grid[r+dir.n[0]][c+dir.n[1]] == UNEXPLORED) {
            if (master.canMove(dir.c)) {
                master.move(dir.c);
                grid[r+dir.n[0]][c+dir.n[1]] = PATH;

                int[] target = findTarget(master, grid, r + dir.n[0], c+dir.n[1]);

                if (target != null)
                    res = target;

                master.move(dir.o);
            } else {
                grid[r+dir.n[0]][c+dir.n[1]] = BLOCKED;
            }
        }
    }

    return res;
}

public int bfs(int[][] grid, int[] start) {
    Queue<int[]> paths = new LinkedList<>();

    paths.add(start);
    int distance = 1;

    while (!paths.isEmpty()) {
        int size = paths.size();
        for (int i = 0; i < size; i++) {
            int[] cur = paths.poll();

            // check the neighbors of current spot
            for (Direction d : Direction.values()) {
                int r2 = cur[0] + d.n[0];
                int c2 = cur[1] + d.n[1];

                if (grid[r2][c2] == TARGET) {
                    return distance;
                }

                if (grid[r2][c2] == PATH) {
                    //System.out.println(r2+" "+c2);

                    paths.add(new int[] {r2, c2});
                    grid[r2][c2] = BLOCKED; // already mark it when adding to queue to avoid duplicates in queue
                }
            }
        }

        distance++;
    }

    return -1;
}

enum Direction {
    UP('U', 'D', new int[]{0,1}),
    DOWN('D', 'U', new int[]{0,-1}),
    LEFT('L', 'R', new int[]{-1,0}),
    RIGHT('R', 'L', new int[]{1,0});

    public char c;
    public char o;
    public int[] n;

    Direction(char c, char o, int[] n) {
        this.c = c;
        this.o = o;
        this.n = n;
    }            
}
```

}

