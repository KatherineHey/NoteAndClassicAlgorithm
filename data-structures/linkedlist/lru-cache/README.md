# LRU Cache

#### Choice of data structures <a id="choice-of-data-structures"></a>

* **Queue** - We should maintain a Queue \(double ended queue\), **in which the most recently used pages \(items\) are in the front, and the least recently used pages are in the rear**. This would allow to remove the least recently used item in _O\(1\)_ time.
* **Doubly Linked List** - We should implement our Queue using a doubly linked list \(instead of arrays\), which would allow us to apply _shifting_ operations in _O\(1\)_ time. \(like, when we need to shift a page to the front of the queue\)
* **HashMap** - We should hash the key values to the location where the page is stored. This would allow `get` operation in _O\(1\)_ time.

#### Design and Implementation <a id="design-and-implementation"></a>

Now that we know which what all data structures to use, let’s look at the implementation.

Whenever a user `gets` a page, we return its value, and also move that page to the front of our Queue.

Whenever a user `sets` a page, if the page is already present, we update its value and move that page to the front of our Queue, else we add a new page to our cache in the front of the Queue. But if our cache has reached its capacity, we remove the least recently used page \(ie the rear item in our Queue\) from our memory.

1. `class Node`
   1. key
   2. value
   3. next node address
   4. previous node address
2. `class DoublyLinkedList`

* Data members:
  1. front node address
  2. rear node address
* Member functions:
  1. move\_page\_to\_head\(\)
  2. remove\_rear\_page\(\)
  3. get\_rear\_page\(\)
  4. add\_page\_to\_head\(\)

1. `class LRUCache`

* Data members:
  1. capacity
  2. current size
  3. a DoublyLinkedList object
  4. Hashmap
* Member functions:
  1. get\(key\)
  2. put\(key, value\)

Let’s make the 3 classes.

