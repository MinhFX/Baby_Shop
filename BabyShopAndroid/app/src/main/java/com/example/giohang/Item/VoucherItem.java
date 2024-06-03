package com.example.giohang.Item;

public class VoucherItem {
    public int VoucherID;
    public String VoucherName;
    public int Points;
    public int DiscountPrice;
    public int Category_ID;
    public int Quantity;
    public int Status;
    public VoucherItem(int voucherID, String voucherName, int points, int discountPrice, int category_ID, int quantity, int status)
    {
        this.VoucherID = voucherID;
        this.VoucherName = voucherName;
        this.Points = points;
        this.DiscountPrice = discountPrice;
        this.Category_ID = category_ID;
        this.Quantity = quantity;
        this.Status = status;
    }


    public int getVoucherID() {
        return VoucherID;
    }

    public String getVoucherName() {
        return VoucherName;
    }

    public int getPoints() {
        return Points;
    }

    public int getDiscountPrice() {
        return DiscountPrice;
    }

    public int getCategory_ID() {
        return Category_ID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getStatus() {
        return Status;
    }
}
