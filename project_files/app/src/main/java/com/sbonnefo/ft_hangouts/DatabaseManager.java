package com.sbonnefo.ft_hangouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ft_hangouts.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager( Context context ){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql_createContacts = "Create Table T_contacts ("
                        + "     idContact integer primary key autoincrement,"
                        + "     name text not null,"
                        + "     first_name text not null,"
                        + "     phone_num text not null,"
                        + "     email text not null,"
                        + "     address text not null,"
                        + "     birth text not null,"
                        + "     notes text)";

        String strSql_createMessages = "Create Table T_messages ("
                        + "     idMessage integer primary key autoincrement,"
                        + "     contact integer not null,"
                        + "     io integer not null,"
                        + "     date text not null,"
                        + "     content text not null)";


        db.execSQL( strSql_createContacts );
        db.execSQL( strSql_createMessages );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dtrSql = " drop table T_contacts T_messages";
        this.onCreate( db );
        Log.i("DATABSE", "nb new version");
    }



    public long insertContact( Contact contact ){
        String          name = contact.getName().replace("'","''");
        String          first_name = contact.getFirstname().replace("'", "''");
        String          phone_num = contact.getPhone().replace("'", "''");
        String          email = contact.getEmail().replace("'","''");
        String          address = contact.getAddress().replace("'","''");
        String          notes = contact.getNotes().replace("'", "''");
        DateFormat      dateFormat = new SimpleDateFormat("MM d, yyyy", Locale.ENGLISH);
        String          birth;
        ContentValues   values = new ContentValues();

        if (contact.getBirth() == null)
            birth = "NR";
        else
            birth = dateFormat.format(contact.getBirth());

        values.put("name", name);
        values.put("first_name", first_name);
        values.put("phone_num", phone_num);
        values.put("email", email);
        values.put("address", address);
        values.put("notes", notes);
        values.put("birth", birth);


        return (this.getWritableDatabase().insert("T_contacts", null, values));
    }


    public long updateContact( Contact contact ){
        int             id = contact.getId();
        String          name = contact.getName().replace("'","''");
        String          first_name = contact.getFirstname().replace("'", "''");
        String          phone_num = contact.getPhone().replace("'", "''");
        String          email = contact.getEmail().replace("'","''");
        String          address = contact.getAddress().replace("'","''");
        String          notes = contact.getNotes().replace("'", "''");
        DateFormat      dateFormat = new SimpleDateFormat("MM d, yyyy", Locale.ENGLISH);
        String          birth;
        ContentValues   values = new ContentValues();
        String          where = "idContact=?";
        String[]        whereArgs = {Integer.toString(id)};

        if (contact.getBirth() == null)
            birth = "NR";
        else
            birth = dateFormat.format(contact.getBirth());

        values.put("name", name);
        values.put("first_name", first_name);
        values.put("phone_num", phone_num);
        values.put("email", email);
        values.put("address", address);
        values.put("notes", notes);
        values.put("birth", birth);

        return (this.getWritableDatabase().update("T_contacts", values, where, whereArgs));
    }

    public long deleteContact( Contact contact){
        int         id = contact.getId();
        String      where = "idContact=?";
        String[]    whereArgs = {Integer.toString(id)};

        return (this.getWritableDatabase().delete("T_contacts", where, whereArgs));
    }

    public void insertMessage( Message message ){
        int contact = message.getContact().getId();
        String content = message.getMessage();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                                                DateFormat.SHORT, Locale.ENGLISH);
        String date = dateFormat.format(message.getDate());
        Integer io = message.isIn() ? 0 : 1;

        String strSql = "insert into T_messages (contact, io, date, content) "
                        + " values ("
                        + contact + ", "
                        + io + ", '"
                        + date + "', '"
                        + content + "')";

        this.getWritableDatabase().execSQL( strSql );
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "T_contacts",
                new String[] { "idContact", "name", "first_name", "phone_num", "email", "address", "notes", "birth"},
                null, null, null, null, "name asc, first_name asc");
        cursor.moveToFirst();
        while (! cursor.isAfterLast()){
            String      birth = cursor.getString(7);
            DateFormat  dateFormat = new SimpleDateFormat("MM d, yyyy", Locale.ENGLISH);
            Date        dateBirth = null;

            if (birth.equals("NR")) {
                dateBirth = null;
            }
            else {
                try {
                    dateBirth = dateFormat.parse(birth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Contact contact = new Contact(  cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getString(4),
                                    cursor.getString(5),
                                    cursor.getString(6)
                                );
            if (dateBirth != null){
                contact.setBirth(dateBirth);
            }
            contacts.add( contact );
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }
/*
    public List<Message> getMessages(Contact contact){
        List<Message> messages = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().query("T_messages",
                new String[] { "idMessage", "io", "date", "content"},
                "contact = ?", new String[] {Integer.toString(contact.getId())},
                null, null, "date");
        cursor.moveToFirst();

        while (! cursor.isAfterLast()){
            String      messageDate = cursor.getString(2);
            DateFormat  dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                                                                DateFormat.SHORT, Locale.ENGLISH);
            Date        date = null;
            try {
                date = dateFormat.parse(messageDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean io = cursor.getInt(1) == 1;
            Message message = new Message(io, contact, cursor.getString(3), date);

            messages.add( message );
            cursor.moveToNext();
        }
        return messages;
    }
*/

}
