package com.example.giohang.Item;

public class ProCategoryItem
{
    public int ProCategoryID;
    public String ProCategoryName;
    public ProCategoryItem(int ProCategoryID, String ProCategoryName)
    {
        this.ProCategoryID = ProCategoryID;
        this.ProCategoryName = ProCategoryName;
    }

    public int getProCategoryID() {
        return ProCategoryID;
    }

    public String getProCategoryName() {
        return ProCategoryName;
    }
}
