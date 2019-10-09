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

</br></br>UDP server</br></br>:
 the Caché User Datagram Protocol (UDP) binding. Provides two-way message transfer between a server and a large number of clients. UDP is not connection-based; each data packet transmission is an independent event. Provides fast and lightweight data transmission for local packet broadcasts and remote multicasting.</br>

**Checksum** </br>
A checksum is an error-detection method in a the transmitter computes a numerical value according to the number of set or unset bits in a message and sends it along with each message frame. At the receiver end, the same checksum function (formula) is applied to the message frame to retrieve the numerical value. If the received checksum value matches the sent value, the transmission is considered to be successful and error-free.</br></br>
**Acknowledgement**</br>
The ACK signal is sent by the receiving station (destination) back to the sending station (source) after the receipt of a recognizable block of data of specific size. In order to be recognizable, the data block must conform to the protocol in use. When the source receives the ACK signal from the destination, it transmits the next block of data. If the source fails to receive the ACK signal, it either repeats the block of data or else ceases transmission, depending on the protocol.

The ACK signal is usually an ASCII character that is reserved for that purpose. In some protocols, there are various ACK signals that indicate the successful reception and recognition of specific commands, such as power-down or standby.</br></br>
**Waiting time**</br>
