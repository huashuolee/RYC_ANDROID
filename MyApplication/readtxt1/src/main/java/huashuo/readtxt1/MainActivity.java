package huashuo.readtxt1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;


public class MainActivity extends ActionBarActivity {
    String content = "";
    String TAG = "XXXXXXXXXXX";
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.Button);
        myText = (TextView)findViewById(R.id.mytextview);
        button.setOnClickListener(new next());



/*
        读取包内的txt
        InputStream inputStream = getResources().openRawResource(R.raw.a);
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "gbk");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        myText.setText(sb.toString());*/


//下面是读取sdcard里的文件

/*        File file = new File("/sdcard/a.txt");
        Log.e(TAG,"file");
        try {
            FileInputStream ins  = new FileInputStream(file);
            InputStreamReader sb = new InputStreamReader(ins,"gbk");
            BufferedReader br = new BufferedReader(sb);
            String line=null;
            int count=0;
            LineNumberReader LR = new LineNumberReader(br);
            //分行读取
            while (( line = br.readLine()) != null) {
                if (count<10){
                    content += line + "\n";
                }
                count++;
            }
            Log.e("count",count+"");
            Log.e("LineNumberReader",LR.readLine()+"");
            ins.close();

        } catch (FileNotFoundException e) {
            Log.e("xxxx","file not found");
            e.printStackTrace();
        } catch(IOException e){
            Log.e("xxxx","IO error ");
            e.printStackTrace();

        }
        myText.setText(content);*/


 //Reader
        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "gbk";
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            char [] buf = new char[1024];
            int temp = br.read(buf);
            int temp1 = br.read(buf);
            content += new String(buf,0,temp);
            LineNumberReader LR = new LineNumberReader(br);
            LR.getLineNumber();
            Log.e("getLinenumber",LR.getLineNumber()+"");


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            Log.e("xxxx","IO error ");
            e.printStackTrace();
        }
        myText.setText(content);


//分页
/*        String path = File.separator + "sdcard" + File.separator + "a.txt";
        String encoding = "gbk";
        File file = new File(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
            int count = 0;
            while (br.readLine()!=null) {
                if (count <10) {
                    content += br.readLine() + "\n";
                }
                count ++;
            }



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            Log.e("xxxx","IO error ");
            e.printStackTrace();
        }
        myText.setText(content);*/
    }
    protected class next implements View.OnClickListener{
        @Override
        public void onClick(View v) {
/*
            String path = File.separator + "sdcard" + File.separator + "a.txt";
            String encoding = "gbk";
            File file = new File(path);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
                int count = 0;

                while (br.readLine()!=null) {
                    if (count <10) {
                        content += br.readLine() + "\n";
                    }
                    count ++;
                }



            }catch (FileNotFoundException e){
                Log.e("xxxx","File not found");
                e.printStackTrace();
            }catch(IOException e){
                Log.e("xxxx","IO error ");
                e.printStackTrace();
            }
            myText.setText(content);
*/


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
