---
description: 416. Partition Equal Subset Sum
---

# 0-1 Knapsack problem





Given a **non-empty** array `nums` containing **only positive integers**, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

**Example 1:**

```text
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
```

```java
public boolean canPartition(int[] nums) {
    int sum = 0;
    
    for (int num : nums) {
        sum += num;
    }
    
    if ((sum & 1) == 1) {
        return false;
    }
    sum /= 2;

    int n = nums.length;
    boolean[][] dp = new boolean[n+1][sum+1];
    for (int i = 0; i < dp.length; i++) {
        Arrays.fill(dp[i], false);
    }
    
    dp[0][0] = true;
    
    for (int i = 1; i < n+1; i++) {
        dp[i][0] = true;
    }
    for (int j = 1; j < sum+1; j++) {
        dp[0][j] = false;
    }
    
    for (int i = 1; i < n+1; i++) {
        for (int j = 1; j < sum+1; j++) {
            dp[i][j] = dp[i-1][j];
            if (j >= nums[i-1]) {
                dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
            }
        }
    }
   
    return dp[n][sum];
}
```

```java
/**
    Actually, this is a 0/1 knapsack problem, 
    for each number, we can pick it or not. 
    Let us assume dp[i][j] means whether the specific sum j can be gotten 
    from the first i numbers. 
    If we can pick such a series of numbers from 0-i whose sum is j, 
    dp[i][j] is true, otherwise it is false.

    Base case: dp[0][0] is true; (zero number consists of sum 0 is true)
    
    Transition function: 
    For each number, if we don't pick it, dp[i][j] = dp[i-1][j], 
    which means if the first i-1 elements has made it to j, 
    dp[i][j] would also make it to j (we can just ignore nums[i]). 
    https://leetcode.com/problems/partition-equal-subset-sum/discuss/
    90592/01-knapsack-detailed-explanation
    */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) 
            sum += n;
        
        if (sum % 2 != 0) return false;
        
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        
        return dp[sum];
    }
```

![](../.gitbook/assets/image%20%286%29.png)

![](../.gitbook/assets/image%20%288%29.png)

**第二层循环的顺序0-1区别于complete knapsack的原因**

![](../.gitbook/assets/image%20%287%29.png)

