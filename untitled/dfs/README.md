# DFS



489\. Robot Room CleanerHard

Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

```
interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
```

**Example:**

```
Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
```

```java
private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<Pair<Integer, Integer>> visited = new HashSet();
    public void cleanRoom(Robot robot) {
        clean(robot, 0, 0,0);
    }
    
    private void clean(Robot robot, int curDirection, int x, int y) {
        robot.clean();
        visited.add(new Pair(x, y));
        
        for (int i = curDirection; i< curDirection+4; i++) {
            int nx = x + directions[i % 4][0];
            int ny = y + directions[i % 4][1];
            
            if (!visited.contains(new Pair(nx, ny)) && robot.move()) {
                clean(robot, i % 4, nx, ny);
            }
            
            robot.turnRight();
        }
        
        // back track
        robot.turnRight(); robot.turnRight();
        robot.move();
        robot.turnRight(); robot.turnRight();
    }
```
