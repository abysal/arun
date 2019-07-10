package com.example.unitybooks.Models;

public class Users {
    public Users(String fname, String lname, String email, String username, String password, String address, String usertype, String age, String profilePic, String tokens) {
        Fname = fname;
        Lname = lname;
        Email = email;
        Username = username;
        Password = password;
        Address = address;
        Usertype = usertype;
        Age = age;
        ProfilePic = profilePic;
        this.tokens = tokens;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUsertype() {
        return Usertype;
    }

    public void setUsertype(String usertype) {
        Usertype = usertype;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    private String Fname;
    private String Lname;
    private String Email;
    private String Username;
    private String Password;
    private String Address;
    private String Usertype;
    private String Age;
    private String ProfilePic;
    private String tokens;
}
