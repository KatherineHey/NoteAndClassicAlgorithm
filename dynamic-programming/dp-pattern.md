---
description: 动态规划解题套路框架
---

# DP Pattern

{% embed url="https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-ji-ben-ji-qiao/dong-tai-gui-hua-xiang-jie-jin-jie" %}

三要素：**重叠子问题 + 最优子结构 + 状态转移方程**

**明确 base case -&gt; 明确「状态」-&gt; 明确「选择」 -&gt; 定义 dp 数组/函数的含义**

\*\*\*\*

例题： 给一个array的coin，每种面试无限量， 得到target amount最少需要的硬币个数

1. 确定【base case】amount为的时候算法返回0
2. 确定【状态】唯一的状态是目标金额amount
3. 确定【选择】也就是导致【状态】产生变化的行为。每选择一个硬币，即相当于减少了目标金额。所以说所有硬币的面值，就是【选择】。
4. 明确【dp函数/数组的定义】dp\(n\)的定义：输入一个目标金额n，返回抽出目标金额n的最少硬币数量。

```cpp
int coinChange(vector<int>& coins, int amount) {
    // 数组大小为 amount + 1，初始值也为 amount + 1
    vector<int> dp(amount + 1, amount + 1);
    // base case
    dp[0] = 0;
    // 外层 for 循环在遍历所有状态的所有取值
    for (int i = 0; i < dp.size(); i++) {
        // 内层 for 循环在求所有选择的最小值
        for (int coin : coins) {
            // 子问题无解，跳过
            if (i - coin < 0) continue;
            dp[i] = min(dp[i], 1 + dp[i - coin]);
        }
    }
    return (dp[amount] == amount + 1) ? -1 : dp[amount];
}
```

PS：为啥 `dp` 数组初始化为 `amount + 1` 呢，因为凑成 `amount` 金额的硬币数最多只可能等于 `amount`（全用 1 元面值的硬币），所以初始化为 `amount + 1` 就相当于初始化为正无穷，便于后续取最小值。

