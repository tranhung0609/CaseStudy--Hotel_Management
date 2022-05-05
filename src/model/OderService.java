package model;

import java.io.Serializable;
import java.time.LocalDate;

public class OderService implements Serializable {
    public static int VALUE;
    private int code;
    private Bill bill;
    private Service service;
    private LocalDate orderDate;
    private int quantity;

    public OderService() {
    }

    public OderService(Bill bill, Service service, LocalDate orderDate, int quantity) {
        this.code = ++VALUE;
        this.bill = bill;
        this.service = service;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OderService{" +
                "code=" + code +
                ", bill=" + bill +
                ", service=" + service +
                ", orderDate=" + orderDate +
                ", quantity=" + quantity +
                '}';
    }
}
