package com.sbonnefo.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private Contact     _contact;
    private TextView    _fullname, _phone, _email, _address, _birthday, _notes;
    private ImageButton _btnEdit, _btnSms, _btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        _contact = (Contact) getIntent().getSerializableExtra("Contact");
        _fullname = findViewById(R.id.lblFullname);
        _phone = findViewById(R.id.lblPhone);
        _email = findViewById(R.id.lblEmail);
        _birthday = findViewById(R.id.lblBirth);
        _address = findViewById(R.id.lblAddress);
        _notes = findViewById(R.id.lblNotes);
        setInfos();
    }

    protected void setInfos(){
        _fullname.setText(_contact.getFirstname() + " " + _contact.getName());
        _phone.setText(_contact.getPhone());
        _email.setText(_contact.getEmail());
        if (_contact.getBirth() != null)
            _birthday.setText(_contact.getBirth().toString());
        _address.setText(_contact.getAddress());
        _notes.setText(_contact.getNotes());
    }
}
