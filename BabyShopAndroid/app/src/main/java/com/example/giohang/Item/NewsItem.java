package com.example.giohang.Item;

public class NewsItem
{
    public int NewsID;
    public int StaffID;
    public String Title;
    public String Content;
    public String Date;
    public int Status;
    public String ImageNews;
    public String Staffname;

    public NewsItem(int newsID, int staffID, String imageNews, String title, String content, String date, int status, String staffname) {
        NewsID = newsID;
        StaffID = staffID;
        ImageNews = imageNews;
        Title = title;
        Content = content;
        Date = date;
        Status = status;
        Staffname = staffname;
    }

    public String getStaffname() {
        return Staffname;
    }

    public String getImageNews() {
        return ImageNews;
    }

    public int getNewsID() {
        return NewsID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }

    public String getDate() {
        return Date;
    }

    public int getStatus() {
        return Status;
    }
}
