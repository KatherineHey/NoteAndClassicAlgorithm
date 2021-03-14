---
description: 'This is a typical DP problem, but DFS solution is fascinating to checkout :)'
---

# Word break \(DFS VS DP\)



139. Word BreakMedium

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

**Note** that the same word in the dictionary may be reused multiple times in the segmentation.

**Example:**

```text
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
```

```java
public boolean wordBreakDP(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<String>(); // put all the words in a set for faster retrieve
        for (String str: wordDict) {
            set.add(str);
        }
        
        boolean[] exists = new boolean[s.length()+1];
        exists[0] = true;
        
        for (int i = 1; i < s.length()+1; i++) { // substring right border non-inclusive
            for (int j = 0; j < i; j++) {
                // substring start at j, ends at i-1
                if (set.contains(s.substring(j, i)) && exists[j])
                    exists[i] = true;
            }
        }
        
        return exists[s.length()];
    }
```

```java
//https://leetcode.com/problems/word-break/discuss/43819/
//DFS-with-Path-Memorizing-Java-Solution
public boolean wordBreak(String s, Set<String> dict) {
    // DFS
    Set<Integer> set = new HashSet<Integer>();
    return dfs(s, 0, dict, set);
}

private boolean dfs(String s, int index, Set<String> dict, Set<Integer> set){
    // base case
    if(index == s.length()) return true;
    // check memory
    if(set.contains(index)) return false;
    // recursion
    for(int i = index+1;i <= s.length();i++){
        String t = s.substring(index, i);
        if(dict.contains(t))
            if(dfs(s, i, dict, set))
                return true;
            else
                set.add(i);
    }
    set.add(index);
    return false;
}
```

140. Word break II: get all the sequences from words in the dictionary

```java
public List<String> wordBreak(String s, List<String> wordDict) {
    return backtrack(s,wordDict,new HashMap<String, List<String>>());
}
// backtrack returns an array including all substrings derived from s.
public List<String> backtrack(String s, List<String> wordDict, Map<String,List<String>> mem){
    if(mem.containsKey(s)) return mem.get(s);
    List<String> result = new ArrayList<String>();
    for(String word: wordDict)
        if(s.startsWith(word)) {
            String next = s.substring(word.length());
            if(next.length()==0) result.add(word);
            else for(String sub: backtrack(next, wordDict, mem)) result.add(word+" "+sub);
        }
    mem.put(s, result);
    return result;
}
```

