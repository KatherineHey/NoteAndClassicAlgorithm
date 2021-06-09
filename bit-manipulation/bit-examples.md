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

it's easy to find that power of 4 numbers have those 3 common features.First,greater than 0.Second,only have one '1' bit in their binary notation,so we use x&\(x-1\) to delete the lowest '1',and if then it becomes 0,it prove that there is only one '1' bit.Third,the only '1' bit should be locate at the odd location

```java
public boolean isPowerOfFour(int n) {
    // n is power of 2 && 0x5 == (0101)B, so 0x55555555 means that every odd bit is 1.
    return ((n & (n-1)) == 0) && ((n & 0x55555555) > 0);
}
    
```



```java
public int singleNumber(int[] nums) {
  int ones = 0, twos = 0, threes = 0;
        
  for (int i = 0; i < nums.length; i++) {
    // twos holds the num that appears twice
    twos |= ones & nums[i];
    
    // ones holds the num that appears once
    ones ^= nums[i];
 
    // threes holds the num that appears three times
    threes = ones & twos;
            
    // if num[i] appears three times
    // doing this will clear ones and twos
    ones &= ~threes;
    twos &= ~threes;
  }
        
  return ones;
}
```

