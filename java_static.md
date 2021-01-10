# static method

Static methods in java belong to the class (not an instance of it). 

```
One rule-of-thumb: ask yourself "Does it make sense to call this method, 
even if no object has been constructed yet?" 
If so, it should definitely be static.
```

So in a class Car you might have a method:

```java
double convertMpgToKpl(double mpg)
```

which would be static, because one might want to know what 35mpg converts to, even if nobody has ever built a Car. 


But this method (which sets the efficiency of one particular Car):

```java
void setMileage(double mpg)

```

# static nested class
Note: java does not have static class except static nested class

http://java.sun.com/docs/books/tutorial/java/javaOO/nested.html

Highlight
```
A nested class is a member of its enclosing class. 
Non-static nested classes (inner classes) have access to other members of the enclosing class, 
even if they are declared private. 
Static nested classes do not have access to other members of the enclosing class.

Note: A static nested class interacts with the instance members of its outer class (and other classes) 
just like any other top-level class. 
```

**In effect, a static nested class is behaviorally a top-level class that has been nested 
in another top-level class for packaging convenience.**

```
It is a better idea if you are using a nested class is to start off with it being static, 
and then decide if it really needs to be non-static based on your usage.
```

Credit to StackOverflow :)
