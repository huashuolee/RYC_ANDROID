package huashuo.actionbar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener{
    private MediaPlayer mPlayer;
    private Button bplay, bstop,bpause,bservice,bstopservice;
    private Intent intent;

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bplay = (Button)findViewById(R.id.bplay);
        bstop = (Button)findViewById(R.id.bstop);
        bpause = (Button)findViewById(R.id.bpause);
        //bservice = (Button)findViewById(R.id.bservice);
        //bstopservice = (Button)findViewById(R.id.bstopservice);
        findViewById(R.id.bstopservice).setOnClickListener(this);
        findViewById(R.id.bservice).setOnClickListener(this);
        findViewById(R.id.btnbind).setOnClickListener(this);
        findViewById(R.id.btnunbind).setOnClickListener(this);
        intent = new Intent(MainActivity.this, MyService.class);

        mPlayer = new MediaPlayer();
        try {

                mPlayer.setDataSource("/sdcard/test.mp3");
                mPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

/*        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.start();
            }
        });
        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        bpause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mPlayer.pause();
            }
        });

        bservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });
        bstopservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbind:
                System.out.println("test");
                break;
            case R.id.btnunbind:
                break;
            case R.id.bservice:
                System.out.println("startService");
                startService(intent);

                break;
            case R.id.bstopservice:
                stopService(intent);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        mPlayer.release();
        super.onDestroy();
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
            System.out.println(id);
            System.out.println("settingslallaladla");


            return true;
        }
        if (id == R.id.action_search) {
            System.out.println(id);
            System.out.println("search11111111111111111111111111");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
