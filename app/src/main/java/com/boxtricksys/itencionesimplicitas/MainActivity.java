package com.boxtricksys.itencionesimplicitas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button call;
    Button visitWeb;
    Button geolocalitation;
    Button takePhoto;
    Button sendEmail;
    Button getImageGallery;
    ImageView viewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeComponets();
        this.initializeEvents();

    }

    private void initializeComponets() {
        call = (Button) findViewById(R.id.call);
        visitWeb = (Button) findViewById(R.id.visitWeb);
        geolocalitation = (Button) findViewById(R.id.geolocalitation);
        takePhoto = (Button) findViewById(R.id.takePhoto);
        sendEmail = (Button) findViewById(R.id.sendEmail);
        getImageGallery = (Button) findViewById(R.id.getImageGallery);
        viewImage = (ImageView) findViewById(R.id.viewImage);

    }

    private void initializeEvents() {
        call.setOnClickListener(this);
        visitWeb.setOnClickListener(this);
        geolocalitation.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        sendEmail.setOnClickListener(this);
        getImageGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3208378937"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intentCall);
                break;
            case R.id.visitWeb:
                break;
            case R.id.geolocalitation:
                break;
            case R.id.takePhoto:
                break;
            case R.id.sendEmail:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
