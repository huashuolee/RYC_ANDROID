package com.uniqueSkeeter.txtreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewFileActivity extends Activity {

	private String filenameString;

	private static final String gb2312 = "GB2312";

	private static final String utf8 = "UTF-8";

	private static final String defaultCode = gb2312;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_viewfile);
		try {
			Bundle bunde = this.getIntent().getExtras();
			this.filenameString = bunde.getString("fileName");
			this.refreshGUI(defaultCode);
		} catch (Exception e) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.gb2312:
				this.refreshGUI(defaultCode);
				break;
			case R.id.utf8:
				this.refreshGUI(utf8);
				break;
			case R.id.about:
				this.doAbout();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	// 弹出关于框
	private void doAbout() {
		Dialog dialog = new AlertDialog.Builder(ViewFileActivity.this).setTitle(R.string.about_title)
				.setMessage(R.string.about_info).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialoginterface, int i) {
						// 按钮事件
					}
				}).create();
		dialog.show();
	}

	private void refreshGUI(String code) {
		TextView tv = (TextView) this.findViewById(R.id.view_contents);
		String fileContent = this.getStringFromFile(code);
		tv.setText(fileContent);
	}

	public String getStringFromFile(String code) {
		try {
			StringBuffer sBuffer = new StringBuffer();
			FileInputStream fInputStream = new FileInputStream(this.filenameString);
			InputStreamReader inputStreamReader = new InputStreamReader(fInputStream, code);
			BufferedReader in = new BufferedReader(inputStreamReader);
			if (!new File(this.filenameString).exists()) {
				return null;
			}
			while (in.ready()) {
				sBuffer.append(in.readLine() + "\n");
			}
			in.close();
			return sBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 读取文件内容
	public byte[] readFile(String fileName) throws Exception {
		byte[] result = null;
		FileInputStream fis = null;
		try {
			File file = new File(fileName);
			fis = new FileInputStream(file);
			result = new byte[fis.available()];
			fis.read(result);
		} catch (Exception e) {
		} finally {
			fis.close();
		}
		return result;
	}

}
