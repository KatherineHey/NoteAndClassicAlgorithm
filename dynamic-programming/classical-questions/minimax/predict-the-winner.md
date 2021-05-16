# Predict the winner

You are given an integer array `nums`. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of `0`. At each turn, the player takes one of the numbers from either end of the array \(i.e., `nums[0]` or `nums[nums.length - 1]`\) which reduces the size of the array by `1`. The player adds the chosen number to their score. The game ends when there are no more elements in the array.

Return `true` if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return `true`. You may assume that both players are playing optimally.

**Example 1:**

```text
Input: nums = [1,5,2]
Output: false
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return false.
```

```java
public boolean PredictTheWinner(int[] nums) {
        int n = nums.length, sum = 0;
	if(n % 2 == 0) return true;
        int[][] dp = new int[n][n];
        for(int i=0; i < n; i++) {
            dp[i][i] = nums[i];
            sum += nums[i];
        }

        for(int j = 0; j < n; j++){
            for(int i = j - 1; i >= 0; i--){
                int a = (i + 1 < n && j - 1 >= 0) ? dp[i + 1][j - 1] : 0;
		            int b = (i + 2 < n) ? dp[i + 2][ j] : 0;
            		int c = (j - 2 >= 0) ? dp[i][j - 2] : 0;
                dp[i][j] = Math.max(Math.min(a, b) + nums[i], Math.min(a, c) + nums[j]);
            }
        }

        return dp[0][n - 1] * 2 >= sum;
    }
```

The goal is maximize the sum of first player.

We have a numeric array from `0 ~ n`, `i` and `j` is the index. `dp[i][j]` means the max sum we can get if the array starts from `i` and ends to `j`.

So `dp[i][i]` means only one coin and we pick firstly, `dp[i][i+1]` means there are two coins, we pick the larger one.

To maximum our gain, `dp[i][j] = max( nums[i] + dp[i + 1][j], dp[i][j - 1] + nums[j])`, since we either will pick the `i` or `j` coin. But here `dp[i + 1][j]` and `dp[i][j - 1]` are not the values directly available for us, it depends on the move that our opponent make.

Then we assume our opponent is as good as we are and always makes optimize move. The worse case is that we will get the minimal value out of all possible situation after our opponent makes its move.

So the correct dp formula would be  
 `dp[i][j] = max( min (dp[i + 1][j - 1], dp[i + 2][ j]) + v[i], min (dp[i][j - 2], dp[i + 1][ j - 1]) + v[j]})` .  
 If we pick `i`, then our opponent need to deal with subproblem `dp[i + 1][j]`, it either picks from `i + 2` or `j - 1`. Similarly, If we pick `j`, then our opponent need to deal with subproblem `dp[i][j - 1]` , it either pick from `i + 1` or `j - 2`. We take the worse case into consideration so use min\(\) here.

