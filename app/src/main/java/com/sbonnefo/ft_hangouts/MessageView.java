package com.sbonnefo.ft_hangouts;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.sleep;



public class MessageView extends AppCompatActivity {

    private RecyclerView        _recyclerViewMessages;
    private ImageButton         _btnSend;
    private Contact             _contact;
    private TextView            _sms;
    List<Message>               _messages;
    private static final int    MY_PERMISSIONS_REQUEST_READ_SMS = 19898;
    private static final int    SMS_MAX_LEN = 160;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(true);

        _recyclerViewMessages.setLayoutManager(layoutManager);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST_READ_SMS);
        }
        else {
            refreshSmsInbox();
        }
}

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_READ_SMS);
        }
        else
            refreshSmsInbox();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        Log.i("PERMISSION ", "ICI");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_SMS) {
                if (grantResults.length == 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    refreshSmsInbox();
                } else {
                    finish();
                    Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                refreshSmsInbox();
            //    Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            //  finish();
        }
    }

    public void refreshSmsInbox() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            _messages = _contact.getMessages(this);
        }
        _recyclerViewMessages.setAdapter(new MessageAdapter(_messages));

        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsContent = _sms.getText().toString().trim();
                if (!smsContent.isEmpty()) {
                    sendSms(_contact.getPhone(),  smsContent);
                    _sms.setText("");
                    _recyclerViewMessages.removeAllViews();
                    refreshSmsInbox();
                    _recyclerViewMessages.refreshDrawableState();

                } else {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });


    }

    public void sendSms(String phonenumber,String message)
    {
        SmsManager manager = SmsManager.getDefault();

        int length = message.length();

        if(length > SMS_MAX_LEN)
        {
            ArrayList<String> messagelist = manager.divideMessage(message);
            manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
        }
        else
            manager.sendTextMessage(phonenumber, null, message, null, null);

        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

}
