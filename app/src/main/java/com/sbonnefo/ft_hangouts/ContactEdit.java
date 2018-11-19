package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactEdit extends AppCompatActivity {

    private ImageButton _btnSave;
    private EditText _firstname, _lastname, _phone, _email, _address, _birth, _notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_card);
        _btnSave = (ImageButton) findViewById(R.id.btnSave);
        _btnSave.setOnClickListener(btnSaveListener);

        _firstname = (EditText) findViewById(R.id.editFirstname);
        _lastname = (EditText) findViewById(R.id.editLastname);
        _phone = (EditText) findViewById(R.id.editPhone);
        _email = (EditText) findViewById(R.id.editEmail);
        _address = (EditText) findViewById(R.id.editAddress);
        _birth = (EditText) findViewById(R.id.editBirth);
        _notes = (EditText) findViewById(R.id.editNotes);


    }



    private View.OnClickListener btnSaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String lastname = _lastname.getText().toString();
            String firstname = _firstname.getText().toString();
            String phone = _phone.getText().toString();
            String email = _email.getText().toString();
            String address = _address.getText().toString();
            String birth = _birth.getText().toString();
            String notes = _notes.getText().toString();
            if (lastname.isEmpty() || firstname.isEmpty() || phone.isEmpty()){
                Toast.makeText(ContactEdit.this,
                        getString(R.string.toast_empty_field), Toast.LENGTH_LONG).show();
            }
            else {
                DatabaseManager databaseManager = new DatabaseManager(ContactEdit.this);
                Contact ctct = new Contact(lastname, firstname, phone, email, address);
                databaseManager.insertContact(ctct);
                Toast.makeText(ContactEdit.this,
                        getString(R.string.toast_contact_save, _firstname + " " + _lastname)
                        , Toast.LENGTH_LONG).show();
                databaseManager.close();
            }
        }
    };

}
