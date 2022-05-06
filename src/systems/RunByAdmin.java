package systems;

import account.UserManage;
import login.Login;
import model.Bill;
import model.Room;
import model.Service;
import modelmanager.BillManager;
import modelmanager.OrderServiceManager;
import modelmanager.RoomManager;
import modelmanager.ServiceManager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {
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

    private final Scanner scanner = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    private final OrderServiceManager orderServiceManager = new OrderServiceManager();
    private final RoomManager roomManager = new RoomManager();
    private final ServiceManager serviceManager = new ServiceManager();
    private final UserManage userManage = new UserManage();

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 9) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("----------------------------------------");
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
                        System.out.println("Nhập vào phòng ");
                        String roomName = scanner.nextLine();
                        System.out.println("Nhập ngày Check-In (dd-mm-yyyy) :");
                        String checkIn = scanner.nextLine();
                        LocalDate checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        checkOut(roomName, checkInDate);
                        break;
                    case SIXTH:
                        userManage.displayUserList();
                        break;
                    case SEVENTH:
                        System.out.println("Nhập tài khoản muốn xóa ");
                        String accountDelete = scanner.nextLine();
                        userManage.deleteByName(accountDelete);
                        break;
                    case EIGHTH:
                        System.out.println("Nhập vào tháng : ");
                        int month = Integer.parseInt(scanner.nextLine());
                        System.out.println("Nhập vào năm ");
                        int year = Integer.parseInt(scanner.nextLine());
                        if (month < 1 || month > 12 || year < 2020) {
                            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
                            System.out.println("----------------------------------");
                        } else {
                            System.out.println("Tổng doanh thu " + month + "/" + year + ": " + getTotalInMonth(month, year));
                        }
                        break;
                    case NINTH:
                        userManage.displayAll();
                        break;
                    case ZERO:
                        exitOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println(" ⛔ Bạn nhập sai dữ liệu mời nhập lại !!!");
            System.out.println("-----------------------------------------");
            System.out.println();
            menuOfAdmin();
        }

    }

    private void checkOut(String roomName, LocalDate checkInDate) {
        billManager.displayBillCheckOut(roomName, checkInDate);
        System.out.println();
        System.out.println("⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔⛔");
        System.out.println();
        System.out.println("Tổng số tiền thanh toán " + getTotal(roomName, checkInDate));
        System.out.println("-----------------------------------");

    }

    private int choiceOfAdmin() {
        System.out.println("╔===================================================╗");
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
        System.out.println("║>[0]. Đăng xuất                                    ║");
        System.out.println("╚===================================================╝");
        System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
        return Integer.parseInt(scanner.nextLine());
    }

    private void exitOfAdmin() {
        System.out.println();
        System.out.println("⛔ Đã thoát khỏi chương trình hệ thống ADMIN !! ");
        System.out.println("-----------------------------------------");
        System.out.println();
        (new Login()).loginSystems();
        System.out.println();
    }

    //    quản lý phòng
    private void menuRoomManager() {
        try {
            do {
                System.out.println("╔===================================================╗");
                System.out.println("║         ▂ ▃ ▅ ▆ █ QUẢN LÝ PHÒNG █ ▆ ▅ ▃ ▂         ║");
                System.out.println("╠===================================================╣");
                System.out.println("║>[1]. Thêm phòng                                   ║");
                System.out.println("║>[2]. Sửa phòng                                    ║");
                System.out.println("║>[3]. Xóa phòng                                    ║");
                System.out.println("║>[4]. Hiển thị danh sách phòng                     ║");
                System.out.println("║>[5]. Tìm kiếm phòng còn trống theo giá            ║");
                System.out.println("║>[6]. Kiểm tra trạng thái phòng                    ║");
                System.out.println("║>[7]. Hiển thị tất cả                              ║");
                System.out.println("║>[0]. Thoát                                        ║");
                System.out.println("╚===================================================╝");
                System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
                int choiceRoom = Integer.parseInt(scanner.nextLine());
                if (choiceRoom < 0 || choiceRoom > 7) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("----------------------------------------------");
                    System.out.println();
                    menuRoomManager();
                }
                switch (choiceRoom) {
                    case FIRST:
                        roomManager.addRoom();
                        break;
                    case SECOND:
                        System.out.println("Nhập ID phòng muốn sửa");
                        int idEdit = scanner.nextInt();
                        roomManager.editRoom(idEdit);
                        break;
                    case THIRD:
                        System.out.println("Nhập ID phòng muốn xóa ");
                        int idDelete = scanner.nextInt();
                        roomManager.deleteByIdRoom(idDelete);
                        break;
                    case FOURTH:
                        roomManager.displayRoomList();
                        break;
                    case FIVE:
                        System.out.println("Nhập giá dưới : ");
                        double lowerPrice = Double.parseDouble(scanner.nextLine());
                        System.out.println("Nhập giá trên : ");
                        double abovePrice = Double.parseDouble(scanner.nextLine());
                        if (lowerPrice > abovePrice) {
                            System.out.println(" ⛔ Nhập sai dữ liệu mời nhập lại ");
                            System.out.println("---------------------------------");
                            return;
                        }
                        roomManager.searchByPriceAndStatus(lowerPrice, abovePrice);
                        break;
                    case SIXTH:
                        System.out.println(" Nhập tên phòng ");
                        String name = scanner.nextLine();
                        System.out.println("Nhập ngày bắt đầu (dd-mm-yyyy)");
                        String before = scanner.nextLine();
                        LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        System.out.println("Nhập ngày kết thúc (dd-mm-yyyy");
                        String after = scanner.nextLine();
                        LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        billManager.checkRoomStatus(name, beforeDate, afterDate);
                        break;
                    case SEVENTH:
                        roomManager.displayAll();
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeException | InputMismatchException e) {
            System.out.println();
            System.out.println(" ⛔ Bạn nhập sai dữ liệu mời nhập lại !!!");
            System.out.println("-----------------------------------------");
            System.out.println();
            menuRoomManager();
        }
    }

    //    quản lý hóa đơn
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
                int choiceBill = Integer.parseInt(scanner.nextLine());
                if (choiceBill < 0 || choiceBill > 5) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("-----------------------------------------------");
                    System.out.println();
                    menuBillManager();
                }
                switch (choiceBill) {
                    case FIRST:
                        System.out.println("Nhập vào phòng muốn thuê ");
                        String name = scanner.nextLine();
                        Room room = roomManager.getRoom(name);
                        if (room != null) {
                            billManager.addBill(room);
                        } else {
                            System.out.println(" ⛔ Phòng trên không tồn tại. Vui lòng nhập lại !!!");
                            System.out.println("--------------------------------------------------");
                        }
                        break;
                    case SECOND:
                        System.out.println("Nhập hóa đơn muốn sửa ");
                        int editId = scanner.nextInt();
                        billManager.editBill(editId);
                        break;
                    case THIRD:
                        System.out.println("Nhập ID của hóa đơn muốn xóa ");
                        int deleteId = scanner.nextInt();
                        billManager.deleteByIdBill(deleteId);
                        break;
                    case FOURTH:
                        billManager.displayBillList();
                        break;
                    case FIVE:
                        System.out.println("Nhập tên phòng ");
                        String roomNameSearch = scanner.nextLine();
                        billManager.displayBillListByRoom(roomNameSearch);
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeException e) {
            System.out.println();
            System.out.println(" ⛔ Bạn nhập sai dữ liệu mời nhập lại !!!");
            System.out.println("-----------------------------------------");
            System.out.println();
            menuBillManager();
        }
    }

    //quản lý dịch vụ
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
                int choiceService = Integer.parseInt(scanner.nextLine());
                if (choiceService < 0 || choiceService > 4) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("-----------------------------------------------");
                    System.out.println();
                    menuServiceManager();
                }
                switch (choiceService) {
                    case FIRST:
                        serviceManager.addService();
                        break;
                    case SECOND:
                        System.out.println("Nhập tên dịch vụ muốn sửa ");
                        String editName = scanner.nextLine();
                        serviceManager.editService(editName);
                        break;
                    case THIRD:
                        System.out.println("Nhập tên dịch vụ muốn xóa");
                        String deleteName = scanner.nextLine();
                        serviceManager.deleteServiceByName(deleteName);
                        break;
                    case FOURTH:
                        serviceManager.displayService();
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
            System.out.println("-----------------------------------------------");
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
                int choiceOrderService = Integer.parseInt(scanner.nextLine());
                if (choiceOrderService < 0 || choiceOrderService > 3) {
                    System.out.println();
                    System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                    System.out.println("-----------------------------------------------");
                    System.out.println();
                    menuOrderServiceManager();
                }
                switch (choiceOrderService) {
                    case FIRST:
                        System.out.println("Nhập tên phòng : ");
                        String roomName = scanner.nextLine();
                        System.out.println("Nhập ngày đặt dịch vụ (dd-mm-yyyy): ");
                        String date = scanner.nextLine();
                        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        int choiceService = choiceService();
                        if (choiceService < 0 || choiceService > 8) {
                            System.out.println();
                            System.out.println("⛔ Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                            System.out.println("-----------------------------------------------");
                            System.out.println();
                            choiceService();
                        }
                        Bill bill = billManager.getBill(roomName, orderDate);
                        Service service = serviceManager.getService(choiceService);
                        if (bill != null && service != null) {
                            orderServiceManager.addOderService(bill, service, orderDate);
                        } else {
                            System.out.println(" ⛔ Phòng trên không tồn tại. Vui lòng nhập lại !!!");
                            System.out.println("---------------------------------------------------");
                        }
                        break;
                    case SECOND:
                        System.out.println("Nhập tên phòng ");
                        String deleteRoomName = scanner.nextLine();
                        System.out.println("Nhập tên dịch vụ ");
                        String deleteService = scanner.nextLine();
                        System.out.println("Nhập ngày đặt dịch vụ (dd-mm-yyyy) : ");
                        String orderDate1 = scanner.nextLine();
                        LocalDate orderDate2 = LocalDate.parse(orderDate1, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        orderServiceManager.deleteByRoomNameAndServiceName(deleteRoomName, deleteService, orderDate2);
                        break;
                    case THIRD:
                        System.out.println("Nhập tên phòng ");
                        String roomServiceName = scanner.nextLine();
                        System.out.println("Nhập ngày check-in (dd-mm-yyyy) : ");
                        String start = scanner.nextLine();
                        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
                        orderServiceManager.displayByRoomName(roomServiceName, startDate);
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println("⛔ Bạn nhập sai dữ liệu, mời bạn nhập lại !!!");
            System.out.println("-----------------------------------------------");
            System.out.println();
            menuOrderServiceManager();
        }
    }

    private int choiceService() {
        System.out.println("╔===================================================╗");
        System.out.println("║             ▂ ▃ ▅ ▆ █ SERVICE █ ▆ ▅ ▃ ▂           ║");
        System.out.println("╠===================================================╣");
        System.out.println("║>[1]. CocaLight               >[5]. Vina           ║");
        System.out.println("║>[2]. Thăng Long              >[6]. Massage Body   ║");
        System.out.println("║>[3]. Hà Nội City Tour        >[7]. Mỳ Hộp Omachi  ║");
        System.out.println("║>[4]. Lavie                   >[8]. Bia Heniken    ║");
        System.out.println("╚===================================================╝");
        System.out.println("[\uD83D\uDD11] Nhập lựa chọn:");
        return Integer.parseInt(scanner.nextLine());
    }
}
