package com.sbonnefo.ft_hangouts;

import java.util.Date;

public class Contact {
    private int _id;
    private String _name, _firstname, _email, _address, _phone, _notes;
    private Date _birth;


    Contact(String name, String firstname, String phone, String email, String address){
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = "";
    }

    Contact(int id, String name, String firstname, String phone, String email, String address){
        _id = id;
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = "";
    }


    Contact(int id, String name, String firstname, String phone, String email, String address, String notes){
        _id = id;
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
    }


    Contact(String name, String firstname, String phone, String email, String address, String notes){
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
    }

    public void setBirth( Date birth ){ _birth = birth; }
    public void setNotes( String notes ){ _notes = notes; }

    public int getId(){ return (_id); }
    public String getName(){ return (_name); }
    public String getFirstname(){ return (_firstname); }
    public String getEmail(){ return (_email); }
    public String getAddress(){ return (_address); }
    public String getPhone(){ return (_phone); }
    public String getNotes(){ return (_notes); }
    public Date getBirth(){ return (_birth); }

    public String toString() {
        return _firstname + _name + "\n\t" + _phone;
    }
}