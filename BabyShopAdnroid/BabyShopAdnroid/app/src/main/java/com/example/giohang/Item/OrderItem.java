package com.example.giohang.Item;

public class OrderItem {
    public int OrderID;
    public int StaffID;
    public String Username;
    public String FullName;
    public String Address;
    public int Phone;
    public int Total;
    public String OrderDate;
    public String TimeOrder;
    public int Status;

    public OrderItem(int orderID, int staffID, String username, String fullName, String address, int phone, int total, String orderDate, String timeOrder, int status) {
        OrderID = orderID;
        StaffID = staffID;
        Username = username;
        FullName = fullName;
        Address = address;
        Phone = phone;
        Total = total;
        OrderDate = orderDate;
        TimeOrder = timeOrder;
        Status = status;
    }

    public String getFullName() {
        return FullName;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhone() {
        return Phone;
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public String getUsername() {
        return Username;
    }

    public int getTotal() {
        return Total;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public int getStatus() {
        return Status;
    }
}

