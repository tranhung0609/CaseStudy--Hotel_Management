package model;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bill implements Serializable {
    public static int VALUE;
    private int idBill;
    private String customerName , staffName;
    private LocalDate startDate , endDate;
    private Room room;

    public Bill() {
    }

    public Bill( String customerName, String staffName, LocalDate startDate, LocalDate endDate, Room room) {
        this.idBill = ++VALUE;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }

    public Bill(int idBill, String customerName, String staffName, LocalDate startDate, LocalDate endDate, Room room) {
        this.idBill = idBill;
        this.customerName = customerName;
        this.staffName = staffName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotalPrice() {
        return (room.getRentalPrice() * (DAYS.between(startDate, endDate)));
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", customerName='" + customerName + '\'' +
                ", staffName='" + staffName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", room=" + room +
                '}';
    }
}
