# Java API

String

```text
String s = "haha";
s.length();
```

ArrayList to String array

```java
List<String> list = new ArrayList<String>();
String[] a = list.toArray(new String[0]);
```

computeIfAbsent

```java
HashMap<Integer, List<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
myMap.computeIfAbsent(MY_KEY, k -> new ArrayList<>()).add(value);
```

