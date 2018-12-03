package com.sbonnefo.ft_hangouts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    List<Message> _messages;


    public MessageAdapter(List<Message> messages){
        _messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;

        if (viewType == 1)
            view = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.recycler_view_item_message_in,
                        viewGroup, false);
        else
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.recycler_view_item_message_out,
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

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        int io = _messages.get(position).isIn() ? 1 : 0;
        return (io);
    }
}
