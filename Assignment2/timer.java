import java.util.Timer;

public class timer 
{
   private static final int DELAY = 150;
   private static final int PERIOD = 150;

   private Timer timer;
   private timeouttask timeOutTask;

   public timer(timeouttask timeOutTask) 
   {
      this.timeOutTask = timeOutTask;
   } 

   // Starts the timer
   public void start() 
   {
      timer = new Timer();
      timeouttask newTimeOutTask = new timeouttask(timeOutTask);
      timer.schedule(newTimeOutTask, DELAY, PERIOD);
   } 

   // Stops the timer
   public void stop() 
   {
      timer.cancel();
   } 

   // Restarts the timer
   public void restart() 
   {
      stop();
      start();
   } 
}
