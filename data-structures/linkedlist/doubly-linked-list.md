# Doubly linked list

Deletion

delete the first element

```text
p = first;
first = p.right;
first.left = null
```

delete the third element

```text
p = first.link.link
p.left.right = p.right
p.right.left = p.left
```

Insertion

insert at the beginning

```text
firstNode left = newNode
newNode.right = firstNode
newNode.left = null
firstNode = newNode
```



