package com.example.huashuolee.mytxttmp1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;


public class MainActivity extends ActionBarActivity {
    CharBuffer buffer = CharBuffer.allocate(128);
    TextView myText;
    Button nextButton,prevButton;
    String content = "";
    int count = 0;
    int maxLine = 100;
    int position=0;
    String TAG = "===========";
    InputStreamReader sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.main_text);
        nextButton = (Button)findViewById(R.id.nextButton);
        prevButton = (Button)findViewById(R.id.prevButton);
        nextButton.setOnClickListener(new nextPage());
        prevButton.setOnClickListener(new prevPage());
        loadBook(0);
        loadPage(0);



    }

    private void loadBook(int position) {
        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "GBK";
        File file = new File(path);
        try {
/*            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            br.read(buffer);*/

            sr = new InputStreamReader(new FileInputStream(file),encoding);



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void loadPage(int position) {
        try {
            sr.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.position(position);
        myText.setText(buffer);
        Log.e(TAG, position + " po " + buffer + "");

    }


    protected class nextPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            //position += 4;
            loadPage(position);


        }
    }

    protected class prevPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //position -= 4;
            loadPage(position);


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