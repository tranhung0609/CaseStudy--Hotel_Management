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

        Date now = new Date();
        System.out.println(ANSI_YELLOW + "\t\t\t現在の時刻は : " + now.getHours() + "時 : " + now.getMinutes() + "分");
        System.out.println("\t\t\tしばらくお待ちください!!");
                    System.out.println("\t\t\tホテルラブホテル管理システムへようこそ" + ANSI_RESET);
                    Login login = new Login();
                    login.loginSystems();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
