package com.example.giohang;

public class GiohangItem {
    public String ProductName;
    public String ImageProduct;
    public int Price;
    public int Quantity;

    public GiohangItem(String ProductName, String ImageProduct, int Price, int Quantity)
    {
        this.ProductName = ProductName;
        this.ImageProduct = ImageProduct;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }
}

