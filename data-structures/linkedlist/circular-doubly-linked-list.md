# Circular Josephus

Josephus 

共n个游客，报到m倍数的游客被淘汰，一直继续到最后一个人

```java
for (int i = 1; i <= n-1; i++) {
    for (int j = 1; j<= m-1; j++) {
        rear = rear.link; // rare每次指向要出队列的前一个结点
        
    if (i == 1) {
        head = rear.link; //head指向出队列结点链表的开头
        p = rear.link; //p指向队列链表的结尾
    } else {
        p.link = rear.link;
        p = rear.link;
    }
    
    rear.link = p.link;
}

p.link = rear;
rear.link = null;
    
    
```

