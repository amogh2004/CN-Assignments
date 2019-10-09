# All the information regarding Question A01
## GBN PROTOCOL</br>
**GBN protocol**, also known as **Go-Back-N Automatic Repeat reQuest**, is a data link layer protocol that uses a **sliding window method** for reliable and sequential delivery of data frames. It is a *case of sliding window protocol* having to send window size of N and receiving window size of 1.</br></br>
#### Working Principle
Go – Back – N ARQ provides for sending multiple frames before receiving the acknowledgment for the first frame. The frames are sequentially numbered and a finite number of frames. The maximum number of frames that can be sent depends upon the size of the sending window. If the acknowledgment of a frame is not received within an agreed upon time period, all frames starting from that frame are retransmitted.</br>
The size of the sending window determines the sequence number of the outbound frames. If the sequence number of the frames is an n-bit field, then the range of sequence numbers that can be assigned is 0 to 2n−1. Consequently, the size of the sending window is 2n−1. Thus in order to accommodate a sending window size of 2n−1, a n-bit sequence number is chosen.</br>
The sequence numbers are numbered as modulo-n. For example, if the sending window size is 4, then the sequence numbers will be 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, and so on. The number of bits in the sequence number is 2 to generate the binary sequence 00, 01, 10, 11.</br>
The size of the receiving window is 1.</br></br>
#### Socket Creation 
int sockfd = socket(domain, type, protocol)</br>
sockfd: socket descriptor, an integer (like a file-handle)</br>
domain: integer, communication domain e.g., AF_INET (IPv4 protocol) , AF_INET6 (IPv6 protocol)</br>
type: communication type</br>
SOCK_STREAM: TCP(reliable, connection oriented)</br>
SOCK_DGRAM: UDP(unreliable, connectionless)</br>
protocol: Protocol value for Internet Protocol(IP), which is 0. This is the same number which appears on protocol field in the IP header of a packet.(man protocols for more details)</br></br>

#### UDP Server
Create UDP socket.</br>
Bind the socket to server address.</br>
Wait until datagram packet arrives from client.</br>
Process the datagram packet and send a reply to client.</br>
Go back to Step 3.</br></br>

#### UDP Client
Create UDP socket.</br>
Send message to server.</br>
Wait until response from server is recieved.</br>
Process reply and go back to step 2, if necessary.</br>
Close socket descriptor and exit.</br></br>

#### Socket
A datagram socket is a type of network socket which provides a connectionless point for sending or receiving data packets. Each packet sent or received on a datagram socket is individually addressed and routed.</br></br>

#### UDP server
 The Caché User Datagram Protocol (UDP) binding. Provides two-way message transfer between a server and a large number of clients. UDP is not connection-based; each data packet transmission is an independent event. Provides fast and lightweight data transmission for local packet broadcasts and remote multicasting.</br></br>

#### Checksum
A checksum is an error-detection method in a the transmitter computes a numerical value according to the number of set or unset bits in a message and sends it along with each message frame. At the receiver end, the same checksum function (formula) is applied to the message frame to retrieve the numerical value. If the received checksum value matches the sent value, the transmission is considered to be successful and error-free.</br></br>

#### Acknowledgement
The ACK signal is sent by the receiving station (destination) back to the sending station (source) after the receipt of a recognizable block of data of specific size. In order to be recognizable, the data block must conform to the protocol in use. When the source receives the ACK signal from the destination, it transmits the next block of data. If the source fails to receive the ACK signal, it either repeats the block of data or else ceases transmission, depending on the protocol.</br>

The ACK signal is usually an ASCII character that is reserved for that purpose. In some protocols, there are various ACK signals that indicate the successful reception and recognition of specific commands, such as power-down or standby.</br></br>
#### Waiting time
Time Difference between turn around time and burst time.</br>
Waiting Time = Turn Around Time – Burst Time</br></br>

### SENDER SIDE </br>

#### Transmission Delay (Tt)</br></br>
 – Time to transmit the packet from host to the outgoing link. If B is the Bandwidth of the link and D is the Data Size to transmit</br>
Tt = D/B</br></br>

#### Propagation Delay (Tp)
It is the time taken by the first bit transferred by the host onto the outgoing link to reach the destination. It depends on the distance d and the wave propagation speed s (depends on the characteristics of the medium).</br>
Tp = d/s </br></br>

#### Efficiency
It is defined as the ratio of total useful time to the total cycle time of a packet. For stop and wait protocol</br>
Total cycle time</br> 
= Tt(data) + Tp(data) + Tt(acknowledgement) + Tp(acknowledgement)</br>
= Tt(data) + Tp(data) + Tp(acknowledgement)</br>
= Tt + 2*Tp</br></br>

#### Effective Bandwidth(EB) or Throughput
Number of bits sent per second.</br>
EB = Data Size(L) / Total Cycle time(Tt + 2*Tp)</br>
Multiplying and dividing by Bandwidth (B),</br>
=  (1/(1+2a)) * B   [ Using a = Tp/Tt ]</br>
=  Efficiency * Bandwidth</br></br>

#### Capacity of link
If a channel is Full Duplex, then bits can be transferred in both the directions and without any collisions. Number of bits a channel/Link can hold at maximum is its capacity.</br>
Capacity = Bandwidth(B) * Propagation(Tp)</br>
For Full Duplex channels, </br>
Capacity = 2*Bandwidth(B) * Propagation(Tp)</br></br>

### RECEIVER SIDE </br>

#### Sender Window Size (WS)
It is N itself. If we say the protocol is GB10, then WS = 10. N should be always greater than 1 in order to implement pipelining.</br>
Efficiency Of GBN = N/(1+2a) Where a = Tp/Tt</br>
If B is the bandwidth of the channel, then Effective Bandwidth or Throughput = Efficiency * Bandwidth = (N/(1+2a)) * B.</br></br>

#### Receiver Window Size (WR)
WR is always 1 in GBN. Now what exactly happens in GBN, we will explain with a help of example. We have sender window size of 4. Assume that we have lots of sequence numbers just for the sake of explanation. Now the sender has sent the packets 0, 1, 2 and 3. After acknowledging the packets 0 and 1, receiver is now expecting packet 2 and sender window slides to further transmit the packets 4 and 5. Now suppose the packet 2 is lost in the network, Receiver will discard all the packets which sender has transmitted after packet 2 as it is expecting sequence number of 2. On the sender side for every packet sent there is a time out timer which will expire for packet number 2. Now from the last transmitted packet 5 sender will go back to the packet number 2 in the current window and transmit all the packets till packet number 5. That’s why it is called Go Back N. Go back means sender has to go back N places from the last transmitted packet in the unacknowledged window and not from the point where the packet is lost.</br></br>

#### Acknowledgement
GBN uses Cumulative Acknowledgement. At the receiver side, it starts a acknowledgement timer whenever receiver receives any packet which is fixed and when it expires, it is going to send a cumulative Ack for the number of packets received in that interval of timer. If receiver has received N packets, then the Acknowledgement number will be N+1. Important point is Acknowledgement timer will not start after the expiry of first timer but after receiver has received a packet.
Time out timer at the sender side should be greater than Acknowledgement timer.</br></br>

#### Relationship Between Window Sizes and Sequence Numbers
We already know that sequence numbers required should always be equal to the size of window in any sliding window protocol.</br>
Minimum sequence numbers required in GBN is N+1. Bits Required will be ceil(log2(N+1)).</br>
The extra 1 is required in order to avoid the problem of duplicate packets as described below:</br>
Consider an example of GB4. Sender window size is 4 therefore we require a minimum of 4 sequence numbers to label each packet in the window. Now suppose receiver has received all the packets(0, 1, 2 and 3 sent by sender) and hence is now waiting for packet number 0 again(We can not use 4 here as we have only 4 sequence numbers available since N = 4). Now suppose the cumulative ack for the above 4 packets is lost in the network. On sender side, there will be timeout for packet 0 and hence all the 4 packets will be transmitted again. Problem now is receiver is waiting for new set of packets which should have started from 0 but now it will receive the duplicate copies of the previously accepted packets. In order to avoid this, we need one extra sequence number. Now the receive could easily reject all the duplicate packets which were starting from 0 because now it will be waiting for packet number 4( We have added an extra sequence number now).</br></br>


