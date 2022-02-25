# Moris

## &#x20;[Morris Traversal方法遍历二叉树（非递归，不用栈，O(1)空间）](https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html)

本文主要解决一个问题，如何实现二叉树的前中后序遍历，有两个要求：

1\. O(1)空间复杂度，即只能使用常数空间；

2\. 二叉树的形状不能被破坏（中间过程允许改变其形状）。

通常，实现二叉树的前序（preorder）、中序（inorder）、后序（postorder）遍历有两个常用的方法：一是递归(recursive)，二是使用栈实现的迭代版本(stack+iterative)。这两种方法都是O(n)的空间复杂度（递归本身占用stack空间或者用户自定义的stack），所以不满足要求。（用这两种方法实现的中序遍历实现可以参考[这里](https://github.com/AnnieKim/LeetCode/blob/master/BinaryTreeInorderTraversal.h)。）

**Morris Traversal**方法可以做到这两点，与前两种方法的不同在于该方法只需要O(1)空间，而且同样可以在O(n)时间内完成。

要使用O(1)空间进行遍历，最大的难点在于，遍历到子节点的时候怎样重新返回到父节点（假设节点中没有指向父节点的p指针），由于不能用栈作为辅助空间。为了解决这个问题，Morris方法用到了[线索二叉树](http://en.wikipedia.org/wiki/Threaded\_binary\_tree#The\_array\_of\_Inorder\_traversal)（threaded binary tree）的概念。在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了。

Morris只提供了中序遍历的方法，在中序遍历的基础上稍加修改可以实现前序，而后续就要再费点心思了。所以先从中序开始介绍。

首先定义在这篇文章中使用的二叉树节点结构，即由val，left和right组成：

```
1 struct TreeNode {
2     int val;
3     TreeNode *left;
4     TreeNode *right;
5     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
6 };
```

**一、中序遍历**

**步骤：**

1\. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。

2\. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

&#x20;  a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。

&#x20;  b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。

3\. 重复以上1、2直到当前节点为空。

**图示：**

下图为每一步迭代的结果（从左至右，从上到下），cur代表当前节点，深色节点表示该节点已输出。

![](https://images0.cnblogs.com/blog/300640/201306/14214057-7cc645706e7741e3b5ed62b320000354.jpg)

**代码：**[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void\(0\);)

```
 1 void inorderMorrisTraversal(TreeNode *root) {
 2     TreeNode *cur = root, *prev = NULL;
 3     while (cur != NULL)
 4     {
 5         if (cur->left == NULL)          // 1.
 6         {
 7             printf("%d ", cur->val);
 8             cur = cur->right;
 9         }
10         else
11         {
12             // find predecessor
13             prev = cur->left;
14             while (prev->right != NULL && prev->right != cur)
15                 prev = prev->right;
16 
17             if (prev->right == NULL)   // 2.a)
18             {
19                 prev->right = cur;
20                 cur = cur->left;
21             }
22             else                       // 2.b)
23             {
24                 prev->right = NULL;
25                 printf("%d ", cur->val);
26                 cur = cur->right;
27             }
28         }
29     }
30 }
```

[![复制代码](https://common.cnblogs.com/images/copycode.gif)](javascript:void\(0\);)

**复杂度分析：**

空间复杂度：O(1)，因为只用了两个辅助指针。

时间复杂度：O(n)。证明时间复杂度为O(n)，最大的疑惑在于寻找中序遍历下二叉树中所有节点的前驱节点的时间复杂度是多少，即以下两行代码：

```
1 while (prev->right != NULL && prev->right != cur)
2     prev = prev->right;
```

直觉上，认为它的复杂度是O(nlgn)，因为找单个节点的前驱节点与树的高度有关。但事实上，寻找所有节点的前驱节点只需要O(n)时间。n个节点的二叉树中一共有n-1条边，整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，如下图所示，其中红色是为了定位到某个节点，黑色线是为了找到前驱节点。所以复杂度为O(n)。

![](https://images0.cnblogs.com/blog/300640/201306/15150628-5285f29bab234750a62e2309394b6e14.jpg)

**二、前序遍历**

