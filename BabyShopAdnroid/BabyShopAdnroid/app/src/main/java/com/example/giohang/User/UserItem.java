package com.example.giohang.User;

public class UserItem
{

    public String Username;
    public String FullName;
    public String Password;
    public String Email;
    public String Birthday;
    public Integer Gender;
    public Integer Phone;
    public String Address;
    public String ImageUser;
    public Integer Points;

    public UserItem(String username, String password, String fullName, String email, String birthday, int gender, int phone, String address, String imageUser, int points) {
        Username = username;
        Password = password;
        FullName = fullName;
        Email = email;
        Birthday = birthday;
        Gender = gender;
        Phone = phone;
        Address = address;
        ImageUser = imageUser;
        Points = points;
    }



    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setGender(Integer gender) {
        Gender = gender;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public void setPoints(Integer points) {
        Points = points;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImageUser() {
        return ImageUser;
    }

    public void setImageUser(String imageUser) {
        ImageUser = imageUser;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
