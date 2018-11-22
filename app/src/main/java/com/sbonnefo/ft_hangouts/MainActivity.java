package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   // private LinearLayout _lytContactsView;
    private RecyclerView    recyclerViewContacts;
    private ImageButton     btnGoContact;
    private DatabaseManager _databaseManager;
    private Date            _pauseDate;
    private boolean         _toastDate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        btnGoContact = (ImageButton) findViewById( R.id.btnContact);
        btnGoContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unsetToastDate();
                Intent intent = new Intent(MainActivity.this, ContactEdit.class );
                //Intent.putExtra( "Contact", contact );
                startActivity(intent);

            }
        });
        refreshList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setToastDate();
        Log.i("PAUSE", "We are on PAUSE");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setToastDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("RESUME", "We are on RESUME");
        refreshList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("STOP", "We are on STOP");
        _pauseDate = Calendar.getInstance().getTime();
        _toastDate = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("RESTART", "We are on RESTART");
        putDateToast();
        refreshList();
    }


    @Override
    protected void onStart() {
        super.onStart();
        putDateToast();
        Log.i("START", "We are on START");
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

    private void refreshList(){
        _databaseManager = new DatabaseManager( this );

        List<Contact> contacts = _databaseManager.getContacts();

        recyclerViewContacts = (RecyclerView) findViewById(R.id.recyclerContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(new ContactsPreviewAdapter(contacts));

        _databaseManager.close();
    }

    public void setToastDate() {
        _toastDate = true;
    }

    public void unsetToastDate(){
        _toastDate = false;
    }


    private void putDateToast(){
        if (_toastDate && _pauseDate != null) {
            for (int i = 0; i < 3; i++) {
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                String toastDate = dateFormat.format(_pauseDate);
                Toast.makeText(this, toastDate, Toast.LENGTH_LONG).show();
                _toastDate = false;
            }
        }
    }
}
