package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class PreviewViewHolder extends RecyclerView.ViewHolder {

    private Contact     _contact;
    private ImageButton _btnEdit, _btnCall, _btnSms;
    private TextView    _txtContactFullname;
    private ImageView   _imgAvatar;

    public PreviewViewHolder(View itemView){
        super(itemView);

        _txtContactFullname = (TextView) itemView.findViewById(R.id.txtContact);
        _imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        _btnSms = (ImageButton) itemView.findViewById(R.id.btnSms);
        _btnCall = (ImageButton) itemView.findViewById(R.id.btnCall);

    }

    public void bind(Contact contact){
        _contact = contact;
    //    Log.i("DATA", contact.getFirstname() + " " + contact.getName().toUpperCase());
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

}
