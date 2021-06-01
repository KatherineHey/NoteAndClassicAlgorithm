# Best Time to Buy and Sell Stock III



You are given an array `prices` where `prices[i]` is the price of a given stock on the `ith` day.

Find the maximum profit you can achieve. You may complete **at most two transactions**.

**Note:** You may not engage in multiple transactions simultaneously \(i.e., you must sell the stock before you buy again\).

**Example 1:**

```text
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
```

It's not difficult to get the DP recursive formula:

```text
dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
```

For k transactions, on i-th day,  
 if we don't trade then the profit is same as previous day dp\[k, i-1\];  
 and if we bought the share on j-th day where j=\[0..i-1\], then sell the share on i-th day then the profit is prices\[i\] - prices\[j\] + dp\[k-1, j-1\] .  
 Actually j can be i as well. When j is i, the one more extra item prices\[i\] - prices\[j\] + dp\[k-1, j\] = dp\[k-1, i\] looks like we just lose one chance of transaction.

```java
public int maxProfit(int[] prices) {
    // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
    int len = prices.length;
    int[][] dp = new int[3][len];
    
    for (int k = 1; k < 3; k++) {
        int min = prices[0];
        for (int i = 1; i < len; i++) {
            min = Math.min(min, prices[i] - dp[k-1][i-1]);
            
            dp[k][i] = Math.max(dp[k][i-1], prices[i]-min);
        }
    }
    
    return dp[2][len-1];
}
```

