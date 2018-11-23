package com.sbonnefo.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
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
        setContentView(R.layout.activity_message_view);

        _contact = (Contact) getIntent().getSerializableExtra("Contact");
        _recyclerViewMessages = findViewById(R.id.rclMessagesView);
        _btnSend = findViewById(R.id.btnSend);
        _sms = findViewById(R.id.editTxtMessage);

        _databaseManager = new DatabaseManager(this);


        List<Message> messages = _databaseManager.getMessages(_contact);

        _recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        _recyclerViewMessages.setAdapter(new MessageAdapter(messages));

        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  smsContent = _sms.getText().toString();
                Message sms = new Message(false, _contact, smsContent);
                DatabaseManager databaseManager = MessageView.this.getDatabaseManager();
                // TODO send message
                databaseManager.insertMessage(sms);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MESS VIEW", "DESTROY");
        _databaseManager.close();
    }

    public DatabaseManager getDatabaseManager(){ return _databaseManager; }
}
