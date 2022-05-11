package multithreadthing;

import login.Login;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MultithreadThing {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void myMultithreadThing() {

        Timer timer = new Timer();
        Date now = new Date();
        System.out.println(ANSI_YELLOW + "\t\tXin chào quý khách đã đến với khách sạn ラブホテル.\nThời gian hiện tại bây giờ là : " + now.getHours() + "giờ : " + now.getMinutes() + "phút");
        System.out.println("Vui lòng đợi trong giây lát!!");
        TimerTask timerTask = new TimerTask() {
            int count = 5;

            @Override
            public void run() {
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        System.out.print("* ");
                    }
                    System.out.println("");
                    count--;
                } else {
                    System.out.println("Chào mừng bạn đến với hệ thống quản lý của Khách sạn ラブホテル" + ANSI_RESET);
                    Login login = new Login();
                    login.loginSystems();
                    timer.cancel();
                }
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        timer.schedule(timerTask, 0, 500);
    }
}
