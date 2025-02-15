
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleAlarmClock {
    public static void main(String[] args) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Getting user input for alarm time (hours and minutes)
        System.out.print("Set alarm hour (24-hour format): ");
        int alarmHour = scanner.nextInt();
        
        System.out.print("Set alarm minute: ");
        int alarmMinute = scanner.nextInt();
        
        // Check if the alarm time is valid
        if (alarmHour < 0 || alarmHour > 23 || alarmMinute < 0 || alarmMinute > 59) {
            System.out.println("Invalid time entered. Please enter valid hours (0-23) and minutes (0-59).");
            return;
        }

        // Timer to check the current time
        Timer timer = new Timer();

        // TimerTask to check the time every minute
        TimerTask checkTimeTask = new TimerTask() {
            @Override
            public void run() {
                // Get current system time
                java.time.LocalTime currentTime = java.time.LocalTime.now();
                
                // If current time matches alarm time, trigger the alarm
                if (currentTime.getHour() == alarmHour && currentTime.getMinute() == alarmMinute) {
                    System.out.println("ALARM! It's " + currentTime + ". Time to wake up!");
                    timer.cancel(); // Stop checking after alarm goes off
                }
            }
        };

        // Schedule the task to run every minute (60,000 milliseconds)
        timer.scheduleAtFixedRate(checkTimeTask, 0, 60000); 

        System.out.println("Alarm set for " + alarmHour + ":" + (alarmMinute < 10 ? "0" + alarmMinute : alarmMinute));

        // Keep the program running until the alarm goes off
        try {
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}