A01. Implement GBN protocol. Write a sender and receiver program using UDP socket programming with a window size of 4, and using 10 sequence numbers i.e. 0, 1, ..., 9. Sender should send N (Configurable e.g. 12) packets and uses a timeout value of 2 seconds. Receiver program would run using socket number 20000+x, where x corresponds to last 2 three digits of your USN number. The message would have msg header value as its sequence number and content as "message number N" + md5sum checksum value, where N corresponds to sequence number. You can use md5sum checksum library to compute checksum. Consider the case. Sender should exit when it receives ack for the last packet i.e. Nth packet. Implement a lossy channel as sender losing every Ith packet (e.g. I=4). Receiver program would run for ever. Whenever sender and receiver receives a message , these are displayed on terminal.<br/>
Thus invocation of sender and receiver program would be as follows
<br/><br/>
./sender |W| |S| |N| |I| |T| |receiver IP| |receiver port|<br/>
  W - window size<br/>
  S - Max sequence number<br/>
  N - number of messages to send<br/>
  I - every I message from sender side is lost<br/>
  T - timeout value (in seconds) waiting for ack<br/>
<br/>
./receiver |S| |receiver port|
<br/><br/>
### Run the file:
Run the makefile to get the output.
