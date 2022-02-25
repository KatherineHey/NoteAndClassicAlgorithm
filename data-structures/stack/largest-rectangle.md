---
description: Mono stack
---

# Largest Rectangle



84\. Largest Rectangle in HistogramHard

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return _the area of the largest rectangle in the histogram_.

**Example 1:** ![](https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg)

```
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
```

说的简单一点就是对于每一个柱子，找到左边的比它短的i, 右边比他短的j，然后局部max就是(j - i - 1) \* h

```java
public int largestRectangleArea(int[] h) {
    int len = h.length;
    int i =0, max = 0;
    Stack<Integer> s = new Stack<Integer>();
    
    while (i < len) {
        // As long as the current bar is shorter than the last one in the stack
        // we keep popping out the stack and calculate the area based on
        // the popped bar
        
        while (!s.isEmpty() && h[i] < h[s.peek()]) {
            // tricky part is how to handle the index of the left bound
            max = Math.max(max, h[s.pop()] * (i - (s.isEmpty()? 0: (s.peek()+1))));
        }
        
        s.push(i++);
    }
    
    // finally pop out any bar left in the stack and calculate the area based on it
    // this bit is tricky, also the left border is tricky, left board can take 1 as an example
    while (!s.isEmpty()) {
        max = Math.max(max, h[s.pop()] * (len - (s.isEmpty()? 0: (s.peek()+1))));
    }
    
    return max;
}
```
