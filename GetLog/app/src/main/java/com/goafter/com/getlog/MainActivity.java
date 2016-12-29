package com.goafter.com.getlog;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    Button btnStart,btnSu,btnTest;
    String cmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnSu = (Button) findViewById(R.id.btnSu);
        btnTest = (Button) findViewById(R.id.btnTest);
        btnStart.setOnClickListener(new ClickButton());
        btnSu.setOnClickListener(new ClickButton());
        btnTest.setOnClickListener(new ClickButton());
    }

    class ClickButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnStart:
                     new Update().execute();
                    break;
                case R.id.btnClear:

                    break;
                case R.id.btnSu:
                    new Thread(new GetSu()).start();
                    break;
                case R.id.btnTest:
                    new Thread(new TestCmd()).start();
                    break;
                default:
                    break;

            }
            //startCmd();

        }
    }


    class Update extends AsyncTask<String, String, String > {
        @Override
        protected String doInBackground(String... params) {

            //String[] cmd = new String[]{"mkdirs", "/mnt/sdcard/logs"};
/*            File logfile = new File("/mnt/sdcard/logs/ap.log");
            try {
                logfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            String[] cmd = new String[]{"logcat","-f", "/mnt/sdcard/logs/ap.log"};
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
                System.err.print(buffer.toString());

                return buffer.toString();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

/*        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvDisplayResult.setText(s);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvDisplayResult.setText(values[0]);
        }*/
    }

    class TestCmd implements Runnable {
        public void run() {

            //String[] cmd = new String[]{"ls",Environment.getExternalStorageDirectory().getAbsolutePath()};
            String[] cmd = new String[]{"logcat"};
    /*        try {
                Process p = Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try {
                Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();


                //int status = p.waitFor();
                //Log.e("status code: ", status + "");
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                File logfile = new File("/sdcard/ap.log");
                BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));
                while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.write("\n");
                    bw.flush();
                    //Log.e("Result: ", line);
                    //System.err.println(line);


                }

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    class GetSu implements Runnable{
        @Override
        public void run() {
            try {
                Process p = new ProcessBuilder("su").redirectErrorStream(true).start();


                int status = p.waitFor();
                Log.e("status code: ", status + "");
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    Log.e("Result: ", line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
