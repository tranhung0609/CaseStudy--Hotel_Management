package login;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
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
                case 1:
                    loginManager();
                    break;
                case 2:
                    break;
                case 0:
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
