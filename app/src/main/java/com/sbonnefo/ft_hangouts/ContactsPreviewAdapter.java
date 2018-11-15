package com.sbonnefo.ft_hangouts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ContactsPreviewAdapter extends RecyclerView.Adapter<PreviewViewHolder> {
    List<Contact> _contacts;

    public ContactsPreviewAdapter(List<Contact> contacts){
        _contacts = contacts;
    }

    @Override
    public PreviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View    view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contact_card,
                        viewGroup, false);
        return new PreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PreviewViewHolder holder, int position ) {
        Contact contact = _contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return _contacts.size();
    }
}
