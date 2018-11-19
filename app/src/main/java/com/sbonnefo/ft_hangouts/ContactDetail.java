package com.sbonnefo.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    private TextView _fullname, _phone, _email, _address, _birthday;
    private ImageButton _btnEdit, _btnSms, _btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
    }
}
