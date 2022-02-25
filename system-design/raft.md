---
description: >-
  Raft is a consensus algorithm that is designed to be easy to understand. It's
  equivalent to Paxos in fault-tolerance and performance.
---

# Raft

## Consensus

* Agreement on shared state (single system image)&#x20;
*   Recovers from server failures autonomously

    * Minority of servers fail: no problem&#x20;
    * Majority fail: lose availability, retain consistency

    ![](<../.gitbook/assets/image (11).png>)
* Key to building consistent storage systems

### Inside a Consistent System

* TODO: eliminate single point of failure
* An ad hoc algorithm§“This case is rare and typically occurs as a result of a network partition with replication lag.”\


&#x20;– OR –\


* A consensus algorithm (built-in or library)§Paxos, Raft, …
* A consensus service§ZooKeeper, etcd, consul, …

### Replicated State Machines

![](<../.gitbook/assets/image (12).png>)

* Replicated log  replicated state machine&#x20;
  * All servers execute same commands in same order&#x20;
* Consensus module ensures proper log replication&#x20;
* System makes progress as long as any majority of servers are up&#x20;
* Failure model: fail-stop (not Byzantine), delayed/lost messages

### How Is Consensus Used?

#### Top-level system configuration

Coordinator: Single point failure

![](<../.gitbook/assets/image (10).png>)![](<../.gitbook/assets/image (1).png>)



#### Replicate entire database state

Data is partitioned, assign each partition to a replicated state machine, each partition can autonomously recover from failures without central authority, increase availability

![](<../.gitbook/assets/image (16).png>)



## Raft overview

### Leader election

* Term: distinguish leader
* Each server replay committed logs and no further
* Candidate state - trying to become a leader
  * Different timeout for each candidate for their campigns&#x20;
* Select one of the servers to act as cluster leader&#x20;
* Detect crashes, choose new leader

### Log replication

* Leader takes commands from clients, appends them to its log&#x20;
* Leader replicates its log to other servers (overwriting inconsistencies)

### Safety

* Only a server with an up-to-date log can become leader



## Raft core overview



### Leader election&#x20;

* Heartbeats and timeouts to detect crashes&#x20;
* Randomized timeouts to avoid split votes&#x20;
* Majority voting to guarantee at most one leader per term&#x20;

### Log replication (normal operation)&#x20;

* Leader takes commands from clients, appends them to its log&#x20;
  * leader that creates an entry can mark the entry committed once it's replicated to majority of the clusters
* Leader replicates its log to other servers (overwriting inconsistencies)&#x20;
  * leader never overwrite its log
* Built-in consistency check (terms of the previous entry) simplifies how logs may differ&#x20;

### Safety&#x20;

* Only elect leaders with all committed entries in their logs&#x20;
* New leader defers committing entries from prior terms

![](<../.gitbook/assets/image (13).png>)
