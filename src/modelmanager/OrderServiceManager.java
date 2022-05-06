package modelmanager;

import model.Bill;
import model.OderService;
import model.Service;
import write_read_File.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderServiceManager {
    private static final String PATHNAME_ORDER_SERVICE = "src\\filedata\\OrderServices";
    private final ArrayList<OderService> oderServicesList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<OderService> ioFile = new IOFile<>();

    public OrderServiceManager() {
        if (new File(PATHNAME_ORDER_SERVICE).length() == 0) {
            this.oderServicesList = new ArrayList<>();
        } else {
            this.oderServicesList = ioFile.readFile(PATHNAME_ORDER_SERVICE);
        }
    }

    public ArrayList<OderService> getOderServicesList() {
        return oderServicesList;
    }

    public void addOderService(Bill bill, Service service, LocalDate orderDate) {
        OderService.VALUE = setValue();
        System.out.println("Nhập số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        OderService oderService = new OderService(bill, service, orderDate, quantity);
        oderServicesList.add(oderService);
        ioFile.writeFile(oderServicesList, PATHNAME_ORDER_SERVICE);
        writeValue();
        System.out.println("Thêm dịch vụ " + service.getServiceName() + " của phòng " + bill.getRoom().getRoomName() + " thành công !!!");
        System.out.println("------------------------------");
    }

    public void deleteByRoomNameAndServiceName(String roomName, String serviceName, LocalDate orderDate) {
        OderService oderService = null;
        for (OderService order :
                oderServicesList) {
            if (order.getBill().getRoom().getRoomName().equals(roomName) && order.getService().getServiceName().equalsIgnoreCase(serviceName) && order.getOrderDate().isEqual(orderDate)) {
                oderService = order;
            }
        }
        if (oderService != null) {
            oderServicesList.remove(oderService);
            ioFile.writeFile(oderServicesList, PATHNAME_ORDER_SERVICE);
            System.out.println("Xóa dịch vụ " + serviceName + " của phòng " + roomName + " thành công !!!");
            System.out.println("---------------------------");
        } else {
            System.out.println("Không tìm thấy dịch vụ cần xóa ");
            System.out.println("-------------------------------");
        }
    }

    public double getTotalInMonth(int month, int year) {
        double total = 0;
        for (OderService orderService : oderServicesList
        ) {
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (orderService.getOrderDate().getMonth().getValue() == month && orderService.getOrderDate().getYear() == year) {
                total += getTotalOrderService;
            }
        }
        return total;
    }

    public double getTotalCheckOut(String roomName, LocalDate startDate) {
        double totalCheckOut = 0;
        for (OderService oderService :
                oderServicesList) {
            boolean checkRoomName = oderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = oderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = oderService.getQuantity() * oderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                totalCheckOut += getTotalOrderService;
            }
        }
        return totalCheckOut;
    }

    public void displayByRoomName(String roomName, LocalDate startDate) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-10s| %-20s| %-15s| %-10s| %-15s|\n", "Ngày", "Tên dịch vụ", "Giá tiền", "Số lượng", "Thành tiền");
        System.out.println("---------------------------------------------------------------------------------");
        for (OderService orderService : oderServicesList) {
            boolean checkRoomName = orderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = orderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                System.out.printf("| %-10s| %-20s| %-15.2f| %-10s| %-15.2f|\n", orderService.getOrderDate(), orderService.getService().getServiceName(),
                        orderService.getService().getPriceOfService(), orderService.getQuantity(), getTotalOrderService);
                System.out.println("---------------------------------------------------------------------------------");
            }
        }
    }

    public void writeValue() {
        try {
            String PATH_NAME = "src\\filedata\\valueOrderService.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(OderService.VALUE);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "src\\filedata\\valueOrderService.txt";
            File file = new File(PATH_NAME);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_NAME));
                int i;
                if ((i = bufferedReader.read()) != -1) {
                    return i;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
