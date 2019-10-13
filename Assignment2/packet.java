import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

import java.nio.ByteBuffer;

public class packet 
{
   public static final int MAX_DATA_LENGTH = 500;
   public static final int SEQ_NUM_MODULO = 32;

   private static final int ACK_PACKET_TYPE = 0;
   private static final int DATA_PACKET_TYPE = 1;
   private static final int EOT_PACKET_TYPE = 2;
	
   private int type;
   private int seqNum;
   private String data;

   // Hidden constructor to prevent creation of invalid packets
   private packet(int type, int seqNum, String data) throws Exception 
   {
      // Throws an exception if data seqment larger than allowed
      if (data.length() > MAX_DATA_LENGTH) 
      {
         System.out.println("ERROR: Expecting data field which is at most" +
            " 500 characters, but got " + data.length() + " characters");
         System.exit(-1);
      } // if
		
      this.type = type;
      this.seqNum = seqNum % SEQ_NUM_MODULO;
      this.data = data;
   }

   // Special packet constructors to be used in place of hidden constructor
   public static packet createACK(int seqNum) throws Exception 
   {
      return new packet(ACK_PACKET_TYPE, seqNum, new String());
   }

   public static packet createData(int seqNum, String data) throws Exception 
   {
      return new packet(DATA_PACKET_TYPE, seqNum, data);
   } 

   public static packet createEOT(int seqNum) throws Exception 
   {
      return new packet(EOT_PACKET_TYPE, seqNum, new String());
   } 

   // Returns the type
   public int getType() 
   {
      return type;
   } 

   // Returns the sequence number
   public int getSeqNum() 
   {
      return seqNum;
   } 

   // Returns the length of the data
   public int getLength() 
   {
      return data.length();
   } 

   // Returns the data as a byte array representation
   public byte[] getData() 
   {
      return data.getBytes();
   } 

   // Checks if a packet is an ACK packet
   public boolean isACK() 
   {
      if (type == ACK_PACKET_TYPE) 
      {
         return true;
      } 
      else 
      {
         return false;
      } 
   } 

   // Checks if a packet is a data packet
   public boolean isData() 
   {
      if (type == DATA_PACKET_TYPE) 
      {
         return true;
      } 
      else 
      {
         return false;
      } 
   } 

   // Checks if a packet is an EOT packet
   public boolean isEOT() 
   {
      if (type == EOT_PACKET_TYPE) 
      {
         return true;
      } 
      else 
      {
         return false;
      } 
   } 
	
   // Creates a packet to send data to the emulator and writes it out to the
   // data transfer socket
   public void sendTo(String emulatorAddress, int emulatorPort,
      DatagramSocket dataTransferSocket) throws Exception 
   {
      byte[] dataToEmulator = getDataToEmulator();
      InetAddress emulatorIPAddress = InetAddress.getByName(emulatorAddress);
      DatagramPacket packetToEmulator =
         new DatagramPacket(dataToEmulator, dataToEmulator.length,emulatorIPAddress, emulatorPort);
      dataTransferSocket.send(packetToEmulator);
   } 
	
   // Gets data to be sent to the emulator
   private byte[] getDataToEmulator() throws Exception 
   {
      ByteBuffer buffer = ByteBuffer.allocate(512);
      buffer.putInt(type);
      buffer.putInt(seqNum);
      buffer.putInt(data.length());
      buffer.put(data.getBytes(), 0, data.length());
      return buffer.array();
   } // getDataToEmulator

   // Creates a packet to receive data from the emulator and reads into it from the data transfer socket
   public static packet receiveFrom(DatagramSocket dataTransferSocket)throws Exception 
   {
      byte[] dataFromEmulator = new byte[512];
      DatagramPacket packetFromEmulator = new DatagramPacket(dataFromEmulator, dataFromEmulator.length);
      dataTransferSocket.receive(packetFromEmulator);
      packet parsedPacket = parseDataFromEmulator(dataFromEmulator);
      return parsedPacket;
   } 

   // Parses data received from the emulator
   private static packet parseDataFromEmulator(byte[] dataFromEmulator)throws Exception 
   {
      ByteBuffer buffer = ByteBuffer.wrap(dataFromEmulator);
      int type = buffer.getInt();
      int seqNum = buffer.getInt();
      int length = buffer.getInt();
      byte[] data = new byte[length];
      buffer.get(data, 0, length);
      return new packet(type, seqNum, new String(data));
   } 
}
