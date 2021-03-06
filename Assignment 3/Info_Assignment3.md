# All the information regarding Question A01
## PING </br>
Ping is a computer network administration software utility used to test the reachability of a host on an Internet Protocol (IP) network. It is available for virtually all operating systems that have networking capability, including most embedded network administration software.

Ping measures the round-trip time for messages sent from the originating host to a destination computer that are echoed back to the source. The name comes from active sonar terminology that sends a pulse of sound and listens for the echo to detect objects under water.<br>
Ping operates by sending Internet Control Message Protocol (ICMP) echo request packets to the target host and waiting for an ICMP echo reply. The program reports errors, packet loss, and a statistical summary of the results, typically including the minimum, maximum, the mean round-trip times, and standard deviation of the mean.

The command-line options of the ping utility and its output vary between the numerous implementations. Options may include the size of the payload, count of tests, limits for the number of network hops (TTL) that probes traverse, interval between the requests and time to wait for a response. Many systems provide a companion utility ping6, for testing on Internet Protocol version 6 (IPv6) networks, which implement ICMPv6.<br><br>

## Echo Request and Echo Reply query messages<br>
One of the simplest tests that a user may wish to perform is verifying that a remote system is up and running on the network. Such a test may be required when basic connectivity appears to be failing.<br>

ICMP provides two query messages that work together to provide just this service. The ICMP Echo Request query message is a probe sent by a user to a destination system, which responds with an ICMP Echo Reply query message.<br>

RFC 1122 states that “every host must implement an ICMP Echo server.” Since this service is mandatory, any user should be able to send an ICMP Echo Request to any host on the Internet and receive an ICMP Echo Reply message in return. However, this is not always the case, as firewalls may be blocking the packets (for security reasons), or the packets may simply fail to be delivered.<br><br>
## Rate Limiting<br>
Rate limiting is used to control the amount of incoming and outgoing traffic to or from a network. For example, let’s say you are using a particular service’s API that is configured to allow 100 requests/minute. If the number of requests you make exceeds that limit, then an error will be triggered. The reasoning behind implementing rate limits is to allow for a better flow of data and to increase security by mitigating attacks such as DDoS.<br>

Rate limiting also comes in useful if a particular user on the network makes a mistake in their request, thus asking the server to retrieve tons of information that may overload the network for everyone. With rate limiting in place however, these types of errors or attacks are much more manageable.<br><br>
## Packet Loss<br>
When accessing the internet or any network, small units of data called packets are sent and received. When one or more of these packets fails to reach its intended destination, this is called packet loss. For users, packet loss manifests itself in the form of network disruption, slow service and even total loss of network connectivity. Any application can be disrupted by packet loss, but the most likely victims are applications that rely on real-time packet processing, such as video, audio and gaming programs.<br><br>
## Network Congestion<br>
Network congestion, as its name suggests, occurs when a network becomes congested with traffic and hits maximum capacity. Packets must wait their turn to be delivered, but if the connection falls so far behind that it cannot store any more packets, they will simply be discarded or ignored so that the network can catch up. The good news is that today's applications are able to gracefully handle discarded packets by resending data automatically or slowing down transfer speeds.<br><br>
