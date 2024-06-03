package com.example.giohang.Item;

public class SliderBannerItem {
    public int ID_banner;
    public String Image;
    public int Status;

    public SliderBannerItem(int id_banner, String image, int status)
    {
        this.ID_banner = id_banner;
        this.Image = image;
        this.Status = status;
    }

    public int getID_banner() {
        return ID_banner;
    }

    public String getImage() {
        return Image;
    }

    public int getStatus() {
        return Status;
    }
}
