package com.sbonnefo.ft_hangouts;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

public class ContactDetail extends AppCompatActivity {

    private final static int    EDIT_CODE = 1;
    private Contact     _contact;
    private TextView    _fullname, _phone, _email, _address, _birthday, _notes;
    private ImageButton _btnEdit, _btnDelete, _btnSms, _btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(MainActivity.getCurrentTheme());
        setContentView(R.layout.activity_contact_detail);

        _contact = (Contact) getIntent().getSerializableExtra("Contact");
        _fullname = findViewById(R.id.lblFullname);
        _phone = findViewById(R.id.lblPhone);
        _email = findViewById(R.id.lblEmail);
        _birthday = findViewById(R.id.lblBirth);
        _address = findViewById(R.id.lblAddress);
        _notes = findViewById(R.id.lblNotes);
        _btnEdit = findViewById(R.id.btnEdit);
        _btnDelete = findViewById(R.id.btnDelete);
        _btnSms = findViewById(R.id.btnSms);
        activateBtnEdit();
        activateBtnDelete();
        activateBtnSms();
        setInfos();

    }

    protected void setInfos(){
        _fullname.setText(_contact.getFirstname() + " " + _contact.getName());
        _phone.setText(_contact.getPhone());

        if (_contact.getEmail().isEmpty())
            _email.setText(getString(R.string.NA));
        else
            _email.setText(_contact.getEmail());

        if (_contact.getBirth() != null) {
            DateFormat dateFormat = DateFormat.getDateInstance();
            _birthday.setText(dateFormat.format(_contact.getBirth()));
        }
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

    protected void activateBtnDelete(){
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteContactAlert = new AlertDialog.Builder(ContactDetail.this);

                deleteContactAlert.setTitle(R.string.DeleteContactTitle);
                deleteContactAlert.setMessage(getString(R.string.WarningDeleteContact,
                        _contact.getFirstname() + " " + _contact.getName().toUpperCase()));
                deleteContactAlert.setPositiveButton(R.string.DeleteBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager databaseManager = new DatabaseManager(ContactDetail.this);
                        databaseManager.deleteContact(_contact);
                        databaseManager.close();
                        Toast.makeText(ContactDetail.this,
                                getString(R.string.contact_delete,
                                        _contact.getFirstname() + " " + _contact.getName()),
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                deleteContactAlert.setNegativeButton(R.string.CancelBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog shouldDelete = deleteContactAlert.create();
                shouldDelete.show();
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

    private void activateBtnSms(){
        _btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetail.this, MessageView.class);
                intent.putExtra("Contact", _contact);
                startActivity(intent);
            }
        });
    }
}
