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


public class MainActivity extends ActionBarActivity {
    TextView myText;
    Button nextButton;
    String content = "";
    int count = 0;
    int maxLine = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.main_text);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new nextPage());
/*        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "GBK";
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            while (br.readLine()!=null) {
                count ++;
                if (count < maxLine){
                    content += br.readLine() + "\n";
                }
            }
            //content =br.readLine();
            myText.setText(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.e("mytxtreader","file note found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }


    protected class nextPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            count = 0;
            content="";
            String path = File.separator + "sdcard" + File.separator + "a.txt";
            String encoding = "GBK";
            File file = new File(path);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
                while (br.readLine()!=null) {
                    count ++;
                    Log.e("mytxtreader",count+"");
                    if (count < maxLine){
                        content += br.readLine() + "\n";
                    }
                }

                myText.setText(content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                Log.e("mytxtreader","file note found");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


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
