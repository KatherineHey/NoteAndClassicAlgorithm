# Shortest common supersequence

Given two strings `str1` and `str2`, return the shortest string that has both `str1` and `str2` as subsequences.  If multiple answers exist, you may return any of them.

_\(A string S is a subsequence of string T if deleting some number of characters from T \(possibly 0, and the characters are chosen anywhere from T\) results in the string S.\)_

```java
public String shortestCommonSupersequence(String str1, String str2) {
    String longestCommonSubsequence = findLongestCommonSubsequence(str1, str2);
    
    // Append all the characters in str1 and str2 but not in longestCommonSubsequence
    StringBuilder sb = new StringBuilder();
    int p1 = 0;
    int p2 = 0;
    for (int i = 0; i < longestCommonSubsequence.length(); i++) {
        while (p1 < str1.length() && str1.charAt(p1) != longestCommonSubsequence.charAt(i)) {
            sb.append(str1.charAt(p1++));
        }
        
        while (p2 < str2.length() && str2.charAt(p2) != longestCommonSubsequence.charAt(i)) {
            sb.append(str2.charAt(p2++));
        }
        
        sb.append(longestCommonSubsequence.charAt(i));
        p1++;
        p2++;
    }
    
     sb.append(str1.substring(p1)).append(str2.substring(p2)); //!!!
    
    return sb.toString();
}

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

