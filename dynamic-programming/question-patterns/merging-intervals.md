# Merging Intervals/ Minimax

#### Statement

> Given a set of numbers find an optimal solution for a problem considering the current number and the best you can get from the left and right sides.

#### Approach

> Find all optimal solutions for every interval and return the best possible answer.

```text
// from i to j
dp[i][j] = dp[i][k] + result[k] + dp[k+1][j]
```

Get the best from the left and right sides and add a solution for the current position.

```text
for(int l = 1; l<n; l++) {
   for(int i = 0; i<n-l; i++) {
       int j = i+l;
       for(int k = i; k<j; k++) {
           dp[i][j] = max(dp[i][j], dp[i][k] + result[k] + dp[k+1][j]);
       }
   }
}

return dp[0][n-1];
```

Example question: Guess Number Higher or Lower II

We are playing the Guessing Game. The game will work as follows:

1. I pick a number between `1` and `n`.
2. You guess a number.
3. If you guess the right number, **you win the game**.
4. If you guess the wrong number, then I will tell you whether the number I picked is **higher or lower**, and you will continue guessing.
5. Every time you guess a wrong number `x`, you will pay `x` dollars. If you run out of money, **you lose the game**.

```java
class Solution {
    int[][] dp;
    public int getMoneyAmount(int n) {
        dp = new int[n+1][n+1];
        
        return minCost(1, n);
    }
    
    public int minCost(int lower, int upper) {
        if (lower >= upper) return 0;
        
        if (dp[lower][upper] != 0) return dp[lower][upper];
        
        int maximum = Integer.MAX_VALUE;
        
        for (int i = lower; i <= upper; i++) {
            maximum = Math.min(maximum,
                Math.max(minCost(lower, i-1), minCost(i+1, upper)) + i);
        }
        
        dp[lower][upper] = maximum;
        
        return maximum;
    }
}
```

