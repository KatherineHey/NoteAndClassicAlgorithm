# Bit examples

```java
public int countOnes (int n) {
    int cnt = 0;
    while (n > 0) {
        n = n & (n-1); //Remove last bit that's 1: A&(A-1)
        cnt++;
    }
    
    return cnt;
}
```

