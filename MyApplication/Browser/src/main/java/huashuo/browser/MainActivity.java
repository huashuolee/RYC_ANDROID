package huashuo.browser;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
    WebView wv;
    Button back,forward,reload;
    ProgressBar pb;
    String urlrul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button openurl = (Button) findViewById(R.id.openurl);
        Button back = (Button)findViewById(R.id.back);
        Button forward = (Button)findViewById(R.id.forward);
        Button reload = (Button)findViewById(R.id.reload);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
/*        openurl.setOnClickListener(new openrlListener());
        back.setOnClickListener(new backListener());
        forward.setOnClickListener(new forwardListener());
        reload.setOnClickListener(new reloadListener());*/
        wv = (WebView)findViewById(R.id.myWebView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setBuiltInZoomControls(true);
        //wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //wv.getSettings().setLoadWithOverviewMode(true);


    }

    public void nav(View v){
        switch (v.getId()){
            case R.id.openurl:
                EditText url = (EditText)findViewById(R.id.url);
                urlrul = url.getText().toString();
                // Uri uri = Uri.parse("http://"+urlrul);
                // Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                // startActivity(intent);
                wv.loadUrl("http://"+urlrul);
                wv.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        pb.setVisibility(View.VISIBLE);
                        return true;
                    }
                });
                wv.setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        pb.setProgress(newProgress);
                        if(newProgress==100){
                            pb.setVisibility(View.GONE);

                        }
                        super.onProgressChanged(view, newProgress);

                    }
                });
                break;
            case R.id.back:
                if (wv.canGoBack()){
                    wv.goBack();}
                break;
            case R.id.forward:
                if (wv.canGoForward()){
                    wv.goForward();
                }
                break;
            case R.id.reload:
                wv.reload();
                break;
            case R.id.source:
                Intent intent = new Intent(this,SourcePage.class);
                intent.putExtra("url",urlrul);
                startActivity(intent);
                break;


        }


    }

   /* private class openrlListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EditText url = (EditText)findViewById(R.id.url);
            String urlrul = url.getText().toString();
           // Uri uri = Uri.parse("http://"+urlrul);
           // Intent intent = new Intent(Intent.ACTION_VIEW,uri);
           // startActivity(intent);
            wv.loadUrl("http://"+urlrul);
            wv.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    pb.setVisibility(View.VISIBLE);
                    return true;
                }
            });
            wv.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    pb.setProgress(newProgress);
                    if(newProgress==100){
                        pb.setVisibility(View.GONE);

                    }
                    super.onProgressChanged(view, newProgress);

                }
            });
        }
    }

    private class backListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (wv.canGoBack()){
                wv.goBack();
            }
        }
    }

    private class forwardListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (wv.canGoForward()){
                wv.goForward();
            }
        }
    }

    private class reloadListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            wv.reload();
        }
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack())
        {
            // 返回键退回
            wv.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("destroy","destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("PAUSE","PAUSE");
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
