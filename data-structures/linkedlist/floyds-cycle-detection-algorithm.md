---
description: (The Tortoise and the Hare)
---

# Floyd's Cycle Detection Algorithm

[https://web.archive.org/web/20160313063357/http://www.siafoo.net/algorithm/10](https://web.archive.org/web/20160313063357/http://www.siafoo.net/algorithm/10)

```text
 tortoise = top
 hare = top
 
 forever:
     if hare == end :
         return 'No Loop Found'
     hare = hare.next
 
     if hare == end :
         return 'No Loop Found'
     hare = hare.next
 
     tortoise = tortoise.next
 
    if hare == tortoise:
         return 'Loop Found'
```

