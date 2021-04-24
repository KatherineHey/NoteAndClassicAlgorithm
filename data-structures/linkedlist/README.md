---
description: Fast pointer and slow pointer to detect cycle
---

# LinkedList

[![loop-explanation](https://web.archive.org/web/20160401024212im_/http://learningarsenal.info/wp-content/uploads/2015/08/loop-explanation-300x199.png)](https://web.archive.org/web/20160401024212/http://learningarsenal.info/wp-content/uploads/2015/08/loop-explanation.png)

**Distance travelled by slowPointer** **before meeting**= x + y

**Distance travelled by fastPointer** **before meeting** = \(x + y + z\) + y

= x + 2y + z

Since fastPointer travels with **double** the speed of slowPointer, and **time is constant** for both when the reach the meeting point.

So by using simple speed, time and distance relation

2\(x+y\)=  x+2y+z

**=&gt;** x+2y+z = 2x+2y

**=&gt;** x=z

So by moving **slowPointer** to start of linked list, and making both **slowPointer** and **fastPointer** to move one node at a time, they both will reach at the point where the loop starts in the linked list.

As you will notice the below code is mostly the same as of previous post where we needed to detect, whether a loop is present or not, and then if a loop is there we move forward to tracing its starting location.



```java
public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) 
            return null;
        
        ListNode slow = head, fast = head, start = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                while (start != fast) {
                    start = start.next;
                    fast = fast.next;
                }
                
                return start;
            }
        }
        
        return null;
    }
```



