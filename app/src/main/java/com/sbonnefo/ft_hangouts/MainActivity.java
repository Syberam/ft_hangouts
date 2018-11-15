package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   // private LinearLayout _lytContactsView;
    private RecyclerView    recyclerViewContacts;
    private ImageButton     btnGoContact;
    private DatabaseManager _databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        btnGoContact = (ImageButton) findViewById( R.id.btnContact);

        btnGoContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactEdit.class );
                //Intent.putExtra( "Contact", contact );
                startActivity(intent);
            }
        });

        _databaseManager = new DatabaseManager( this );
        // Contact ctct = new Contact("BARELLE", "Margaux",
        //          "0613492146", "mago@gmail.com", "here");
        //     _databaseManager.insertContact(ctct);


        List<Contact> contacts = _databaseManager.getContacts();


        recyclerViewContacts = (RecyclerView) findViewById(R.id.recyclerContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(new ContactsPreviewAdapter(contacts));



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
