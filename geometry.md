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

{% embed url="https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/" %}



```java
 /* A utility function to calculate area of triangle
       formed by (x1, y1) (x2, y2) and (x3, y3) */
    static double area(int x1, int y1, int x2, int y2,
                                        int x3, int y3)
    {
       return Math.abs((x1*(y2-y3) + x2*(y3-y1)+
                                    x3*(y1-y2))/2.0);
    }
      
    /* A function to check whether point P(x, y) lies
       inside the triangle formed by A(x1, y1),
       B(x2, y2) and C(x3, y3) */
    static boolean isInside(int x1, int y1, int x2,
                int y2, int x3, int y3, int x, int y)
    {  
       /* Calculate area of triangle ABC */
        double A = area (x1, y1, x2, y2, x3, y3);
      
       /* Calculate area of triangle PBC */ 
        double A1 = area (x, y, x2, y2, x3, y3);
      
       /* Calculate area of triangle PAC */ 
        double A2 = area (x1, y1, x, y, x3, y3);
      
       /* Calculate area of triangle PAB */  
        double A3 = area (x1, y1, x2, y2, x, y);
        
       /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }
     
```

