import java.io.FileWriter;
import java.io.BufferedWriter;

import java.net.DatagramSocket;

public class receiver 
{
   public static void main(String[] args) throws Exception 
   {
	      if (args.length != 4) 
	      {
	         System.out.println("ERROR: Expecting 4 command line arguments," +
	            " but got " + args.length + " arguments");
	         System.exit(-1);
	      } 
	      
	      if ((!isValidIPAddress(args[0])) && (!isValidHostName(args[0]))) 
	      {
	         System.out.println("ERROR: Expecting an emulator address which is" +
	            " a valid IP address or host name, but got " + args[0]);
	         System.exit(-1);
	      } 

	      try
	      {
	         int emulatorPort = Integer.parseInt(args[1]);
	      } 
	      catch (NumberFormatException e) 
	      {
	         System.out.println("ERROR: Expecting an emulator port which is" +
	            " an integer, but got " + args[1]);
	         System.exit(-1);
	      } 
	      
	      try 
	      {
	         int senderPort = Integer.parseInt(args[2]);
	      } 
	      catch (NumberFormatException e) 
	      {
	         System.out.println("ERROR: Expecting a receiver port which is" +
	            " an integer, but got " + args[2]);
	      } 
 
      String emulatorAddress = args[0];
      int emulatorPort = Integer.parseInt(args[1]);
      int receiverPort = Integer.parseInt(args[2]);
      String fileName = args[3];

      // Stores the expected sequence number of the next packet to be received
      int expectedSeqNum = 0;
      
      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
      BufferedWriter arrivalLogWriter = new BufferedWriter(new FileWriter("arrival.log"));

      
      DatagramSocket receiverSocket = new DatagramSocket(receiverPort);

      // Receives packets from the emulator until an EOT packet is received
      while (true) 
      {
         
         packet packetFromEmulator = packet.receiveFrom(receiverSocket);
         
         
         int seqNum = packetFromEmulator.getSeqNum();
         
         if (!packetFromEmulator.isEOT()) 
         { 
        	
         
            arrivalLogWriter.write(String.valueOf(seqNum));
            arrivalLogWriter.newLine();
         } 

         if (seqNum == expectedSeqNum) 
         { 
        	
             if (packetFromEmulator.isEOT()) 
             { 
               // Creates an EOT packet with the same sequence number as the received packet 
               // to send to the emulator and writes it out to the receiver socket
               packet packetToEmulator = packet.createEOT(expectedSeqNum);
               packetToEmulator.sendTo(emulatorAddress, emulatorPort, receiverSocket);
               break;
             } 
             else 
             {
              
               String data = new String(packetFromEmulator.getData());
               fileWriter.write(data);

              
               packet packetToEmulator = packet.createACK(expectedSeqNum);
               packetToEmulator.sendTo(emulatorAddress, emulatorPort, receiverSocket);

               expectedSeqNum = (expectedSeqNum + 1) % packet.SEQ_NUM_MODULO;
             } 
         } 
         
         else 
         {
            
            int lastSeqNum = (expectedSeqNum - 1) % packet.SEQ_NUM_MODULO;
            
            if (lastSeqNum < 0) 
            { 
               
               lastSeqNum = lastSeqNum + packet.SEQ_NUM_MODULO;
            }
            
            packet packetToEmulator = packet.createACK(lastSeqNum);
            packetToEmulator.sendTo(emulatorAddress, emulatorPort, receiverSocket);
         } 
      }

      
      receiverSocket.close();
      
      
      arrivalLogWriter.close();
      fileWriter.close();
   } 

   // Checks if a string is a valid IP address, which ranges from 0.0.0.0 to
   // 255.255.255.255
   private static boolean isValidIPAddress(String string) throws Exception 
   {
      String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                     "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                     "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                     "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
      boolean isRegexMatch = string.matches(regex);
      return isRegexMatch;
   } 

   // Checks if a string is a valid host name, which complies with RFC 1912
   private static boolean isValidHostName(String string) throws Exception 
   {
      String regex = "^([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])" +
                     "(\\.([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9]))*$";
      boolean isRegexMatch = string.matches(regex);
      
      boolean isCorrectLength = (string.length() <= 255);
      
      String[] labels = string.split("\\.");
      boolean isLabelsNotAllNumeric = true;
      for (String label : labels) 
      {
         if (label.matches("^[0-9]+$")) 
         {
            isLabelsNotAllNumeric = false;
            break;
         } 
      }

      if (isRegexMatch && isCorrectLength && isLabelsNotAllNumeric) 
      {
         return true;
      } 
      else 
      {
         return false;
      } 
   }
}
