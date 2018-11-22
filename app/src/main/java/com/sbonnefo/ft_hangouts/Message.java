package com.sbonnefo.ft_hangouts;

import java.util.Date;

public class Message {
    private Contact _contact;
    private boolean _io;
    private String _message;
    private Date _date;

    Message(boolean io, Contact contact, String message) {
        _io = io;
        _contact = contact;
        _message = message;
        _date = new Date();
    }

    Message(boolean io, Contact contact, String message, Date date) {
        _io = io;
        _contact = contact;
        _message = message;
        _date = date;
    }

    public void setDate(Date date) {
        _date = new Date();
    }

    public Contact getContact(){ return _contact ; }
    public String getMessage(){ return _message; }
    public Date getDate(){ return _date;}
    public Boolean isIn(){ return _io; }
}

