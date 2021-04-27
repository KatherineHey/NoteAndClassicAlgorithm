---
description: >-
  Visit each edge exactly once. Find the end node first and delete the path to
  this node(backtrack)
---

# Eulerian path

{% embed url="https://leetcode.com/problems/reconstruct-itinerary/discuss/78768/Short-Ruby-Python-Java-C%2B%2B" %}



![](../.gitbook/assets/image%20%282%29.png)

Example:

![enter image description here](https://www.stefan-pochmann.info/misc/reconstruct-itinerary.png)

From JFK we first visit JFK -&gt; A -&gt; C -&gt; D -&gt; A. There we're stuck, so we write down A as the end of the route and retreat back to D. There we see the unused ticket to B and follow it: D -&gt; B -&gt; C -&gt; JFK -&gt; D. Then we're stuck again, retreat and write down the airports while doing so: Write down D before the already written A, then JFK before the D, etc. When we're back from our cycle at D, the written route is D -&gt; B -&gt; C -&gt; JFK -&gt; D -&gt; A. Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -&gt; A -&gt; C -&gt; D -&gt; B -&gt; C -&gt; JFK -&gt; D -&gt; A.

PriorityQueue to guarantee lexical order

```text
public List<String> findItinerary(String[][] tickets) {
    for (String[] ticket : tickets)
        targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
    visit("JFK");
    return route;
}

Map<String, PriorityQueue<String>> targets = new HashMap<>();
List<String> route = new LinkedList();

void visit(String airport) {
    while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
        visit(targets.get(airport).poll());
    route.add(0, airport);
}
```

Iterative version:

```text
public List<String> findItinerary(String[][] tickets) {
    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    for (String[] ticket : tickets)
        targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
    List<String> route = new LinkedList();
    Stack<String> stack = new Stack<>();
    stack.push("JFK");
    while (!stack.empty()) {
        while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
            stack.push(targets.get(stack.peek()).poll());
        route.add(0, stack.pop());
    }
    return route;
}
```

