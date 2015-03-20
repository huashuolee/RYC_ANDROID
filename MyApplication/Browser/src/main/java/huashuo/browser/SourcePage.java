package huashuo.browser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by lihuashx on 2015/3/18.
 */
public class SourcePage extends Activity {
    WebView wv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sourcepage);
        Intent intent = getIntent();
       // String url1 = intent.getStringExtra("url");
        tv = (TextView)findViewById(R.id.tv);

        wv = (WebView)findViewById(R.id.sourcepage);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        wv.setWebViewClient(new MyWebViewClient());
        wv.loadUrl("http://www.baidu.com");
        //wv.setVisibility(View.GONE);



    }

    final class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        public void onPageFinished(WebView view, String url) {
            Log.e("WebView","onPageFinished ");
            view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');"
            );
        }
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            Log.e("HTML", html);

        }
    }
}
