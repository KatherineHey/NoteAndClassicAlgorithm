---
description: Inorder traversal of a BST gives us a sorted order of the items in it
---

# InOrder

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> results = new ArrayList<Integer>();
    
    Stack<TreeNode> s = new Stack<TreeNode>();
    while (root != null || !s.isEmpty()) {
        if (root != null) {
            s.push(root);
            root = root.left;
        } else {
            root = s.pop();
            
            results.add(root.val);
            root = root.right;
        }
    }
    
    return results;
}    
```

```java
public List<Integer> InorderMorisTraversal(TreeNode current) {
    List<Integer> results = new ArrayList<Integer>();
    while (current != null) {
        if (current.left == null) {
            results.add(current.val);
            current = current.right;
        } else {
            TreeNode pre = current.left;
            
            while (pre.right != null && pre.right != current) {
                pre = pre.right;
            }
            
            if (pre.right == current) {
                pre.right = null; // already visited, return to original state
                results.add(current.val);
                current = current.right;
            } else {
                pre.right = current;
                current = current.left;
            }
        }
    }
    
    return results;
}
```

