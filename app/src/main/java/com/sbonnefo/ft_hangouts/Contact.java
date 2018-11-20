package com.sbonnefo.ft_hangouts;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {
    private int _id;
    private String _name, _firstname, _email, _address, _phone, _notes;
    private Image _avatar;
    private Date _birth;


    Contact(String name, String firstname, String phone, String email, String address){
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = "";
        _avatar = null;
        _birth = null;
    }

    Contact(int id, String name, String firstname, String phone, String email, String address){
        _id = id;
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = "";
        _avatar = null;
        _birth = null;
    }


    Contact(int id, String name, String firstname, String phone, String email, String address, String notes){
        _id = id;
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
        _avatar = null;
        _birth = null;
    }


    Contact(String name, String firstname, String phone, String email, String address, String notes){
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
    }

    Contact(int id, String name, String firstname, String phone, String email, String address,
            String notes, Date birth){
        _id = id;
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
        _birth = birth;
    }

    Contact(String name, String firstname, String phone, String email, String address, String notes,
            Date birth){
        _name = name;
        _firstname = firstname;
        _email = email;
        _address = address;
        _phone = phone;
        _notes = notes;
        _birth = birth;
    }

    public void setName( String name ){_name = name; }
    public void setFirtname( String firstname ){ _firstname = firstname; }
    public void setEmail( String email ){ _email = email; }
    public void setAddress( String address ){ _address = address; }
    public void setPhone( String phone ){ _phone = phone; }
    public void setBirth( Date birth ){ _birth = birth; }
    public void setNotes( String notes ){ _notes = notes; }
    public void setAvatar( Image avatar ){ _avatar = avatar; }

    public int      getId(){ return (_id); }
    public String   getName(){ return (_name); }
    public String   getFirstname(){ return (_firstname); }
    public String   getEmail(){ return (_email); }
    public String   getAddress(){ return (_address); }
    public String   getPhone(){ return (_phone); }
    public String   getNotes(){ return (_notes); }
    public Date     getBirth(){ return (_birth); }
    public Image    getAvatar(){ return (_avatar); }


    public String toString() {
        String str;

        str = _firstname + " " + _name;
        return str;
    }
}
