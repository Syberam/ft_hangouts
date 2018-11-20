package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactEdit extends AppCompatActivity {

    private final static int    EDIT_CODE = 1;
    private Contact     _contact;
    private ImageButton _btnSave;
    private EditText    _firstname, _lastname, _phone, _email, _address, _birth, _notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DateFormat dateFormat;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_card);
        _contact = (Contact) getIntent().getSerializableExtra("Contact");
        _btnSave = (ImageButton) findViewById(R.id.btnSave);
        _btnSave.setOnClickListener(btnSaveListener);

        _firstname = (EditText) findViewById(R.id.editFirstname);
        _lastname = (EditText) findViewById(R.id.editLastname);
        _phone = (EditText) findViewById(R.id.editPhone);
        _email = (EditText) findViewById(R.id.editEmail);
        _address = (EditText) findViewById(R.id.editAddress);
        _birth = (EditText) findViewById(R.id.editBirth);
        _notes = (EditText) findViewById(R.id.editNotes);


        if (_contact != null){
            _firstname.setText(_contact.getFirstname());
            _lastname.setText(_contact.getName());
            _phone.setText(_contact.getPhone());
            _email.setText(_contact.getEmail());
            _address.setText(_contact.getAddress());

            if (_contact.getBirth() != null){
                dateFormat = DateFormat.getDateInstance();
                _birth.setText(dateFormat.format(_contact.getBirth()));
            }
            _notes.setText(_contact.getNotes());
        }


    }

    private View.OnClickListener btnSaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String  lastname = _lastname.getText().toString();
            String  firstname = _firstname.getText().toString();
            String  phone = _phone.getText().toString();
            String  email = _email.getText().toString();
            String  address = _address.getText().toString();
            String  notes = _notes.getText().toString();
            Date    birth;

            if (_birth.getText().toString().isEmpty())
                birth = null;
            else
                birth = new Date(_birth.getText().toString());

            if (lastname.isEmpty() || firstname.isEmpty() || phone.isEmpty()){
                Toast.makeText(ContactEdit.this,
                        getString(R.string.toast_empty_field), Toast.LENGTH_LONG).show();
            }
            else {
                _contact.setName(lastname);
                _contact.setFirtname(firstname);
                _contact.setPhone(phone);
                _contact.setEmail(email);
                _contact.setAddress(address);
                _contact.setNotes(notes);
                _contact.setBirth(birth);
                DatabaseManager databaseManager = new DatabaseManager(ContactEdit.this);
                if (databaseManager.updateContact(_contact) != -1) {
                    Toast.makeText(ContactEdit.this,
                            getString(R.string.toast_contact_update, firstname + " " + lastname)
                            , Toast.LENGTH_LONG).show();
                }
                else if (databaseManager.insertContact(_contact) != -1) {
                    Toast.makeText(ContactEdit.this,
                            getString(R.string.toast_contact_save, firstname + " " + lastname)
                            , Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(ContactEdit.this, getString(R.string.toast_contact_save_error), Toast.LENGTH_LONG).show();

                databaseManager.close();
                Intent  intent = new Intent(ContactEdit.this, ContactDetail.class);
                intent.putExtra("ContactBis", _contact);
                Log.i("IN EDIT", _contact.getName());
                setResult(EDIT_CODE, intent);
                finish();
            }
        }
    };


}
