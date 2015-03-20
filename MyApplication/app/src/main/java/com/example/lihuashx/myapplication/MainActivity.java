package com.example.lihuashx.myapplication;

import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
   // public final static String EXTRA_MESSAGE = "test";
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /** Called when the user clicks the Send button */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cameraButton = (Button) findViewById(R.id.camera);
        cameraButton.setOnClickListener(new cameraListener());
        Button videoButton = (Button) findViewById(R.id.video);
        videoButton.setOnClickListener(new videoLister());
    }

    private class cameraListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }
    }

    private class videoLister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivity(intent);
        }
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.edit_message1);
        EditText editText2 = (EditText) findViewById(R.id.edit_message2);
        String message1 = editText1.getText().toString();
        String message2 = editText2.getText().toString();
        intent.putExtra("test1", message1);
        intent.putExtra("test2", message2);
        startActivity(intent);
    }

}
