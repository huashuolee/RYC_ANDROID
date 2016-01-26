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
    Button btnStart,btnClear;
    String url;
    EditText etUrl;
    TextView tvDisplayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnStart.setOnClickListener(new ClickButton());
        btnClear.setOnClickListener(new ClickButton());
        etUrl = (EditText) findViewById(R.id.etUrl);
        tvDisplayResult = (TextView) findViewById(R.id.tvDisplayResult);


    }

    class ClickButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnStart:
                    url = etUrl.getText().toString();
                    tvDisplayResult.setText("START......");
                    new Update().execute(url);
                    break;
                case R.id.btnClear:
                    tvDisplayResult.setText("");
                    break;
                default:
                    break;

            }
            //startCmd();

        }
    }


    class Update extends AsyncTask<String, String, String >{
        @Override
        protected String doInBackground(String... params) {

            String[] cmd = new String[]{"ping", "-c", "4", params[0]};
            StringBuffer buffer = new StringBuffer();

            try {
                Process p = new ProcessBuilder()
                        .command(cmd)
                        .redirectErrorStream(true)
                        .start();
                int status = p.waitFor();
                Log.e("status code: ", status + "");
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    buffer.append(line);
                    buffer.append("\n");
                    publishProgress(buffer.toString());
                    Thread.sleep(1000);


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

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvDisplayResult.setText(values[0]);
        }
    }


}
