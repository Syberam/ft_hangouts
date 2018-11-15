package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   // private LinearLayout _lytContactsView;
    private TextView _contactPreView, _phonePreView;
    private Button btnGoContact;
    private DatabaseManager _databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        _contactPreView = (TextView) findViewById( R.id.txtContactPreView);
        _phonePreView = (TextView) findViewById( R.id.txtPhoneView);
        btnGoContact = (Button) findViewById( R.id.btnContact);

        btnGoContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, contactCard.class );
                //Intent.putExtra("message", txtInputData.getText().toString() );
                startActivity(intent);
            }
        });


        //_lytContactsVieux = ()
        _databaseManager = new DatabaseManager( this );
//        Contact ctct = new Contact("BARELLE", "Margaux",
//                "0613492146", "mago@gmail.com", "here");
//        _databaseManager.insertContact(ctct);


        List<Contact> contacts = _databaseManager.getContacts();
        for (int i = 0; i <100 ; i++){
            for (Contact contact : contacts){
                _contactPreView.append( contact.toString() + "\n");
                _phonePreView.append( contact.getPhone() + "\n");
            }
        }

        _databaseManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId()){
            case R.id.menu_options:
                Toast.makeText( this, "Go to options", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
