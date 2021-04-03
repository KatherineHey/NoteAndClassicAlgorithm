---
description: Classical backtracking questions and code
---

# Permutations

**Module**

1. Paths to take - 可选路径
2. Change && change back - 修改状态 & 改回原有状态
3. End condition - 结束条件



Given an array `nums` of distinct integers, return _all the possible permutations_. You can return the answer in **any order**.

**Example 1:**

```text
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```



```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(nums, result, new ArrayList<Integer>());
        
        return result;
    }
    
    public void backtrack(int[] nums, List<List<Integer>> result, List<Integer> cur) {
        if (cur.size() == nums.length)
            result.add(new ArrayList<>(cur));
        else {
            for (int i = 0 ; i < nums.length; i++) {
                if (cur.contains(nums[i])) continue; //since it's all distinct integers
                cur.add(nums[i]);
                backtrack(nums, result, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
```



Another sample question: 1079. Letter Tile Possibilities

