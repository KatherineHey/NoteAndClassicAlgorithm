# Minimum \(Maximum\) Path to Reach a Target



#### Statement

> Given a target find minimum \(maximum\) cost / path / sum to reach the target.

#### Approach

> Choose minimum \(maximum\) path among all possible paths before the current state, then add value for the current state.

```text
routes[i] = min(routes[i-1], routes[i-2], ... , routes[i-k]) + cost[i]
```

Generate optimal solutions for all values in the target and return the value for the target.

```text
for (int i = 1; i <= target; ++i) {
   for (int j = 0; j < ways.size(); ++j) {
       if (ways[j] <= i) {
           dp[i] = min(dp[i], dp[i - ways[j]] + cost / path / sum) ;
       }
   }
}
 
return dp[target]
```

#### Similar Problems

[322. Coin Change](https://leetcode.com/problems/coin-change/) `Medium`

```text
for (int j = 1; j <= amount; ++j) {
   for (int i = 0; i < coins.size(); ++i) {
       if (coins[i] <= j) {
           dp[j] = min(dp[j], dp[j - coins[i]] + 1);
       }
   }
}
```

