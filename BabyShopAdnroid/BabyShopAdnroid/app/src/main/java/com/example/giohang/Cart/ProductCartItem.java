package com.example.giohang.Cart;

public class ProductCartItem
{
    public int ProductID;
    public int Quantity;
    public int Price;
    public String ProductName;
    public String ImageProduct;

    public ProductCartItem(int productID, int quantity, int price, String productName, String imageProduct) {
        ProductID = productID;
        Quantity = quantity;
        Price = price;
        ProductName = productName;
        ImageProduct = imageProduct;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
    }
}
