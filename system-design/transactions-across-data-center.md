# Vol4. Transactions across Data center

## Consistency

* Weak
* Eventual
* Strong
* Use case: read after write

#### Weak consistency

After a write, reads may or may not see it. A best effort approach is taken.

This approach is seen in systems such as memcached. Weak consistency works well in real time use cases such as VoIP, video chat, and realtime multiplayer games.

#### 

#### Eventual consistency

After a write, reads will eventually see it \(typically within milliseconds\). Data is replicated asynchronously.

This approach is seen in systems such as DNS and email. Eventual consistency works well in highly available systems. Amazon S3, SimpleDB

#### 

#### Strong consistency

After a write, reads will see it. Data is replicated synchronously.

This approach is seen in file systems, Azure tables, and RDBMSes \(RelationalDatabase Management System\). Strong consistency works well in systems that need transactions.



**Why across Data centers/ Multihoming**

* Catastrophic failures ****\(e.g. fire\)
* Expected failures \(hardware etc\)
* Routine maintenance \(can still cause down time\)
* Geolocality 
  * CDNs, edge caching \(Data that doesn't change often, you throw it all over the places, you try to get it as many places and as close to as many people as you can.\)

![](../.gitbook/assets/image%20%289%29.png)



little reads:

CDN

A CDN \(Content Delivery Network\) is a highly-distributed platform of servers that helps minimize delays in loading web page content by reducing the physical distance between the server and the user. This helps users around the world view the same high-quality content without slow loading times.

 Without a CDN, content origin servers must respond to every single end user request. This results in significant traffic to the origin and subsequent load, thereby increasing the chances for origin failure if the traffic spikes are exceedingly high or if the load is persistent.

 By responding to end user requests in place of the origin and in closer physical and network proximity to the end user, a CDN offloads traffic from content servers and improves the web experience, thus benefiting both the content provider and its end users.

### How does a CDN work?

 Over half of the internet’s traffic is served by a content delivery network \(CDN\). The goal of the CDN is to reduce latency – the delay between submitting a request for a web page and the web page fully loading on your device – by reducing the physical distance that the request has to travel.

 For example, a US visitor wishing to view content which originates at a UK-based server will experience poor loading times if this request has to travel across the Atlantic.

 To combat this, CDNs store a cached version of your website content in multiple geographical locations around the world, which are known as “points of presence” \(PoPs\). These PoPs will contain their own caching servers and will be responsible for delivering that content in the user’s location.

 User-agents, which are essentially devices running web browsers, make requests for content needed to render web pages such as HTML, images, CSS, and JavaScript files. For most CDNs, each request for content will cause the end user to be mapped to an optimally-located CDN server and the server will respond with the cached \(pre-saved\) version of the requested files. If it fails to locate the files, it will look for the content on the other servers in the CDN platform and send the response to the end user. However, when content is unavailable or stale, the CDN will act as a request proxy to the origin server and store the fetched content to serve future requests.

 Although the delivery of website content is a common use case for CDNs, it is not the only type of content that a CDN can deliver. In fact, CDNs deliver an incredible variety of content that includes: 4K and HD-quality video; audio streams; software downloads such as apps, games, and OS updates; data records that contain medical and financial information; and much more. Potentially any data that can be digitized can be delivered through a CDN.

