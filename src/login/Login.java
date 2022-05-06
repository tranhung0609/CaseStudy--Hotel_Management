package login;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    public static final int ZERO = 0;
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THRICE = 3;
    public static final int FOURTH = 4;
    public static final int FIVE = 5;
    public static final int SIXTH = 6;
    private final Scanner scanner = new Scanner(System.in);

    public Login(){
    }

    public void loginSystems(){
        try {
            menuLogin();
        }catch (NullPointerException | NumberFormatException e){
            System.out.println();
            System.out.println("Đã nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            loginSystems();
        }
    }

    public void menuLogin() throws NumberFormatException{
        do {
            System.out.println();
            System.out.println("               \uD83C\uDFE8 ラブホテル \uD83C\uDFE8");
            System.out.println();
            System.out.println("╔===================================================╗");
            System.out.println("║       ▂ ▃ ▅ ▆ █ QUẢN LÝ KHÁCH SẠN █ ▆ ▅ ▃ ▂       ║");
            System.out.println("╠===================================================╣");
            System.out.println("║>[1]. Đăng nhập                                    ║");
            System.out.println("║>[2]. Đăng ký                                      ║");
            System.out.println("║>[0]. Thoát                                        ║");
            System.out.println("╚===================================================╝");
            System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 2) {
                System.out.println();
                System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                System.out.println("--------------------");
            }
            switch (choice){
                case FIRST:
                    loginManager();
                    break;
                case SECOND:
                    break;
                case ZERO:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    private void loginManager() throws InputMismatchException{
        System.out.println("┎──────────────[ĐĂNG NHẬP]──────────────┒");
        System.out.print("┠ ▹ Nhập tài khoản: ");
        String account = scanner.nextLine();
        System.out.print("┠ ▹ Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.println("┖───────────────────────────────────────┚");

    }

}
