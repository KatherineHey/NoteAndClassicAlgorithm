# Longest Palindromic Subsequence

Given a string `s`, find _the longest palindromic **subsequence**'s length in_ `s`.

A **subsequence** is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

**Example 1:**

```text
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
```

```java
public int longestPalindromeSubseq(String s) {
    int len = s.length();
    
    int[][] dp = new int[len][len];
    
    for (int i = 0; i < len; i++) {
        dp[i][i] = 1;
        
        for (int j = i-1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i-1][j+1]+2;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j+1]);
            }
        }
    }
    
    return dp[len - 1][0];
}
```

