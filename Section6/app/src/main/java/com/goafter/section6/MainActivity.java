package com.goafter.section6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText etContent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = (EditText) findViewById(R.id.etContent);
        String content = loading();
        if (!TextUtils.isEmpty(content)){
            etContent.setText(content);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveWord();
    }

    private void saveWord() {
        StringBuilder sb = new StringBuilder();
        String content = etContent.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("data", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            BufferedWriter bwriter = new BufferedWriter(writer);
            bwriter.write(content);
            bwriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loading() {
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            FileInputStream fis = openFileInput("data");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ( (line = br.readLine()) != null) {
                sb.append(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException E){
            E.printStackTrace(); }

        return sb.toString();
    }
}
