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



```java
public boolean isPowerOfFour(int n) {
    // n is power of 2 && 0x5 == (0101)B, so 0x55555555 means that every odd bit is 1.
    return ((n & (n-1)) == 0) && ((n & 0x55555555) > 0);
}
    
```

