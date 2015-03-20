package huashuo.getmac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {
    Button getMAC,sendButton;
    int REQUEST_CODE = 100;
    ImageView myImageView;
    Uri uri;
    Button sendMMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMAC = (Button)findViewById(R.id.getMac);
        sendButton = (Button)findViewById(R.id.sendButton);
        sendMMS = (Button)findViewById(R.id.sendButtonMMS);
        myImageView = (ImageView)findViewById(R.id.myImageView);
        //myImageView.setImageURI(Uri.parse("/sdcard/1425539355727.jpg"));
        Button takepicture = (Button)findViewById(R.id.takepicture);
        getMAC.setOnClickListener(new getMACListener());
        takepicture.setOnClickListener(new takePictureListener());
        sendButton.setOnClickListener(new sendListener());
        sendMMS.setOnClickListener(new sendMMSListener());


    }

    private class getMACListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TextView macAddr = (TextView)findViewById(R.id.macAddr);
            WifiManager Wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
            String macinfo = Wifi.getConnectionInfo().getMacAddress();
            macAddr.setText(macinfo);





        }
    }

    private class sendMMSListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, uri);// uri为你的附件的uri
            intent.putExtra("subject", "it's subject"); //彩信的主题
            intent.putExtra("address", "10086"); //彩信发送目的号码
            intent.putExtra("sms_body", "it's content"); //彩信中文字内容
            intent.putExtra(Intent.EXTRA_TEXT, "it's EXTRA_TEXT");
            intent.setType("image/*");// 彩信附件类型
            intent.setClassName("com.android.mms","com.android.mms.ui.ComposeMessageActivity");
            startActivity(intent);
        }
    }

    private class takePictureListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            File jpgfile = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
            uri = Uri.fromFile(jpgfile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            myImageView.setImageURI(uri);
            //Bitmap bmp = (Bitmap)data.getExtras().get("data");

        }
    }

    private class sendListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            WifiManager WIFI = (WifiManager)getSystemService(Context.WIFI_SERVICE);
            String macinfo = WIFI.getConnectionInfo().getMacAddress();
            //SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage("13466538951", null, macinfo, null, null);
            Uri smsToUri = Uri.parse("smsto:10086");
            Intent intent = new Intent(Intent.ACTION_SENDTO,smsToUri);
            intent.putExtra("sms_body",macinfo);
            startActivity(intent);

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
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
