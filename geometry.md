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

