# logger



Design a logger system that receives a stream of messages along with their timestamps. Each **unique** message should only be printed **at most every 10 seconds** \(i.e. a message printed at timestamp `t` will prevent other identical messages from being printed until timestamp `t + 10`\).

All messages will come in chronological order. Several messages may arrive at the same timestamp.

Implement the `Logger` class:

* `Logger()` Initializes the `logger` object.
* `bool shouldPrintMessage(int timestamp, string message)` Returns `true` if the `message` should be printed in the given `timestamp`, otherwise returns `false`.



timeNew record the start time of the mapNew. mapNew keeps the messages of \[timeNew, timeNew +10\); mapOld keeps messages at most 20 seconds before.

假设有个message是 \(a,9\), 下一个message是（a, 11）那么newCache只包含latest=11这个info 里面没有任何message 因为a不应该被print 已经return



```java
class Logger {
    private Map<String, Integer> cacheOld;
    private Map<String, Integer> cacheNew;
    private int latest;

    /** Initialize your data structure here. */
    public Logger() {
        this.cacheOld = new HashMap<String, Integer>();
        this.cacheNew = new HashMap<String, Integer>();
        
        this.latest = 0;
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(timestamp >= latest + 20){
            cacheOld.clear();
            cacheNew.clear();
            latest = timestamp;
        }else if(timestamp >= latest + 10){
            cacheOld = new HashMap<>(cacheNew);
            cacheNew.clear();
            latest = timestamp;
        }
        
        if(cacheNew.containsKey(message)) return false;
        
        if(cacheOld.containsKey(message)){
            int last = cacheOld.get(message);
            if(last + 10 > timestamp) return false;
        }
        
        cacheNew.put(message, timestamp);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
```

