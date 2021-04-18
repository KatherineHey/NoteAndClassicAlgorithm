# Sliding Window

sliding window

题目特点: 连续的window，求最值

两种套路解法



解法1. 不断扩大右指针，缩小左指针找到最值

题目：

76. Minimum Window Substring

Given two strings `s` and `t`, return _the minimum window in `s` which will contain all the characters in `t`_. If there is no such window in `s` that covers all characters in `t`, return _the empty string `""`_.

**Note** that If there is such a window, it is guaranteed that there will always be only one unique minimum window in `s`.

**Example 1:**

```text
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
```

答案

```text
public String minWindow(String s, String t) {
    int[] tchars = new int[256];

    for (char c : t.toCharArray()) {
        tchars[c]++;
    }

    int tcount = t.length();
    int start = 0;
    int end = 0;
    int minWindow = Integer.MAX_VALUE;
    int head = 0;

    while (end < s.length()) {
        char c = s.charAt(end++);
        if (tchars[c]-- > 0) {
            tcount--;
        }

        while (tcount == 0) {
            if (end - start < minWindow) {
                minWindow = end - start;
                head = start;
            }
            if (tchars[s.charAt(start++)]++ == 0) { //meaning run into one of the characers in t
                tcount++;
            }
        }
    }

    return minWindow==Integer.MAX_VALUE? "":s.substring(head, head+minWindow);
}
```



解法2. MonotonicQueue 维护一个单调递减的队列

Example



239. Sliding Window Maximum

You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.

Return _the max sliding window_.

**Example 1:**

```text
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

答案

```java
public class MonotonicQueue {
        // decreasing
        private Deque<Integer> monotonicQueue = new ArrayDeque<Integer>();
        
        public void push(int n) {
            while (!monotonicQueue.isEmpty() && monotonicQueue.getLast() < n)
                monotonicQueue.removeLast();
            
            monotonicQueue.addLast(n);
        }
        
        public void pop(int n) {
            if (!monotonicQueue.isEmpty() && monotonicQueue.getFirst() == n) {
                monotonicQueue.poll();
            }
        }
        
        public int max() {
            return monotonicQueue.getFirst();
        }
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue mq = new MonotonicQueue();
        int[] maxSliding = new int[nums.length - k + 1];
        for (int i = 0 ; i < nums.length; i++) {
            if (i < k-1) {
                mq.push(nums[i]);
            } else {
                mq.push(nums[i]);
                maxSliding[i-k+1] = mq.max();
                mq.pop(nums[i-k+1]);
            }
        }
        
        return maxSliding;
}
```

套路 3

例题：求最小sub数组拥有相同个数0,1

把0 变成 -1 ， 讲 &lt;sum, idx&gt; 放入hashmap, 通过sum找之前是否见过。注意要先插入\(0,-1\) 相当于在开始之前已经见过0. 然后维护一个到目前为止的sum

