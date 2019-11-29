import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
//
import java.lang.Object;
class NewClass implements Runnable
{ 
  //final RateLimiter rateLimiter = RateLimiter.create(1);
  String ipAddress;
  public NewClass(String ipAddress)
  {
      this.ipAddress = ipAddress;
  }

  public void run()
  { 
    try
    {
      InetAddress net = InetAddress.getByName(ipAddress); 
      System.out.println("Sending Ping Request to " + ipAddress);  
      if (net.isReachable(5000)) 
        System.out.println("Host is reachable"); 
      else
        System.out.println("Sorry ! We can't reach to this host");
    }
    catch(Exception e)
    {
      System.out.println("Exception Caught:"+e);
    }
  } 
} 

class pingClass 
{
  public static void main(String[] args) throws Exception
  {
    
    System.out.println("FIRST PING TO LOCALHOST");
    for(int i=0;i<60;i++)
    {
      Runnable r = new NewClass("127.0.0.1");
      new Thread(r).start();
      Thread.sleep(1000);
    }
    
    Thread.sleep(30000);
    
    System.out.println("SECOND PING TO LOCALHOST");
    for(int i=0;i<60;i++)
    {
      Runnable r = new NewClass("127.0.0.1");
      new Thread(r).start();
      Thread.sleep(1000);
    }

    Thread.sleep(30000);

    System.out.println("PING TO STACKOVERFLOW.COM");
    for(int i=0;i<60;i++)
    {
      Runnable r = new NewClass("151.101.65.69");
      new Thread(r).start();
      Thread.sleep(1000);
    } 
    
    ArrayList<String> commandList = new ArrayList<String>(); 
          
    commandList.add("ping"); 
    // can be replaced by IP 
    commandList.add("www.google.com");
    // to read the output 

    ProcessBuilder build = new ProcessBuilder(commandList); 
    Process process = build.start();

    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream())); 
    BufferedReader Error = new BufferedReader(new InputStreamReader(process.getErrorStream())); 
    String s = null; 
          
    System.out.println("Standard output: "); 
    while((s = input.readLine()) != null) 
    { 
        System.out.println(s); 
    } 
    System.out.println("error (if any): "); 
    while((s = Error.readLine()) != null) 
    { 
        System.out.println(s); 
    } 
  }
}