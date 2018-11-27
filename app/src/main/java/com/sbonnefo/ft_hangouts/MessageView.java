package com.sbonnefo.ft_hangouts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

public class MessageView extends AppCompatActivity {

    private RecyclerView    _recyclerViewMessages;
    private ImageButton     _btnSend;
    private DatabaseManager _databaseManager;
    private Contact         _contact;
    private TextView        _sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTheme(MainActivity.getCurrentTheme());
        setContentView(R.layout.activity_message_view);

        _contact = (Contact) getIntent().getSerializableExtra("Contact");
        _recyclerViewMessages = findViewById(R.id.rclMessagesView);
        _btnSend = findViewById(R.id.btnSend);
        _sms = findViewById(R.id.editTxtMessage);

        setTitle(_contact.getFirstname() + " " + _contact.getName().toUpperCase());

        /* to reverse the scroll direction */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        _recyclerViewMessages.setLayoutManager(layoutManager);
        /* _______________________________ */

        setMessages();
        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  smsContent = _sms.getText().toString().trim();
                if (!smsContent.isEmpty()) {
                    Message sms = new Message(false, _contact, smsContent);
                    DatabaseManager databaseManager = MessageView.this.getDatabaseManager();
                    databaseManager.insertMessage(sms);
                    // SmsManager smsManager = SmsManager.getDefault();
                    // smsManager.sendTextMessage(_contact.getPhone(), null, smsContent, null, null);
                    setMessages();
                    _recyclerViewMessages.removeAllViews();
                    _recyclerViewMessages.refreshDrawableState();
                    _sms.setText("");
                }
                else {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

    protected void setMessages(){


        _databaseManager = new DatabaseManager(this);
        List<Message> messages = _databaseManager.getMessages(_contact);


        _recyclerViewMessages.setAdapter(new MessageAdapter(messages));

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MESS VIEW", "DESTROY");
        _databaseManager.close();
    }

    public DatabaseManager getDatabaseManager(){ return _databaseManager; }

}
