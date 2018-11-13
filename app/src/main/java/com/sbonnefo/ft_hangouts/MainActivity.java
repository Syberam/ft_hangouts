package com.sbonnefo.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView _contactPreView;
    private DatabaseManager _databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _contactPreView = (TextView) findViewById( R.id.contactPreView);
        _databaseManager = new DatabaseManager( this );
   //     Contact sylvain = new Contact("BONNEFON", "Sylvain",
   //             "sbonnefo@student.42.fr", "here", "0610587883");
   //     _databaseManager.insertContact(sylvain);
        List<Contact> contacts = _databaseManager.getContacts();
        for (int i = 0; i <100 ; i++){
            for (Contact contact : contacts){
                _contactPreView.append( contact.toString() + "\n");
            }
        }

        _databaseManager.close();
    }
}
