package modelmanager;

import model.Service;
import sun.dc.pr.PRError;
import write_read_File.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManager {
    public static final String PATHNAME_SERVICE = "src\\FileData\\service.csv";
    private final ArrayList<Service> serviceList;
    private final Scanner scanner = new Scanner(System.in);
    private final IOFile<Service> ioFile = new IOFile<>();

    public ServiceManager() {
        if (new File(PATHNAME_SERVICE).length() == 0) {
            this.serviceList = new ArrayList<>();
        } else {
            this.serviceList = ioFile.readFile(PATHNAME_SERVICE);
        }
    }

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public void addService() {
        boolean check = true;
        Service.VALUE = setValue();
        System.out.println("Nhập tên dịch vụ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá dịch vụ");
        double price = Double.parseDouble(scanner.nextLine());
        for (Service service :
                serviceList) {
            if (service.getServiceName().equalsIgnoreCase(name)) {
                check = false;
                break;
            }
        }
        Service service = null;
        if (check){
            service = new Service(name,price);
            serviceList.add(service);
            ioFile.writeFile(serviceList, PATHNAME_SERVICE);
            writeValue();
            System.out.println(" Thêm dịch vụ " + name + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println(" Tên bị trùng mời nhập lại!!!");
            System.out.println("--------------------");
        }
    }

    public void editService(String name){
        Service editService = null;
        for (Service service:
             serviceList) {
            if (service.getServiceName().equalsIgnoreCase(name)){
                editService = service;
            }
        }
        if (editService != null){
            int index = serviceList.indexOf(editService);
            System.out.println("Nhập giá mới");
            editService.setPriceOfService(Double.parseDouble(scanner.nextLine()));
            serviceList.set(index,editService);
            ioFile.writeFile(serviceList,PATHNAME_SERVICE);
            System.out.println("Sửa thành công");
            System.out.println("--------------");
        } else {

        }
    }

}
