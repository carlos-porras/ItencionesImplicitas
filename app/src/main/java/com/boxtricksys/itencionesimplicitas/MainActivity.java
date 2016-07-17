package com.boxtricksys.itencionesimplicitas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button call;
    Button visitWeb;
    Button geolocalitation;
    Button takePhoto;
    Button sendEmail;
    Button getImageGallery;
    ImageView viewImage;

    private Uri pictureUri;

    private static final int RESULT_TAKE_PICTURE = 1;
    private static final int RESULT_GALLERY = 2;

    private int requestCode;

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
                Intent intentWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("http://turisas.com"));
                startActivity(intentWebPage);
                break;
            case R.id.geolocalitation:
                Intent intentLocation = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:4.7161407,-74.2107647"));
                startActivity(intentLocation);
                break;
            case R.id.takePhoto:
                Intent intentTakePhoto = new Intent("android.media.action.IMAGE_CAPTURE");
                pictureUri = Uri.fromFile(getPicture());
                intentTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(intentTakePhoto, RESULT_TAKE_PICTURE);
                break;
            case R.id.sendEmail:
                Intent intentSendEmail = new Intent(Intent.ACTION_SEND);
                intentSendEmail.setType("text/plain");
                intentSendEmail.putExtra(Intent.EXTRA_SUBJECT, "Test Subject email");
                intentSendEmail.putExtra(Intent.EXTRA_TEXT, "Body Message");
                intentSendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{
                        "carlos.porras.0508@gmail.com",
                        "alfonsogallegost@gmail.com",
                        "elchaconco@gmail.com",
                        "wcastiblancoq@gmail.com"
                });
                startActivity(intentSendEmail);
                break;

            case R.id.getImageGallery:
                Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
                intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
                intentGallery.setType("image/*");
                startActivityForResult(intentGallery, RESULT_GALLERY);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        if(pictureUri != null){
            outState.putString("KEY_URI_PICTURE", pictureUri.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (requestCode == RESULT_TAKE_PICTURE) {
            pictureUri = Uri.parse(savedInstanceState.getString("KEY_URI_PICTURE"));
            showPictureInToImageView();
        }
    }

    private void showPictureInToImageView() {
        viewImage.setImageURI(pictureUri);
    }

    public File getPicture() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + "img_" + (System.currentTimeMillis() / 1000 + ".jpg"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.requestCode = requestCode;
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == RESULT_TAKE_PICTURE  && pictureUri != null){
                showPictureInToImageView();
            }else if(requestCode == RESULT_GALLERY){
                pictureUri = Uri.parse(data.getDataString());
                showPictureInToImageView();

            }
        }
    }
}
