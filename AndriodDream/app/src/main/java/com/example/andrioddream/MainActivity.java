package com.example.andrioddream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	//全局参数
	WebView wv;
	TextView textView, reload;
	ProgressBar progressBar;
	LinearLayout rootViewLayout,errorViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rootViewLayout = new LinearLayout(this);
		rootViewLayout.setOrientation(LinearLayout.VERTICAL);
		// 生成水平进度条
		progressBar = new ProgressBar(this, null,
				android.R.attr.progressBarStyleHorizontal);
		progressBar.setIndeterminate(false);
		progressBar.setMax(100);
		progressBar.setProgressDrawable(getResources().getDrawable(
				R.drawable.progress_bar_states));
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 5);
		reload = new TextView(this);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		reload.setLayoutParams(param);
		reload.setText("重新加载..");
		progressBar.setLayoutParams(lp2);
		wv = new WebView(this);
		rootViewLayout.addView(progressBar);
		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		wv.setLayoutParams(lp1);
		rootViewLayout.addView(wv);
		setContentView(rootViewLayout);
		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				WebViewShow(errorCode, description);
				view.loadUrl("javascript:document.body.innerHTML=\"" + description + "\"");  
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				WebViewHiden();
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				// 页面下载完毕,却不代表页面渲染完毕显示出来
				// WebChromeClient中progress==100时也是一样
				if (wv.getContentHeight() != 0) {
					// 这个时候网页才显示
					
				}
				
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 自身加载新链接,不做外部跳转
				view.loadUrl(url);
				return true;
			}

		});
		wv.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				ProgressChanged(newProgress);
			}
		});
		wv.loadUrl("http://www.baidu.com");
	}
	//设置进度条
	public void ProgressChanged(int newProgress){
		if (newProgress == 0) {
			progressBar.setVisibility(View.VISIBLE);
		}
		progressBar.setProgress(newProgress);
		progressBar.postInvalidate();
		if (newProgress == 100) {
			progressBar.setVisibility(View.GONE);
		}
	}
	//显示自定义View
	public void WebViewShow(int errorCode,
			String description) {
		initView(description+":errorCode"+errorCode);
	}
	//隐藏错误View
	public void WebViewHiden() {
		if(errorViewLayout!=null){
			rootViewLayout.removeView(errorViewLayout);
		}
		wv.setVisibility(View.VISIBLE);
	}
	public void  initView(String description){
		wv.setVisibility(View.GONE);
		errorViewLayout=new LinearLayout(this);
		//数值排列
		errorViewLayout.setOrientation(LinearLayout.VERTICAL);
		/**
		 * 图片
		 */
		ImageView wifi=new ImageView(this);
		LinearLayout.LayoutParams wifiParam = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		wifi.setLayoutParams(wifiParam);
		wifi.setScaleType(ScaleType.CENTER);//居中显示 
		wifi.setBackground(getResources().getDrawable(R.drawable.loding_logo));
		
		TextView title=new TextView(this);
		title.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		title.setLayoutParams(titleParam);
		title.setText(description);
		Button reloadbtn=new Button(this);
		reloadbtn.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams reloadParam = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		reloadbtn.setLayoutParams(reloadParam);
		reloadbtn.setText("重新加载");
		reloadbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				wv.reload();
			}
		});
		
		//设置高宽
		LinearLayout.LayoutParams errorParam = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		errorViewLayout.setLayoutParams(errorParam);
		errorViewLayout.setGravity(Gravity.CENTER);
		errorViewLayout.addView(wifi);
		errorViewLayout.addView(title);
		errorViewLayout.addView(reloadbtn);
		rootViewLayout.addView(errorViewLayout);
		
	}
}
