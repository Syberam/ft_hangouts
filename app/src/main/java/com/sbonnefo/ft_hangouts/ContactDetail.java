package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private final static int    EDIT_CODE = 1;
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
        _btnEdit = findViewById(R.id.btnEdit);
        activateBtnEdit();
        setInfos();

    }

    protected void setInfos(){
        _fullname.setText(_contact.getFirstname() + " " + _contact.getName());
        _phone.setText(_contact.getPhone());

        if (_contact.getEmail().isEmpty())
            _email.setText(getString(R.string.NA));
        else
            _email.setText(_contact.getEmail());

        if (_contact.getBirth() != null)
            _birthday.setText(_contact.getBirth().toString());
        else
            _birthday.setText(getString(R.string.NA));

        if (_contact.getAddress().isEmpty())
            _address.setText(getString(R.string.NA));
        else
            _address.setText(_contact.getAddress());

        _notes.setText(_contact.getNotes());
    }

    protected void activateBtnEdit(){
        _btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(ContactDetail.this, ContactEdit.class);
                intent.putExtra("Contact", _contact);
                startActivityForResult(intent, EDIT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == EDIT_CODE) {
            _contact = (Contact) data.getSerializableExtra("ContactBis");
            Log.i("RESULT CHANGED", _contact.getName());
        }
        setInfos();
    }
}
