package com.example.giohang.Item;

public class CategoriesHomeItem {
    public int CategoryID;
    public String CategoryName;
    public String Image;
    public CategoriesHomeItem(int categoryID, String categoryName, String image)
    {
        this.CategoryID = categoryID;
        this.CategoryName = categoryName;
        this.Image = image;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getImage() {
        return Image;
    }
}
