---
description: if ((state & (1<<i)) == 1) continue; // number i already used
---

# Can i win

464. Can I WinMedium

In the "100 game" two players take turns adding, to a running total, any integer from `1` to `10`. The player who first causes the running total to **reach or exceed** 100 wins.

What if we change the game so that players **cannot** re-use integers?

For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total &gt;= 100.

Given two integers `maxChoosableInteger` and `desiredTotal`, return `true` if the first player to move can force a win, otherwise, return `false`. Assume both players play **optimally**.

```java
public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    HashMap<Integer, Boolean> statesResult = new HashMap<Integer, Boolean>();
    return canIWin(maxChoosableInteger, desiredTotal, 0, statesResult);
}

public boolean canIWin(int maxChoosableInteger, int desiredTotal, int state, HashMap<Integer, Boolean> statesResult) {
    if (statesResult.containsKey(state))
        return statesResult.get(state);
    
    for (int i=1; i <= maxChoosableInteger; i++) {
        if ((state & (1<<i)) != 0) continue; // number i already used
        
        // whoever reaches next level cannot win, therefore current level can win
        if (desiredTotal <= i || !canIWin(maxChoosableInteger, desiredTotal - i, state | (1<<i), statesResult)) {
            statesResult.put(state, true);
            return true;
        }
    }
    
    statesResult.put(state, false);
    return false;
}
```

