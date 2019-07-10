package com.example.unitybooks.Models;

public class Books {
    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String bookname) {
        Bookname = bookname;
    }

    public String getBooktype() {
        return Booktype;
    }

    public void setBooktype(String booktype) {
        Booktype = booktype;
    }

    public String getBookauthor() {
        return Bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        Bookauthor = bookauthor;
    }

    public String getBookprice() {
        return Bookprice;
    }

    public void setBookprice(String bookprice) {
        Bookprice = bookprice;
    }

    public String getBookImageName() {
        return BookImageName;
    }

    public void setBookImageName(String bookImageName) {
        BookImageName = bookImageName;
    }

    public Books(String bookname, String booktype, String bookauthor, String bookprice, String bookImageName) {
        Bookname = bookname;
        Booktype = booktype;
        Bookauthor = bookauthor;
        Bookprice = bookprice;
        BookImageName = bookImageName;
    }

    private String Bookname;
    private String Booktype;
    private String Bookauthor;
    private String Bookprice;
    private String BookImageName;
}
