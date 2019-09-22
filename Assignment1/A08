import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Redirect
{
    public static void main(String args[])
    {
    
        
        URL url=new URL("http://www.google.com");
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
         // int code = connection.getResponseCode();
        
        connection.setReadTimeout(5000);
	connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
	
	System.out.println("Request URL..."+url);
	boolean Redirect=false;
	
	int code = connection.getResponseCode();
	
	if(code!=HttpURLConnection.HTTP_OK)
	{
	    if(code==HttpURLConnection.HTTP_MOVED_TEMP || code==HttpURLConnection.HTTP_MOVED_PERM)
	    Redirect=true;
	}
	
	else
	{
	    if(code==HttpURLConnection.HTTP_OK
	    || code==HttpURLConnection.HTTP_FORBIDDEN 
	    || code==HttpURLConnection.HTTP_NOT_FOUND)
	    System.out.println("Error");
	    
	    
	}
	System.out.println("Response code...."+code);
    }
}
