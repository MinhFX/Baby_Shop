package com.example.giohang.Item;

public class ProductItem {
    public int ProductID;
    public String ProductName;
    public String ImageProduct;
    public String Describes;
    public int Price;
    public int Quantity;
    public int Status;
    public int Category_ID;

    public ProductItem(int ProductID, String ProductName, String ImageProduct, String Describes, int Price, int Quantity, int Status, int Category_ID)
    {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ImageProduct = ImageProduct;
        this.Describes = Describes;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Status = Status;
        this.Category_ID = Category_ID;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public String getDescribes() {
        return Describes;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getStatus() {
        return Status;
    }

    public int getCategory_ID() {
        return Category_ID;
    }
}
