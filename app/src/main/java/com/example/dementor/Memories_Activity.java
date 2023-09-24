package com.example.dementor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Memories_Activity extends AppCompatActivity {

        private VideoView videoView;
        private static final int PICK_VIDEO_REQUEST = 2;

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_memory);

            videoView = findViewById(R.id.videoView);

            // Open the gallery when the activity starts
            openGallery();
        }

        private void openGallery() {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_VIDEO_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null) {
                // Get the URI of the selected video
                Uri videoUri = data.getData();

                // Set the video URI to the VideoView
                videoView.setVideoURI(videoUri);

                // Start playing the video
                videoView.start();
            }
        }
}

