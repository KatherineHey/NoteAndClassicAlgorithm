# 0-1 Knapsack problem



416. Partition Equal Subset Sum

Given a **non-empty** array `nums` containing **only positive integers**, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

**Example 1:**

```text
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
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



