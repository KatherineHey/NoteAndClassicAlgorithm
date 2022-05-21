# BinarySearch

find m that f(mid) is true. Or return the smallest number l such that condition(left) is true.

&#x20;**Minimize k , s.t. condition(k) is True**

```java
def binary_search(left, right):
  while left < right:
    mid = left + (right - left) / 2
    if f(mid): return mid // optional
    
    if condition(mid):
      right = mid     
    else:
      left = mid + 1 

  return left
```

f(mid) 函数：判断mid是不是当前的解。 condition(mid) 函数：判断解是不是 >= mid。

f(mid) 是可选的，如果找到解，直接返回解。没有f(mid)也可以。 **如果省略f(mid)，那么循环结束后left存储是最小的能满足condition(left) = true 的数。**



* Correctly initialize the boundary variables `left` and `right` to specify search space. Only one rule: set up the boundary to **include all possible elements**;
* Decide return value. Is it `return left` or `return left - 1`? Remember this: **after exiting the while loop, `left` is the minimal k​ satisfying the `condition` function**;
* Design the `condition` function. This is the most difficult and most beautiful part. Needs lots of practice.

&#x20;As for the question "When can we use binary search?", my answer is that, **If we can discover some kind of monotonicity, for example, if `condition(k) is True` then `condition(k + 1) is True`, then we can consider binary search**.



例子：sqrt(x), 返回x开根号后的整数部分。 样例： sqrt(4) = 2 sqrt(8) = 2

代码： sqrt(x)的**取值范围在 \[0, x] 之间，所以l=0, r = x + 1, 搜索范围 \[0, x + 1)**。

```python
def sqrt(x):
  l = 0
  r = x + 1

  while l < r:
    m = l + (r - l) / 2
    if m * m > x
      r = m
    else
      l = m + 1

  return l - 1
```

套用模版：f(m) 没有，g(m) = m _m > x。 循环结束后l为最小的数满足 l_ l > x，第一个平方大于x的整数。所以sqrt(x) = l - 1，最后返回 l - 1。

```java
// first find the peek of the mountain
        int len = mountainArr.length();
        int l = 0 ; int r = len - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid+1)) {
                l = mid+1;
            } else {
                r = mid;
            }
        }

        int peak = l;
```



Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

```
public int search(int[] nums, int target) { int start = 0; int end = nums.length-1;
    while (start < end) {
        int mid = start + (end - start) / 2;
        
        if (nums[mid] < target) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    
    return nums[start] == target? start: -1;
}
```

Another example to find the **largest** l that condition is true

1802 Maximum Value at a Given Index in a Bounded Array

```java
public int maxValue(int n, int index, int maxSum) {
    maxSum -= n;
    int left = 0, right = maxSum, mid;
    while (left < right) {
        mid = (left + right + 1) / 2;
        if (test(n, index, mid) <= maxSum)
            left = mid;
        else
            right = mid - 1;
    }
    
    return left + 1;
}

private long test(int n, int index, int a) {
    int b = Math.max(a - index, 0);
    long res = (long)(a + b) * (a - b + 1) / 2;
    b = Math.max(a - ((n - 1) - index), 0);
    res += (long)(a + b) * (a - b + 1) / 2;
    return res - a;
}
```

非常赞的总结！！

{% embed url="https://leetcode.com/discuss/general-discussion/786126/python-powerful-ultimate-binary-search-template-solved-many-problems" %}





[https://www.topcoder.com/thrive/articles/Binary%20Search](https://www.topcoder.com/thrive/articles/Binary%20Search)

&#x20;You can verify that this satisfies our condition that the element we’re looking for always be present in the interval (lo, hi). However, there is another problem. Consider what happens when you run this code on some search space for which the predicate gives:\
\


| no | yes |
| -- | --- |

\
The code will get stuck in a loop. It will always select the first element as mid, but then will not move the lower bound because it wants to keep the no in its search space. The solution is to change mid = lo + (hi-lo)/2 to mid = lo + (hi-lo+1)/2, i.e. so that it rounds up instead of down. There are other ways of getting around the problem, but this one is possibly the cleanest. Just remember to always test your code on a two-element set where the predicate is false for the first element and true for the second.



Each time we only do one of the two:

```
(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]
```

Doing so will maintain the tails invariant. The the final answer is just the size.

**Java (binary search to find the ceiling: i/ left is the ceiling)**

```
public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int x : nums) {
        int i = 0, j = size;
        while (i < j) {
            int m = (i + j) / 2;
            if (tails[m] < x)
                i = m + 1;
            else
                j = m;
        }
        tails[i] = x;
        if (i == size) ++size;
    }
    return size;
}
// Runtime: 2 ms
```
