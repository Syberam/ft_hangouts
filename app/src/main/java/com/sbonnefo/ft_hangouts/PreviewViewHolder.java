package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class PreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Contact     _contact;
    private ImageButton _btnEdit, _btnCall, _btnSms;
    private TextView    _txtContactFullname;
    private ImageView   _imgAvatar;


    public PreviewViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

        _txtContactFullname = (TextView) itemView.findViewById(R.id.txtContact);
        _imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        _btnSms = (ImageButton) itemView.findViewById(R.id.btnSms);
        _btnCall = (ImageButton) itemView.findViewById(R.id.btnCall);
    }

    public void bind(Contact contact){
        _contact = contact;
        _txtContactFullname.setText(contact.getFirstname() + " " + contact.getName().toUpperCase());


        if (contact.getAvatar() != null){
            Log.i("HOLDER", "No image");
        }
        else {
            _imgAvatar.setImageResource(R.drawable.default_avatar);
        }
        // TODO btns events

    }

    public Contact  getContact(){ return _contact; }

    public void onClick(View view){
        Intent intent = new Intent (itemView.getContext(), ContactDetail.class);
        intent.putExtra("Contact", _contact);
        itemView.getContext().startActivity(intent);


    }

}
