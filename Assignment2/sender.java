import java.util.LinkedList;
import java.util.Iterator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class sender 
{
   public static void main(String[] args) throws Exception 
   {

   checkCommandLineArguments(args);

   String emulatorAddress = args[0];
   int emulatorPort = Integer.parseInt(args[1]);
   int senderPort = Integer.parseInt(args[2]);
   String fileName = args[3];

   int base = 0;

   
   int nextSeqNum = 0;

   // Stores whether the EOT packet is sent yet 
   boolean isEOTPacketSent = false;

   LinkedList<packet> unACKedPacketsSent = new LinkedList<packet>();

  
   BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
   BufferedWriter seqNumLogWriter = new BufferedWriter(new FileWriter("seqnum.log"));
   BufferedWriter ACKLogWriter = new BufferedWriter(new FileWriter("ack.log"));

   
   DatagramSocket senderSocket = new DatagramSocket(senderPort);
 
   timeouttask timeOutTask = new timeouttask(unACKedPacketsSent,
      emulatorAddress, emulatorPort, senderSocket, seqNumLogWriter);
   timer senderTimer = new timer(timeOutTask);

   // Sends packets to the emulator until an EOT packet is received
   while (true) 
   {
      if (!isWindowFull(base, nextSeqNum) && !isEOTPacketSent) 
      { 
         char[] charsRead = new char[packet.MAX_DATA_LENGTH];
            
         
         int numOfCharsRead = fileReader.read(charsRead, 0, packet.MAX_DATA_LENGTH);
            
         packet packetToEmulator = null;
 
         if (numOfCharsRead == -1) 
         { 
            packetToEmulator = packet.createEOT(nextSeqNum);
   
            // Indicates that the EOT packet is sent
            isEOTPacketSent = true;
         } 
         else 
         {
            packetToEmulator = packet.createData(nextSeqNum, new String(charsRead, 0, numOfCharsRead));
         } 

        
         unACKedPacketsSent.offer(packetToEmulator);

         
         packetToEmulator.sendTo(emulatorAddress, emulatorPort, senderSocket);

         if (base == nextSeqNum) 
         {  
            
            senderTimer.start(); 
         } 

         if (!packetToEmulator.isEOT()) 
         { 
            
            seqNumLogWriter.write(String.valueOf(nextSeqNum));
            seqNumLogWriter.newLine();
         } 

    
         nextSeqNum = (nextSeqNum + 1) % packet.SEQ_NUM_MODULO;
      } 
      else 
      {
         packet packetFromEmulator = packet.receiveFrom(senderSocket);

         
         int seqNum = packetFromEmulator.getSeqNum();

         if (!packetFromEmulator.isEOT()) 
         { 
             
            ACKLogWriter.write(String.valueOf(seqNum));
            ACKLogWriter.newLine();
         }

         base = (seqNum + 1) % packet.SEQ_NUM_MODULO;

         
         removeACKedPacketsSent(unACKedPacketsSent, base);

         if (base == nextSeqNum) 
         { 
            
            senderTimer.stop();
                              
            if (packetFromEmulator.isEOT()) 
            { 
               break;
            } // if
         } 
         else 
         {
            
            senderTimer.restart();
         } 
      } 
   }

   senderSocket.close();
      
   ACKLogWriter.close();
   seqNumLogWriter.close();
   fileReader.close();
   } 

   private static void checkCommandLineArguments(String[] args) throws Exception 
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
         System.out.println("ERROR: Expecting a sender port which is" +
            " an integer, but got " + args[2]);
      } 
   }

   // Checks if a string is a valid IP address, which ranges from 0.0.0.0 to 255.255.255.255
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

   // Checks if the window is full, that is, whether there are ten outstanding, unacknowledged packets
   private static boolean isWindowFull(int base, int nextSeqNum) 
   {
      final int WINDOW_SIZE = 10;
      if ((base + WINDOW_SIZE) >= packet.SEQ_NUM_MODULO) 
      {
         
         if ((nextSeqNum >= base) && (nextSeqNum < packet.SEQ_NUM_MODULO)) 
         {
            return false;
         } 
         else if ((nextSeqNum >= 0) && (nextSeqNum < ((base + WINDOW_SIZE) % packet.SEQ_NUM_MODULO))) 
         {
            return false;
         } 
         else 
         {
            return true;
         } 
      
      } 
      else 
      {
         
         if ((nextSeqNum >= base) && (nextSeqNum < (base + WINDOW_SIZE)))
         {
            return false;
         } 
         else 
         {
            return true;
         } 
      
      }
   }
   private static void removeACKedPacketsSent(LinkedList<packet> unACKedPacketsSent,int base)
   {
      Iterator<packet> it = unACKedPacketsSent.iterator();
      
      while (it.hasNext()) 
      {
         packet packetToEmulator = it.next();
         
         if (packetToEmulator.getSeqNum() == base) 
         {
            break;
         } 
         else 
         {
            it.remove();
         } 
       } 
   } 
}