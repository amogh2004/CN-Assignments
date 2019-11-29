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
