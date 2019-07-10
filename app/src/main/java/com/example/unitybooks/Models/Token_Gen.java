package com.example.unitybooks.Models;

public class Token_Gen {
    private String token, user,_id;
    public String get_id() {
        return _id;
    }
    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }
    public void set_id(String _id) {this._id = _id;}
    public String getToken() { return token;}
    public void setToken(String token){ this.token = token; }
}
