package com.sbonnefo.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MessageView extends AppCompatActivity {

    private RecyclerView    _recyclerViewMessages;
    private DatabaseManager _databaseManager;
    private Contact         _contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        _contact = (Contact) getIntent().getSerializableExtra("Contact");

        _databaseManager = new DatabaseManager(this);


        List<Message> messages = _databaseManager.getMessages(_contact);

        _recyclerViewMessages = findViewById(R.id.rclMessagesView);
        _recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        _recyclerViewMessages.setAdapter(new MessageAdapter(messages));

        _databaseManager.close();
    }
}
