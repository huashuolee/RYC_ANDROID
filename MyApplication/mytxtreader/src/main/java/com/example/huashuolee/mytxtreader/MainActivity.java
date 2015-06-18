package com.example.huashuolee.mytxtreader;

import android.content.DialogInterface;
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
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;


public class MainActivity extends ActionBarActivity {
    CharBuffer buffer = CharBuffer.allocate(1024);
    TextView myText;
    Button nextButton,prevButton;
    String content = "";
    int count = 0;
    int maxLine = 100;
    int position=0;

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
        String encoding = "UTF-8";
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            br.read(buffer);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadPage(int position) {

        buffer.position(position);
        myText.setText(buffer);


    }


    protected class nextPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            position += 256;
            loadPage(position);
            Log.e("next","next");

        }
    }

    protected class prevPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            position -= 256;
            loadPage(position);
            Log.e("next","next");

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
