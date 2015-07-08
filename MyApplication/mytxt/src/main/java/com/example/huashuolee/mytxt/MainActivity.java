package com.example.huashuolee.mytxt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class MainActivity extends ActionBarActivity {
    BufferedReader br ;
    FileInputStream fis  ;
    InputStreamReader fisr;
    TextView myText;
    String pre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button nextButton = (Button)findViewById(R.id.nextButton);
        Button preButton = (Button)findViewById(R.id.preButton);
        myText = (TextView)findViewById(R.id.myText);
        loadBook();
        nextButton.setOnClickListener(new nextPage());
    }

    private void loadBook() {
        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "GBK";
        File file = new File(path);
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding),1024);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage() {
        String content = "";
        try {
            int lineNum = 0;
            while (lineNum < 5) {
                content += br.readLine()+"\r\n";
                lineNum++;
            }
            Log.e("11111111", content);
            myText.setText(content);
            pre = content;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected class nextPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            loadPage();

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
