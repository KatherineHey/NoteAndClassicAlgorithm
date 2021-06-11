# TreeMap and HashMap

 _TreeMap_ are **sorted according to their natural order**.

// Treemap is ordered 

```java
// Treemap is ordered
for (TreeMap<Integer, PriorityQueue<Integer>> tm : m.values()) {
```

```java
// creating object of TreeMap with key of reverse order
// treemap's order can only be based on key, not value
NavigableMap<Integer, String> treemap = new TreeMap<Integer, String>(
    Collections.reverseOrder());
```

```java
for (Map.Entry<Integer, HashSet<Integer>> entry : scores.entrySet()) {
    HashSet<Integer> players = entry.getValue();
    sumScore += players.size() * entry.getKey();
}
```

LinkedHashMap

insertion order

