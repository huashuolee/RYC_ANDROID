package com.goafter.demoping;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    String url;
    EditText etUrl;
    TextView tvDisplayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new ClickButton());
        etUrl = (EditText) findViewById(R.id.etUrl);
        url = etUrl.getText().toString();
        Log.e("11111111", url);
        tvDisplayResult = (TextView) findViewById(R.id.tvDisplayResult);


    }

    class ClickButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            //startCmd();
            new Update().execute(url);
        }
    }


    class Update extends AsyncTask<String, String, String >{
        @Override
        protected String doInBackground(String... params) {

            String[] cmd = new String[]{"ping","-c","4","127.0.0.1"};
            StringBuffer buffer = new StringBuffer();

            try {
                Process p = new ProcessBuilder()
                        .command(cmd)
                        .redirectErrorStream(true)
                        .start();
                int status = p.waitFor();
                Log.e("status code: ", status+"");
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                String result = "";
                while ((line = br.readLine()) != null) {

                    //Log.e("Result: ", line);
                    buffer.append(line);
                    buffer.append("\n");

                }
                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvDisplayResult.setText(s);

        }
    }


}
