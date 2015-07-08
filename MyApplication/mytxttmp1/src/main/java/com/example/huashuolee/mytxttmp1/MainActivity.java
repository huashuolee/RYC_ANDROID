package com.example.huashuolee.mytxttmp1;

import android.content.Intent;
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
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends ActionBarActivity {
    TextView myText;
    Button nextButton,prevButton;
    BufferedReader br ;
    FileInputStream fis  ;
    InputStreamReader fisr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.main_text);
        nextButton = (Button)findViewById(R.id.nextButton);
        prevButton = (Button)findViewById(R.id.preButton);
        nextButton.setOnClickListener(new nextPage());
        prevButton.setOnClickListener(new prevPage());
        Button startS = (Button)findViewById(R.id.startS);
        Button stopS = (Button)findViewById(R.id.stopS);
        startS.setOnClickListener(new start());
        loadBook();
    }

    protected class start implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }

    private void loadBook() {
        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "GBK";
        File file = new File(path);
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            fis = new FileInputStream(file);
            fisr = new InputStreamReader(fis,"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage() {
        char [] buf = new char[256];
        try {
            fisr.read(buf);
            Log.e("1111111111111",new String(buf));
            myText.setText(new String(buf));
//  下面的代码，会导致丢失文字， 不知道为什么
/*            if (fisr.read(buf)==-1){
                 myText.setText("finished");
            }*/
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

    protected class prevPage implements View.OnClickListener{
        @Override
        public void onClick(View v) {
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
