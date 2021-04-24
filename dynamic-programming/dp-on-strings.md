# DP on strings



#### Statement

> Given two strings `s1` and `s2`, return `some result`.

#### Approach

> Most of the problems on this pattern requires a solution that can be accepted in O\(n^2\) complexity.



1143. Longest Common SubsequenceMedium

Given two strings `text1` and `text2`, return _the length of their longest **common subsequence**._ If there is no **common subsequence**, return `0`.

A **subsequence** of a string is a new string generated from the original string with some characters \(can be none\) deleted without changing the relative order of the remaining characters.

* For example, `"ace"` is a subsequence of `"abcde"`.

A **common subsequence** of two strings is a subsequence that is common to both strings.

**Example 1:**

```text
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
```

```java
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[m][n];
    }
```

```java
public String findLongestCommonSubsequence (String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        
        String[][] dp = new String[len1+1][len2+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], "");
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+str1.charAt(i-1);
                } else {
                    dp[i][j] = dp[i-1][j].length() >dp[i][j-1].length() ? dp[i-1][j]: dp[i][j-1];
                }
            }
        }
        
        return dp[len1][len2];
    }
```

