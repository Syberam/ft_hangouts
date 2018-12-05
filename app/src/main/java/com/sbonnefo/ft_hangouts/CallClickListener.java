package com.sbonnefo.ft_hangouts;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;


public class CallClickListener implements View.OnClickListener {

    private static final int    MY_PERMISSIONS_REQUEST_CALL_PHONE = 369;
    private Contact             _contact;
    private Activity            _activity;

    public CallClickListener(Activity activity, Contact contact) {
        _activity = activity;
        _contact = contact;
    }

    @Override
    public void onClick(View view) {

        if (ContextCompat.checkSelfPermission(view.getContext(),
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + _contact.getPhone()));
            view.getContext().startActivity(intent);
        }
        else
            Toast.makeText(view.getContext(), "no permission", Toast.LENGTH_LONG);
    }

    public Activity getActivity() { return _activity;}
    public Contact getContact() { return _contact;}

}
