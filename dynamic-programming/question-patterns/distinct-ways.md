# Distinct ways



#### Statement

> Given a target find a number of distinct ways to reach the target.

#### Approach

> Sum all possible ways to reach the current state.

```text
routes[i] = routes[i-1] + routes[i-2], ... , + routes[i-k]
```

Generate sum for all values in the target and return the value for the target.

```text
for (int i = 1; i <= target; ++i) {
   for (int j = 0; j < ways.size(); ++j) {
       if (ways[j] <= i) {
           dp[i] += dp[i - ways[j]];
       }
   }
}
 
return dp[target]
```



You have `d` dice, and each die has `f` faces numbered `1, 2, ..., f`.

Return the number of possible ways \(out of `fd` total ways\) **modulo `10^9 + 7`** to roll the dice so the sum of the face up numbers equals `target`.

**Example 1:**

```text
Input: d = 1, f = 6, target = 3
Output: 1
Explanation: 
You throw one die with 6 faces.  There is only one way to get a sum of 3.
```

```java
public int numRollsToTarget(int d, int f, int target) {
    int MASK = 1000000007;
        
    int[][] dp = new int[d + 1][target + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= d; i++) {
        for (int j = 0; j <= target; j++) {
            if (target > f * d) continue;
            for (int k = 1; k <= f; k++) {
                if (j - k >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MASK;
                }
            }
        }
    }

    return dp[d][target];
}
```

