package com.example.dementor;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Video_Activity extends AppCompatActivity {

    private static int CAMERA_PERMISSION_CODE = 100;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(isCameraPresentInPhone()){
            Log.i("VIDEO_RECORD_TAG", "Camera is Detected");
            getCameraPermission();
        }
        else {
            Log.i("VIDEO_RECORD_TAG", "Camera is NOT Detected");
        }

        findViewById(R.id.third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                //intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(data.getData());
            videoView.start();
            builder.setView(videoView).show();
        }
    }

    private boolean isCameraPresentInPhone(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            return true;
        }
        else{
            return false;
        }
    }

    private void getCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }

}

/*
private static int CAMERA_PERMISSION_CODE = 100;
    private static int VIDEO_PERMISSION_CODE = 101;

    private Uri videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        if(isCameraPresentInPhone()){
            Log.i("VIDEO_RECORD_TAG", "Camera is Detected");
            getCameraPermission();
        }
        else {
            Log.i("VIDEO_RECORD_TAG", "Camera is NOT Detected");
        }
    }

    public void recordVideoButtonPressed(View view){
        recordVideo();
    }

    private boolean isCameraPresentInPhone(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            return true;
        }
        else{
            return false;
        }
    }

    private void getCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }

    private void recordVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_PERMISSION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == VIDEO_PERMISSION_CODE){
            if(resultCode == RESULT_OK){
                videoPath = data.getData();
                Log.i("VIDEO_RECORD_TAG", "Video is Recorded and available at path " + videoPath);
            }
            else if(resultCode == RESULT_CANCELED){
                Log.i("VIDEO_RECORD_TAG", "Recording Video is Cancelled");
            }
            else{
                Log.i("VIDEO_RECORD_TAG", "Recording Video Error");
            }
        }
    }
 */