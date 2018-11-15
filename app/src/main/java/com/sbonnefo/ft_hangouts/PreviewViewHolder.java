package com.sbonnefo.ft_hangouts;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviewViewHolder extends RecyclerView.ViewHolder {

    private ImageButton btnEdit, btnCall, btnSms;
    private TextView    txtContactFullname;
    private ImageView   imgAvatar;

    public PreviewViewHolder(View itemView){
        super(itemView);

        txtContactFullname = (TextView) itemView.findViewById(R.id.txtContact);
        imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        btnEdit = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnSms = (ImageButton) itemView.findViewById(R.id.btnSms);
        btnCall = (ImageButton) itemView.findViewById(R.id.btnCall);

    }

    public void bind(Contact contact){
        txtContactFullname.setText(contact.getFirstname() + " " + contact.getName().toUpperCase());
        if (contact.getAvatar() != null){
            Log.i("HOLDER", "No image");
        }
        else {
            imgAvatar.setImageResource(R.drawable.default_avatar);
        }
        // TODO btns events

    }

}
