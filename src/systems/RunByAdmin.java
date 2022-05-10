package systems;

import account.UserManager;
import login.Login;
import model.Bill;
import model.Room;
import model.Service;
import modelmanager.BillManager;
import modelmanager.OrderServiceManager;
import modelmanager.RoomManager;
import modelmanager.ServiceManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static final int ZERO = 0;
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    public static final int FIVE = 5;
    public static final int SIXTH = 6;
    public static final int SEVENTH = 7;
    public static final int EIGHTH = 8;
    public static final int NINTH = 9;
    private final Scanner scan = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    private final RoomManager roomManager = new RoomManager();
    private final UserManager userManager = new UserManager();
    private final ServiceManager serviceManager = new ServiceManager();
    private final OrderServiceManager orderServiceManager = new OrderServiceManager();

    public RunByAdmin() {
    }

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 9) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case FIRST:
                        menuRoomManager();
                        break;
                    case SECOND:
                        menuBillManager();
                        break;
                    case THIRD:
                        menuServiceManager();
                        break;
                    case FOURTH:
                        menuOrderServiceManager();
                        break;
                    case FIVE:
                        System.out.println("Nhập vào phòng:");
                        scan.nextLine();
                        String roomName = scan.nextLine();
                        System.out.println("Nhập ngày Check-in(dd-mm-yyyy):");
                        String checkIn = scan.nextLine();
                        LocalDate checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        checkOut(roomName, checkInDate);
                        break;
                    case SIXTH:
                        userManager.displayUserList();
                        break;
                    case SEVENTH:
                        System.out.println("Nhập tài khoản muốn xóa:");
                        String accountDelete = scan.nextLine();
                        scan.nextLine();
                        userManager.deleteByName(accountDelete);
                        break;
                    case EIGHTH:
                        System.out.println("Nhập vào tháng:");
                        int month = scan.nextInt();
                        System.out.println("Nhập vào năm:");
                        int year = scan.nextInt();
                        if (month < 1 || month > 12 || year < 2015) {
                            System.out.println("⛔ Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("--------------------");
                        } else {
                            System.out.println("Tổng doanh thu " + month + "/" + year + ": " + getTotalInAMonth(month, year));
                        }
                        break;
                    case NINTH:
                        userManager.displayAll();
                        break;
                    case ZERO:
                        exitOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOfAdmin();
        }
    }

    private double getTotalInAMonth(int month, int year) {
        return (billManager.getTotalBillInAMonth(month, year) + orderServiceManager.getTotalInAMonth(month, year));
    }

    private double getTotal(String roomName, LocalDate checkInDate) {
        return (billManager.getBillCheckOut(roomName, checkInDate) + orderServiceManager.getTotalCheckOut(roomName, checkInDate));
    }

    private void checkOut(String roomName, LocalDate checkInDate) {
        billManager.displayBillCheckOut(roomName, checkInDate);
        System.out.println();
        System.out.println("⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ⛔ ");
        System.out.println();
        orderServiceManager.displayByRoomName(roomName, checkInDate);
        System.out.println();
        System.out.println("⛔ Tổng số tiền phải thanh toán: " + getTotal(roomName, checkInDate));
        System.out.println("--------------------");
    }


    private int choiceOfAdmin() {
        int choice = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN + "╔===================================================╗");
                System.out.println("║         ▂ ▃ ▅ ▆ █ HỆ THỐNG ADMIN █ ▆ ▅ ▃ ▂        ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Quản lý phòng                                ║");
                System.out.println("║>[2]. Quản lý hóa đơn                              ║");
                System.out.println("║>[3]. Quản lý dịch vụ                              ║");
                System.out.println("║>[4]. Khách hàng đặt dịch vụ                       ║");
                System.out.println("║>[5]. Check-Out                                    ║");
                System.out.println("║>[6]. Hiển thị thông tin USER                      ║");
                System.out.println("║>[7]. Xóa USER                                     ║");
                System.out.println("║>[8]. Tính tổng doanh thu theo tháng               ║");
                System.out.println("║>[9]. Hiển thị tất cả thông tin                    ║");
                System.out.println("║>[0]. Đăng xuất                                    ║");
                System.out.println("╚===================================================╝" + ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                choice = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                scan.nextLine();
            }
        }
        return choice;
    }

    private void exitOfAdmin() {
        System.out.println();
        System.out.println("⛔ Đã thoát khỏi hệ thống ADMIN !!!");
        System.out.println("--------------------");
        System.out.println();
        (new Login()).loginSystems();
        System.out.println();
    }


    private void menuRoomManager() {
//        try {
        do {

            System.out.println(ANSI_CYAN + "╔===================================================╗");
            System.out.println("║         ▂ ▃ ▅ ▆ █ QUẢN LÝ PHÒNG █ ▆ ▅ ▃ ▂         ║");
            System.out.println("╠===================================================╣");
            System.out.println("║>[1]. Thêm phòng                                   ║");
            System.out.println("║>[2]. Sửa phòng                                    ║");
            System.out.println("║>[3]. Xóa phòng                                    ║");
            System.out.println("║>[4]. Hiển thị danh sách phòng                     ║");
            System.out.println("║>[5]. Tìm kiếm phòng còn trống theo giá            ║");
            System.out.println("║>[6]. Kiểm tra trạng thái phòng                    ║");
            System.out.println("║>[7]. Hiển thị toàn bộ                             ║");
            System.out.println("║>[0]. Thoát                                        ║");
            System.out.println("╚===================================================╝" + ANSI_RESET);
            System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");

            int choiceRoom = scan.nextInt();

            if (choiceRoom < 0 || choiceRoom > 7) {
                System.out.println();
                System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                System.out.println("--------------------");
                System.out.println();
                menuRoomManager();
            }
            try {
                switch (choiceRoom) {
                    case FIRST:
                        roomManager.addRoom();
                        break;
                    case SECOND:
                        System.out.println("Nhập Id phòng muốn sửa:");
                        int idEdit = scan.nextInt();
                        roomManager.editRoom(idEdit);
                        break;
                    case THIRD:
                        System.out.println("Nhập Id phòng muốn xóa:");
                        int idDelete = scan.nextInt();
                        roomManager.deleteByIdRoom(idDelete);
                        break;
                    case FOURTH:
                        roomManager.displayRoomList();
                        break;
                    case FIVE:
                        System.out.println("Nhập giá trên : ");
                        double lowerPrice = scan.nextDouble();
                        System.out.println("Nhập giá dưới : ");
                        double abovePrice = scan.nextDouble();
                        if (lowerPrice > abovePrice) {
                            System.out.println("⛔ Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("--------------------");
                            return;
                        }
                        roomManager.searchByPriceAndStatus(lowerPrice, abovePrice);
                        break;
                    case SIXTH:
                        System.out.println("Nhập tên phòng:");
                        String name = scan.nextLine();
                        scan.nextLine();
                        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
                        String before = scan.nextLine();
                        LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
                        String after = scan.nextLine();
                        LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        billManager.checkRoomStatus(name, beforeDate, afterDate);
                        break;
                    case SEVENTH:
                        roomManager.displayAll();
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
//                } while (true) ;
            } catch (NumberFormatException | DateTimeParseException | InputMismatchException e) {
                System.out.println();
                System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
                System.out.println("--------------------");
                System.out.println();
                menuRoomManager();
            }
        } while (true);
    }


    private void menuBillManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║        ▂ ▃ ▅ ▆ █ QUẢN LÝ HÓA ĐƠN █ ▆ ▅ ▃ ▂        ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm hóa đơn                                 ║");
                System.out.println("║>[2]. Sửa hóa đơn                                  ║");
                System.out.println("║>[3]. Xóa hóa đơn                                  ║");
                System.out.println("║>[4]. Hiển thị danh sách hóa đơn                   ║");
                System.out.println("║>[5]. Hiển thị danh sách hóa đơn theo phòng        ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceBill = scan.nextInt();
                if (choiceBill < 0 || choiceBill > 5) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuBillManager();
                }
                switch (choiceBill) {
                    case FIRST:
                        System.out.println("Nhập vào phòng muốn thuê:");
                        String name = scan.nextLine();
                        Room room = roomManager.getRoom(name);
                        if (room != null) {
                            billManager.addBill(room);
                        } else {
                            System.out.println("⛔ Phòng trên không tồn tại !!!");
                            System.out.println("--------------------");
                        }
                        break;
                    case SECOND:
                        System.out.println("Nhập Id hóa đơn muốn sửa:");
                        int editId = scan.nextInt();
                        billManager.editBill(editId);
                        break;
                    case THIRD:
                        System.out.println("Nhập Id hóa đơn muốn xóa:");
                        int deleteId = scan.nextInt();
                        billManager.deleteByIdBill(deleteId);
                        break;
                    case FOURTH:
                        billManager.displayBillList();
                        break;
                    case FIVE:
                        System.out.println("Nhập tên phòng:");
                        String roomNameSearch = scan.nextLine();
                        billManager.displayBillListByRoom(roomNameSearch);
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuBillManager();
        }
    }


    private void menuServiceManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║            ▂ ▃ ▅ ▆ █ DỊCH VỤ █ ▆ ▅ ▃ ▂            ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm dịch vụ                                 ║");
                System.out.println("║>[2]. Sửa dịch vụ                                  ║");
                System.out.println("║>[3]. Xóa dịch vụ                                  ║");
                System.out.println("║>[4]. Hiển thị các dịch vụ                         ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceService = scan.nextInt();
                if (choiceService < 0 || choiceService > 4) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuServiceManager();
                }
                switch (choiceService) {
                    case FIRST:
                        serviceManager.addService();
                        break;
                    case SECOND:
                        System.out.println("Nhập tên dịch vụ muốn sửa:");
                        String editName = scan.nextLine();
                        scan.nextLine();
                        serviceManager.editService(editName);
                        break;
                    case THIRD:
                        System.out.println("Nhập tên dịch vụ muốn xóa:");
                        String deleteName = scan.nextLine();
                        serviceManager.deleteServiceByName(deleteName);
                        break;
                    case FOURTH:
                        serviceManager.displayServiceList();
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuServiceManager();
        }
    }


    private void menuOrderServiceManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║             ▂ ▃ ▅ ▆ █ ORDER █ ▆ ▅ ▃ ▂             ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Đặt dịch vụ                                  ║");
                System.out.println("║>[2]. Hủy dịch vụ                                  ║");
                System.out.println("║>[3]. Hiển thị các dịch vụ đã đặt theo phòng       ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceOrderService = scan.nextInt();
                if (choiceOrderService < 0 || choiceOrderService > 3) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("--------------------");
                    System.out.println();
                    menuOrderServiceManager();
                }
                switch (choiceOrderService) {
                    case FIRST:
                        System.out.println("Nhập tên phòng:");
                        String roomName = scan.nextLine();
                        System.out.println("Nhập ngày đặt dịch vụ(dd-mm-yyyy):");
                        String date = scan.nextLine();
                        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        int choiceService = choiceService();
                        if (choiceService < 0 || choiceService > 8) {
                            System.out.println();
                            System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                            System.out.println("--------------------");
                            System.out.println();
                            choiceService();
                        }
                        Bill bill = billManager.getBill(roomName, orderDate);
                        Service service = serviceManager.getService(choiceService);
                        if (bill != null && service != null) {
                            orderServiceManager.addOrderService(bill, service, orderDate);
                        } else {
                            System.out.println("⛔ Phòng trên không tồn tại");
                            System.out.println("--------------------");
                        }
                        break;
                    case SECOND:
                        System.out.println("Nhập tên phòng:");
                        String deleteRoomName = scan.nextLine();
                        System.out.println("Nhập tên dịch vụ:");
                        String deleteService = scan.nextLine();
                        System.out.println("Nhập ngày đặt dịch vụ(dd-mm-yyyy):");
                        String orderDate1 = scan.nextLine();
                        LocalDate orderDate2 = LocalDate.parse(orderDate1, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        orderServiceManager.deleteByRoomNameAndServiceName(deleteRoomName, deleteService, orderDate2);
                        break;
                    case THIRD:
                        System.out.println("Nhập tên phòng:");
                        String roomServiceName = scan.nextLine();
                        scan.nextLine();
                        System.out.println("Nhập ngày Check-in(dd-mm-yyyy):");
                        String start = scan.nextLine();
                        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        orderServiceManager.displayByRoomName(roomServiceName, startDate);
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            System.out.println();
            menuOrderServiceManager();
        }
    }


    private int choiceService() {
        System.out.println("╔===================================================╗");
        System.out.println("║             ▂ ▃ ▅ ▆ █ SERVICE █ ▆ ▅ ▃ ▂           ║");
        System.out.println("╠===================================================╣");
        System.out.println("║>[1]. Coca                    >[5]. Vina           ║");
        System.out.println("║>[2]. 555                     >[6]. Massage Body   ║");
        System.out.println("║>[3]. Hà Nội City Tour        >[7]. Mỳ Hộp Omachi  ║");
        System.out.println("║>[4]. Lavie                   >[8]. Bia Heniken    ║");
        System.out.println("╚===================================================╝");
        System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
        return Integer.parseInt(scan.nextLine());
    }
}