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
	//ȫ�ֲ���
	WebView wv;
	TextView textView, reload;
	ProgressBar progressBar;
	LinearLayout rootViewLayout,errorViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rootViewLayout = new LinearLayout(this);
		rootViewLayout.setOrientation(LinearLayout.VERTICAL);
		// ����ˮƽ������
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
		reload.setText("���¼���..");
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
				// ҳ���������,ȴ������ҳ����Ⱦ�����ʾ����
				// WebChromeClient��progress==100ʱҲ��һ��
				if (wv.getContentHeight() != 0) {
					// ���ʱ����ҳ����ʾ
					
				}
				
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// �������������,�����ⲿ��ת
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
	//���ý�����
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
	//��ʾ�Զ���View
	public void WebViewShow(int errorCode,
			String description) {
		initView(description+":errorCode"+errorCode);
	}
	//���ش���View
	public void WebViewHiden() {
		if(errorViewLayout!=null){
			rootViewLayout.removeView(errorViewLayout);
		}
		wv.setVisibility(View.VISIBLE);
	}
	public void  initView(String description){
		wv.setVisibility(View.GONE);
		errorViewLayout=new LinearLayout(this);
		//��ֵ����
		errorViewLayout.setOrientation(LinearLayout.VERTICAL);
		/**
		 * ͼƬ
		 */
		ImageView wifi=new ImageView(this);
		LinearLayout.LayoutParams wifiParam = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		wifi.setLayoutParams(wifiParam);
		wifi.setScaleType(ScaleType.CENTER);//������ʾ 
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
		reloadbtn.setText("���¼���");
		reloadbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				wv.reload();
			}
		});
		
		//���ø߿�
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
