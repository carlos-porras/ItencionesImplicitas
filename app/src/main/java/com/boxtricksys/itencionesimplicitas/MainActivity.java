package com.boxtricksys.itencionesimplicitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    }

    private void initializeComponets(){
        call = (Button) findViewById(R.id.call);
        visitWeb = (Button) findViewById(R.id.visitWeb);
        geolocalitation = (Button) findViewById(R.id.geolocalitation);
        takePhoto = (Button) findViewById(R.id.takePhoto);
        sendEmail = (Button) findViewById(R.id.sendEmail);
        getImageGallery = (Button) findViewById(R.id.getImageGallery);
        viewImage = (ImageView) findViewById(R.id.viewImage);

        call.setOnClickListener(this);
        visitWeb.setOnClickListener(this);
        geolocalitation.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        sendEmail.setOnClickListener(this);
        getImageGallery.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

    }
}
