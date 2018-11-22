package com.sbonnefo.ft_hangouts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    List<Message> _messages;


    public MessageAdapter(List<Message> messages){
        _messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.recycler_view_item_message_in,
                        viewGroup, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = _messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return _messages.size();
    }
}
