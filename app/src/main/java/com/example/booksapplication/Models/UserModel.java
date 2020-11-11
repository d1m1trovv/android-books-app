package com.example.booksapplication.Models;

public class UserModel {
    private int _id;
    private String _name;
    private String _password;
    private String isLogged = "false";

    public UserModel(int _id, String _name, String _password) {
        this._id = _id;
        this._name = _name;
        this._password = _password;
    }
    public UserModel( String _name, String _password) {
        this._id = _id;
        this._name = _name;
        this._password = _password;
    }
    public UserModel() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(String isLogged) {
        this.isLogged = isLogged;
    }
}
