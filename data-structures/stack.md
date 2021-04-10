---
description: 单调递增stack
---

# Stack

503. Next Greater Element II

Given a circular integer array `nums` \(i.e., the next element of `nums[nums.length - 1]` is `nums[0]`\), return _the **next greater number** for every element in_ `nums`.

The **next greater number** of a number `x` is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return `-1` for this number.

tip from Lee:

Loop once, we can get the Next Greater Number of a normal array. 

Loop twice, we can get the Next Greater Number of a circular array

```java
public int[] nextGreaterElements(int[] nums) {
    Stack<Integer> dec = new Stack<>();//value, index
    int[] numsNextGreater = new int[nums.length];
    Arrays.fill(numsNextGreater, -1);
    
    for (int i = 0; i < nums.length * 2; i++) {
        int j = i % nums.length;
        while (!dec.isEmpty() && nums[dec.peek()] < nums[j]) {
            numsNextGreater[dec.pop()] = nums[j];
        }
        
        dec.push(j);
    }
    
    return numsNextGreater;
}
```

