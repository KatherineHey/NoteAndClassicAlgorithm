# Geometry

```java
// function to find the slope of a straight line
float slope(float x1, float y1, float x2, float y2)
{
    if(x1 == x2)
        return INT_MAX;
    return (y2 - y1) / (x2 - x1);
}
```

```java
// To find orientation of ordered triplet (p1, p2, p3).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
int orientation(Point p1, Point p2, Point p3)
{
    // See 10th slides from following link for derivation
    // of the formula
    int val = (p2.y - p1.y) * (p3.x - p2.x) -
              (p2.x - p1.x) * (p3.y - p2.y);
  
    if (val == 0) return 0;  // colinear
  
    return (val > 0)? 1: 2; // clock or counterclock wise
}
  
```

[https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/](https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/)

