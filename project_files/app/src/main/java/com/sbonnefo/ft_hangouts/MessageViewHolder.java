package com.sbonnefo.ft_hangouts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView    _txtMessage, _date;
    private Message     _message;

    public MessageViewHolder(View itemView){
        super(itemView);
        _txtMessage = (TextView) itemView.findViewById(R.id.txtMessageContent);
        _date = (TextView) itemView.findViewById(R.id.txtMessageDate);

    }

    public void bind(Message message){
        _message = message;
        _txtMessage.setText(_message.getMessage());
        if (_message.getDate() != null)
            _date.setText(DateFormat
                            .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
                            .format(_message.getDate()));

    }

}
