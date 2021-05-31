---
description: 'https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html'
---

# Boyer-Moore Majority Vote algorithm

## Majority Voting Algorithm

### Find the majority element in a list of values



I haven't done an algorithms post in awhile, so the usual disclaimer first: If you don't find programming algorithms interesting, stop reading. This post is not for you.

#### Problem Statement

Imagine that you have a non-sorted list of values. You want to know if there is a value that is present in the list for more than half of the elements in that list. If so what is that value? If not, you need to know that there is no majority element. You want to accomplish this as efficiently as possible.

One common reason for this problem could be fault-tolerant computing. You perform multiple redundant computations and then verify that a majority of the results agree.

#### Simple Solution

Sort the list, if there is a majority value it must now be the middle value. To confirm it's the majority, run another pass through the list and count it's frequency.

The simple solution is `O(n lg n)` due to the sort though. We can do better!

#### Boyer-Moore Algorithm

The Boyer-Moore algorithm is presented in this paper: [Boyer-Moore Majority Vote Algorithm](http://www.cs.rug.nl/~wim/pub/whh348.pdf). The algorithm uses `O(1)` extra space and `O(N)` time. It requires exactly 2 passes over the input list. It's also quite simple to implement, though a little trickier to understand how it works.

In the first pass, we generate a single candidate value which is the majority value if there is a majority. The second pass simply counts the frequency of that value to confirm. The first pass is the interesting part.

In the first pass, we need 2 values:

1. A `candidate` value, initially set to any value.
2. A `count`, initially set to `0`.

For each element in our input list, we first examine the `count` value. If the count is equal to 0, we set the `candidate` to the value at the current element. Next, first compare the element's value to the current `candidate` value. If they are the same, we increment `count` by 1. If they are different, we decrement `count` by 1.

In [python](http://www.python.org/):

> ```text
> candidate = 0
> count = 0
> for value in input:
>   if count == 0:
>     candidate = value
>   if candidate == value:
>     count += 1
>   else:
>     count -= 1
> ```

At the end of all of the inputs, the `candidate` will be the majority value if a majority value exists. A second O\(N\) pass can verify that the `candidate` is the majority element \(an exercise left for the reader\).

#### Explanation

To see how this works, we only need to consider cases that contain a majority value. If the list does not contain a majority value, the second pass will trivially reject the candidate.

First, consider a list where the first element is not the majority value, for example this list with majority value `0`: `[5, 5,` **`0`**`,` **`0`**`,` **`0`**`, 5,` **`0`**`,` **`0`**`, 5]`

When processing the first element, we assign the value of 5 to `candidate` and 1 to `count`. Since 5 is not the majority value, at some point in the list our algorithm must find another value to pair with every 5 we've seen so far, thus `count` will drop to `0` at some point before the last element in the list. In the above example, this occurs at the 4th' element:

List Value: `[5, 5, 0, 0, ...`

Count value: `[1, 2, 1, 0, ...`

At the point that `count` returns to `0`, we have consumed exactly the same number of 5's as other elements. If all of the other elements were the majority element as in this case, we've consumed 2 majority elements and 2 non-majority elements. This is the largest number of majority elements we could have consumed, but even still the majority element must still be a majority of the remainder of the input list \(in our example, the remainder is ... **`0`**`, 5,` **`0`**`,` **`0`**`, 5]`\). If some of the other elements were not majority elements \(for example, if the value was 4 instead\), this would be even more true.

We can see similarly that if the first element was a majority element and `count` at some point drops to `0`, then we can also see that the majority element is still the majority of the remainder of the input list since again we have consumed an equal number of majority and non-majority elements.

This in turn demonstrates that the range of elements from the time `candidate` is first assigned to when `count` drops to `0` can be discarded from the input without affecting the final result of the first pass of the algorithm. We can repeat this over and over again discarding ranges that prefix our input until we find a range that is a suffix of our input where `count` never drops to `0`.

Given an input list suffix where count never drops to `0`, we must have more values that equal the first element than values that do not. Hence, the first element \(`candidate`\) must be the majority of that list and is the only possible candidate for the majority of the full input list, though it is still possible there is no majority at all.

