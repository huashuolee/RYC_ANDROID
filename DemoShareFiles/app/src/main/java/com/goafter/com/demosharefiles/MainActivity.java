package com.goafter.com.demosharefiles;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File mPrivateRootDir;
    // The path to the "images" subdirectory
    private File mImagesDir;
    // Array of files in the images subdirectory
    File[] mImageFiles;
    // Array of filenames corresponding to mImageFiles
    String[] mImageFilenames;

    // Initialize the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent mResultIntent =
                new Intent("com.example.myapp.ACTION_RETURN_FILE");

        mPrivateRootDir = getFilesDir();

        mImagesDir = new File(mPrivateRootDir, "images");

        mImageFiles = mImagesDir.listFiles();

        setResult(Activity.RESULT_CANCELED, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}