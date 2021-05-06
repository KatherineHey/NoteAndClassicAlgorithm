# Binary Indexed Tree

{% embed url="https://www.topcoder.com/thrive/articles/Binary%20Indexed%20Trees" %}

#### Reading cumulative frequency

To compute the cumulative frequency at index **idx**, we perform the following sequence of steps: add **tree\[idx\]** to **sum** \(initially, we set **sum** to be zero\); subtract the last bit of **idx** from itself \(i.e., set the least significat non-zero bit of **idx** to zero\); and repeat this process while **idx** is greater than zero. The following function \(written in C++\) implements this approach:

```text
int read(int idx) {
  int sum = 0;
  while (idx > 0) {
    sum += tree[idx];
    idx -= (idx & -idx);
  }
  return sum;
}
```

We provide an example for **idx** = 13; **sum** = 0:

iteration\| idx                          \| position of the last bit \|  idx & -idx   \|      sum

1             13 = 1101                   0                                       1 \(2 ^0\)                 3

2              12 = 1100                  2                                        4 \(2 ^2\)                14

3               8 = 1000                   3                                        8 \(2 ^3\)                26

4                 0 = 0                                                           ———

  
![](https://images.ctfassets.net/piwi0eufbb2g/6MTMWSoGHQcg6R7a5nsT8G/278635e8f83f93b2769cfacb3f4de7ce/read.gif)  
Image 1.5 – the arrows show the path from an index to zero which is used to compute sum \(the image shows example for index 13\)  


So, our result is 26. The number of iterations in this function is the number of non-zero bits in **idx**, which is at most **log MaxIdx**.

#### Time complexity: O\(log MaxIdx\).  Code length: Up to ten lines.

#### Change frequency at some position and update tree

We now illustrate how to perform a BIT update. That is, we show how to update BIT at all the indices which are responsible for the frequency that we are changing. Assume that we want to increase the frequency at index **idx** by **val**. As a reminder, to read the cumulative frequency at some index we repeatedly remove the last bit of the corresponding index and accumulate the corresponding tree frequency. To update the BIT corresponding to the increase of the frequency at **idx** by **val**, we apply the following steps: increment the tree frequency at the current index by **val** \(the starting index is the one whose frequency has changed\); add the last bit of **idx** to itself; and, repeat while **idx** is less than or equal to **MaxIdx**. The corresponding function in C++ follows:

```text
void update(int idx, int val) {
  while (idx <= MaxIdx) {
    tree[idx] += val;
    idx += (idx & -idx);
  }
}
```

Note that the functions **read** and **update** in some sense perform inverse operations of each other – in **read** we subtract while in **update** we add the last bit of the current index.

The following example illustrates **update** for **idx** = 5:

iteration idx position of the last bit idx & -idx 15 = 10101 \(2 ^0\)26 = 11012 \(2 ^1\)38 = 100038 \(2 ^3\)416 = 10000416 \(2 ^4\)532 = 100000——

![](https://images.ctfassets.net/piwi0eufbb2g/25Kj4cq7Ab9M6eBRyM8cfU/80c135356c9820412c84b9e65582ac83/bitupdate.gif)

Image 1.6 – Updating a tree \(in the brackets are tree frequencies before the update\); the arrows show the path while the tree is being updated from index to **MaxIdx** \(the image shows an example for index 5\)  


Using the algorithm above or following the arrows shown in Image 1.6 we can update **BIT**.

#### _Time complexity:_ O\(log MaxIdx\).  _Code length:_ Up to ten lines.

### Read the actual frequency at a position

We have described how to read the cumulative frequency at a given index. Assume that we want to get the actual frequency at index **idx**. It is obvious that we can not simply return **tree\[idx\]** to achieve that. One approach to get the frequency at a given index is to maintain an additional array. In this array, we separately store the frequency for each index. Reading or updating a frequency takes O\(1\) time; the memory space is linear. However, it is also possible to obtain the actual frequency at a given index without using additional structures.

First, the frequency at index **idx** can be calculated by calling the function **read** twice – **f\[idx\] = read\(idx\) – read\(idx – 1\)** — by taking the difference of two adjacent cumulative frequencies. This procedure works in 2 \* O\(log n\) time. There is a different approach that has lower running time complexity than invoking **read** twice, lower by a constant factor. We now describe this approach.

The main idea behind this approach is motivated by the following observation. Assume that we want to compute the sum of frequencies between two indices. For each of the two indices, consider the path from the index to the root. These two paths meet at some index \(at latest at index 0\), after which point they overlap. Then, we can calculate the sum of the frequencies along each of those two paths until they meet and subtract those two sums. In that way we obtain the sum of the frequencies between that two indices.

We translate this observation to an algorithm as follows. Let **x** be an index and **y**=**x**-1. We can represent \(in binary notation\) **y** as **a0b**, where **b** consists of all ones. Then, **x** is **a1b¯** \(note that **b¯** consists of all zeros\). Now, consider the first iteration of the algorithm **read** applied to **x**. In the first iteration, the algorithm removes the last bit of **x**, hence replacing **x** by **z**=**a0b¯**.

Now, let us consider how the active index **idx** of the function **read** changes from iteration to iteration on the input **y**. The function **read** removes, one by one, the last bits of **idx**. After several steps, the active index **idx** becomes **a0b¯** \(as a reminder, originally **idx** was equal to **y**=**a0b**\), that is the same as **z**. At that point we stop as the two paths, one originating from **x** and the other one originating from **y**, have met. Now, we can write our algorithm that resembles this discussion. \(Note that we have to take special care in case **x** equals 0.\) A function in C++:

```text
int readSingle(int idx) {
  int sum = tree[idx]; // this sum will be decreased
  if (idx > 0) { // the special case
    int z = idx - (idx & -idx);
    idx--; // idx is not important anymore, so instead y, you can use idx
    while (idx != z) { // at some iteration idx (y) will become z
      sum -= tree[idx];
      // substruct tree frequency which is between y and "the same path"
      idx -= (idx & -idx);
    }
  }
  return sum;
}
```

Here is an example for getting the actual frequency for index 12:

First, we calculate **z = 12 – \(12 & -12\) = 8**, **sum = 11**

iteration y position of the last bit y & -y sum 111 = 101101 \(2 ^0\)9210 = 101012 \(2 ^1\)238 = 1000———

  
![](https://images.ctfassets.net/piwi0eufbb2g/7N1GelGEUWMWqK5NAejfsg/e08316d27d55af28ff7f8f6fd58eaa49/reada.gif)  
_Image 1.7 – read the actual frequency at some index by using BIT \(the image shows an example for index 12\)_  


It is clear from the algorithm that it runs faster than invoking **read** twice – the while loop corresponds to a single invocation of **read**. Furthermore, for any odd number this algorithm runs in constant time.  


####  Time complexity: O\(log MaxIdx\). Code length: Up to fifteen lines.

