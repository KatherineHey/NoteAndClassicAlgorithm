# Guess Number Higher or Lower II

We are playing the Guessing Game. The game will work as follows:

1. I pick a number between `1` and `n`.
2. You guess a number.
3. If you guess the right number, **you win the game**.
4. If you guess the wrong number, then I will tell you whether the number I picked is **higher or lower**, and you will continue guessing.
5. Every time you guess a wrong number `x`, you will pay `x` dollars. If you run out of money, **you lose the game**.

Given a particular `n`, return _the minimum amount of money you need to **guarantee a win regardless of what number I pick**_.



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
            maximum = Math.min(maximum, Math.max(minCost(lower, i-1), minCost(i+1, upper)) + i);
        }
        
        dp[lower][upper] = maximum;
        
        return maximum;
    }
}
```

**dp\[i\]\[j\] is the minimal cost to guess from range\(i...j\)**. 

When you choose an x where i &lt;= x &lt;= j, you may find the target number from left i...x-1, or you may find the target number from the x+1...j, because you don't know which way should go, so to guarantee you have enough money to find the target, you need to prepare the more, which is max\(dp\[i\]\[x-1\], dp\[x+1\]\[j\]\).

