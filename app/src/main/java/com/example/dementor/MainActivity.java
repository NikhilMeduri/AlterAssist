package com.example.dementor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;
/* import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
*/
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String myLocation = "", numberCall;
    SmsManager manager = SmsManager.getDefault();

    private static int CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.panicBtn).setOnClickListener(this);
        findViewById(R.id.first).setOnClickListener(this);
        findViewById(R.id.second).setOnClickListener(this);
        findViewById(R.id.third).setOnClickListener(this);
        findViewById(R.id.fifth).setOnClickListener(this);

        if(isCameraPresentInPhone()){
            Log.i("VIDEO_RECORD_TAG", "Camera is Detected");
            getCameraPermission();
        }
        else {
            Log.i("VIDEO_RECORD_TAG", "Camera is NOT Detected");
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.first){
            startActivity(new Intent(MainActivity.this, Contact_Activity.class));
            MainActivity.this.finish();
        }else if(id == R.id.fifth){
            startActivity(new Intent(MainActivity.this, Mood_Activity.class));
        } else if(id == R.id.second){
            startActivity(new Intent(MainActivity.this, Memories_Activity.class));
            MainActivity.this.finish();
        }else if(id == R.id.third){
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            //intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
            startActivityForResult(intent, 1);
        }
        else if (id == R.id.panicBtn) {

            startActivity(new Intent(MainActivity.this, SMS_Activity.class));

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
//else if(id == R.id.third){
//        startActivity(new Intent(MainActivity.this, Video_Activity.class));
//        MainActivity.this.finish();
//    }
    void sendMsg(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new HashSet<>());
        if(!oldNumbers.isEmpty()){
            for(String ENUM : oldNumbers)
                manager.sendTextMessage(ENUM,null,"Im in Trouble!\nSending My Location :\n"+myLocation,null,null);
        }
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