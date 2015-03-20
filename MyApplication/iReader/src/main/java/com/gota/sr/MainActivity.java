package com.gota.sr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	ReadView readView;
	BufferedReader reader;
	CharBuffer buffer = CharBuffer.allocate(8000);
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		readView = (ReadView) findViewById(R.id.read_view);
		
		loadBook();
		loadPage(0);
	}

	/**
	 * 将电子书都一部分到缓冲区
	 */
	private void loadBook() {
		AssetManager assets = getAssets();
		try {
			InputStream in = assets.open("documents/The Golden Compass.txt");
			Charset charset = CharsetDetector.detect(in);
			reader = new BufferedReader(new InputStreamReader(in, charset));
			reader.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从指定位置开始载入一页
	 */
	private void loadPage(int position) {
		buffer.position(position);
		readView.setText(buffer);
	}
	
	/**
	 * 上一页按钮
	 */
	public void previewPageBtn(View view) {
		
	}
	
	/**
	 * 下一页按钮
	 */
	public void nextPageBtn(View view) {
		position += readView.getCharNum();
		loadPage(position);
		readView.resize();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
