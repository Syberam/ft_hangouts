package com.sbonnefo.ft_hangouts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ft_hangouts.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager( Context context ){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "Create Table T_contacts ("
                        + "     idContact integer primary key autoincrement,"
                        + "     name text not null,"
                        + "     first_name text not null,"
                        + "     phone_num text not null,"
                        + "     email text not null,"
                        + "     address text not null,"
                        + "     notes text);"

                        + "Create Table T_messages ("
                        + "     idMessage integer primary key autoincrement,"
                        + "     contact integer,"
                        + "     date integer not null,"
                        + "     content test not null)";

        db.execSQL( strSql );
        Log.i("DATABASE", "database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dtrSql = " drop table T_contacts T_messages";
        this.onCreate( db );
        Log.i("DATABSE", "nb new version");
    }

    public void insertContact( Contact contact ){
        String name = contact.getName().replace("'","''");
        String first_name = contact.getFirstname().replace("'", "''");
        String phone_num = contact.getPhone().replace("'", "''");
        String email = contact.getEmail().replace("'","''");
        String address = contact.getAddress().replace("'","''");
        String notes = contact.getNotes().replace("'", "''");

        String strSql = "insert into T_contacts (name, first_name, phone_num, email, address, notes)"
                        + " values ('"
                        + name + "', '"
                        + first_name + "', '"
                        + phone_num + "', '"
                        + email + "', '"
                        + address + "', '"
                        + notes + "')";

        this.getWritableDatabase().execSQL( strSql );
        Log.i("DATABASE", "Contact insert in db");
    }

    public void insertMessage( Message message ){
        int contact = message.getContact().getId();
        String content = message.getMessage();
        Date date = message.getDate();

        String strSql = "insert into T_messages (contact, date, content) "
                        + " values ("
                        + contact + ", "
                        + date.getTime() + ", '"
                        + content + "')";

        this.getWritableDatabase().execSQL( strSql );
        Log.i("DATABASE", "Message insert in db");
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "T_contacts",
                new String[] { "idContact", "name", "first_name", "phone_num", "email", "address", "notes"},
                null, null, null, null, "name asc");
        cursor.moveToFirst();
        while (! cursor.isAfterLast()){
            Contact contact = new Contact(  cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getString(4),
                                    cursor.getString(5),
                                    cursor.getString(6)
                                );
            contacts.add( contact );
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }


}
