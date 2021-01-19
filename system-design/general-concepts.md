---
description: CAP
---

# General Concepts

From wiki.

It is impossible for a [distributed data store](https://en.wikipedia.org/wiki/Distributed_data_store) to simultaneously provide [more than two out of the following three](https://en.wikipedia.org/wiki/Trilemma) guarantees:[\[1\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-Gilbert_Lynch-1)[\[2\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-2)[\[3\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-3)

* [Consistency](https://en.wikipedia.org/wiki/Consistency_model): Every read receives the most recent write or an error
* [Availability](https://en.wikipedia.org/wiki/Availability): Every request receives a \(non-error\) response, without the guarantee that it contains the most recent write
* [Partition tolerance](https://en.wikipedia.org/wiki/Network_partitioning): The system continues to operate despite an arbitrary number of messages being dropped \(or delayed\) by the network between nodes

When a network partition failure happens should we decide to

* Cancel the operation and thus decrease the availability but ensure consistency
* Proceed with the operation and thus provide availability but risk inconsistency

The CAP theorem implies that in the presence of a network partition, one has to choose between consistency and availability. Note that consistency as defined in the CAP theorem is quite different from the consistency guaranteed in [ACID](https://en.wikipedia.org/wiki/ACID) [database transactions](https://en.wikipedia.org/wiki/Database_transaction).[\[4\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-4) \(CAP consistency: All Nodes Have Same Data via Eventual Consistency/ ACID consistency: All Applied Data Changes Provide Consistent View of Data For All DB Connections\)



No distributed system is safe from network failures, thus [network partitioning](https://en.wikipedia.org/wiki/Network_partition) generally has to be tolerated.[\[7\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-7)[\[8\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-8) In the presence of a partition, one is then left with two options: consistency or [availability](https://en.wikipedia.org/wiki/Availability). When choosing consistency over availability, the system will return an error or a time out if particular information cannot be guaranteed to be up to date due to network partitioning. When choosing availability over consistency, the system will always process the query and try to return the most recent available version of the information, even if it cannot guarantee it is up to date due to network partitioning.

In the absence of network failure – that is, when the distributed system is running normally – both availability and consistency can be satisfied.

CAP is frequently misunderstood as if one has to choose to abandon one of the three guarantees at all times. In fact, the choice is really between consistency and availability only when a network partition or failure happens; at all other times, no trade-off has to be made.[\[9\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-9)[\[10\]](https://en.wikipedia.org/wiki/CAP_theorem#cite_note-10)

