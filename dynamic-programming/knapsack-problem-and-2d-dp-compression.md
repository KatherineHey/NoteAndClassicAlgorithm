---
description: 背包问题
---

# Knapsack problem \(& 2D dp compression\)



经典问题例子：target sum，给定一组array，给一个target，对array中的数字前面放+/ -， 求得到target有多少种方法

```java
public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        
        if (sum < S ||(sum + S) % 2 != 0) return 0;
        
        int target = (sum + S) /2;
        
        // Where dp is 2-d array with dp[i][j] means number of ways to get sum j with first i elements from nums.
        /*
		we use 'n+1' rather than 'n', to make it easier to tackle the boundary.
		dp[i][sum] measn how many ways we choose from [0, i) i.e. [0,1,2,...,i-1] to sum up to 0.
		*/
        int[][] dp = new int[nums.length+1][target + 1]; 
        
        dp[0][0] = 1;

        for (int i = 1; i <= nums.length; i++) {
           //note you need to loop descending, otherwise you'll pickup duplicated partial result along the way
           for (int j = 0; j <= target; ++j){
				       dp[i][j] = dp[i - 1][j];// part 1
				       if (j - nums[i-1] >= 0) //important, nums[i-1] rather than nums[i].
					         dp[i][j] += dp[i-1][j - nums[i-1]];//part 2
			     }
        }
        
        return dp[nums.length][target];
    }
```

DP 2D -&gt; 1D with 2 arrays \(all based on  [OneSheep](https://leetcode.com/OneSheep) [https://leetcode.com/problems/target-sum/discuss/97334/Java-\(15-ms\)-C%2B%2B-\(3-ms\)-O\(ns\)-iterative-DP-solution-using-subset-sum-with-explanation](https://leetcode.com/problems/target-sum/discuss/97334/Java-%2815-ms%29-C%2B%2B-%283-ms%29-O%28ns%29-iterative-DP-solution-using-subset-sum-with-explanation) THANKS!!!!\)

```java
		/*
		pre means dp[i-1], and cur means dp[i].
		*/
		pre[0] = 1;
		for (int i = 0; i < n; ++i){
			//here we can iterate from [0, n-1] or [1, n], all are feasible.
			//we just should be careful to use nums[i] or nums[i-1].
			for (int j = 0; j <= target; ++j){
				cur[j] = pre[j];//part 1
				if (j - nums[i] >= 0)
					cur[j] += pre[j - nums[i]];//part 2
			}
			swap(pre, cur);
		}
		return pre[target];
```

 For part 1 above, `cur[j] = pre[j]`, we need do nothing.  
 But for part 2, `cur[j] += pre[j-nums[i]]`, and `nums[i]` is non-negative, which means `j-nums[i]`should locate on `j`'s left side.  
 So if we want to update the array with just 1 array, we should **update the data on the right first** in this problem.  
 Otherwise, if we update the data on the left first, we can not find the `pre[j-nums[i]]` when we update `cur[j]`.  
 When we use 1 array, we just store `pre[j-nums[i]]` in `cur[j-nums[i]]`, so we should make use of `cur[j-nums[i]]` when it stores `pre[j-nums[i]]`, i.e., we should make use of `cur[j-nums[i]]` before it has been updated.

DP 1D with 2 arrays -&gt; DP 1D with 1 array

```cpp
for (int i = 0; i < n; ++i){
			/*for (int j = target; j >= 0; --j){
				if (j - nums[i] >= 0)
					cur[j] += cur[j - nums[i]];
			}*/
			//See above, so we can simplify.
			for (int j = target; j >= nums[i];--j)
				cur[j] += cur[j - nums[i]];
		}
		
		return cur[target];
```



