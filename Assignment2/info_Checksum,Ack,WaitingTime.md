## Information regarding question A01
**CHECKSUM** </br>
A checksum is an error-detection method in a the transmitter computes a numerical value according to the number of set or unset bits in a message and sends it along with each message frame. At the receiver end, the same checksum function (formula) is applied to the message frame to retrieve the numerical value. If the received checksum value matches the sent value, the transmission is considered to be successful and error-free.</br></br>
**ACKNOWLEDGEMENT**</br>
The ACK signal is sent by the receiving station (destination) back to the sending station (source) after the receipt of a recognizable block of data of specific size. In order to be recognizable, the data block must conform to the protocol in use. When the source receives the ACK signal from the destination, it transmits the next block of data. If the source fails to receive the ACK signal, it either repeats the block of data or else ceases transmission, depending on the protocol.

The ACK signal is usually an ASCII character that is reserved for that purpose. In some protocols, there are various ACK signals that indicate the successful reception and recognition of specific commands, such as power-down or standby.</br></br>
**Waiting Time**</br>
