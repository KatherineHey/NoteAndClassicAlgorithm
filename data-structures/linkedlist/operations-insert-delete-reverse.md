# Operations insert, delete, reverse

Insertion

```text
// Insert at the first
newNode.next = first
first = newNode

// Insert at the third
before = first.link.link
newNode.link = before.link
before.link = newNode
```

Deletion of Node

```text
// Delete the first element
first = first.link

// Delete the third element
before  = first.link
before.link = before.link.link
```

Reverse Linked List \(iterative and recursive\)

```java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    
    return newHead;
}
```

```java
public ListNode reverseList(ListNode head) {
    if (head == null) return head;
        
    ListNode prev = null;
    while (head.next != null) {
        ListNode nextNode = head.next;
        head.next = prev;
        prev = head;
        head = nextNode;
    }
        
    head.next = prev;
        
    return head;
}
```

