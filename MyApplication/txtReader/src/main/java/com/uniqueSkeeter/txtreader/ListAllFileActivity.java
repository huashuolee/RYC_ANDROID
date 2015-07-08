package com.uniqueSkeeter.txtreader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListAllFileActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_listallfile);
		this.initFileList();
	}

	private void initFileList() {
		File path = android.os.Environment.getExternalStorageDirectory();
		File[] f = path.listFiles();
		this.fill(f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		menu.removeItem(R.id.utf8);
		menu.removeItem(R.id.gb2312);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
		Dialog dialog = new AlertDialog.Builder(ListAllFileActivity.this).setTitle(R.string.about_title)
				.setMessage(R.string.about_info).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialoginterface, int i) {
						// 按钮事件
					}
				}).create();
		dialog.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(ListAllFileActivity.this, ViewFileActivity.class);
		this.bundle = new Bundle();
		File file = this.fileNameList.get(position);
		if (file.isDirectory()) {
			File[] f = file.listFiles();
			this.fill(f);
		} else {
			this.bundle.putString(this.fileNameKey, file.getAbsolutePath());
			intent.putExtras(this.bundle);
			this.startActivityForResult(intent, 0);
		}
	}

	// 读取文件列表,并设置listview
	private void fill(File[] files) {
		this.fileNameList = new ArrayList<File>();
		for (File file : files) {
			if (this.isValidFileOrDir(file)) {
				this.fileNameList.add(file);
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				this.fileToStrArr(this.fileNameList));
		this.setListAdapter(adapter);
	}

	/* 检查是否为合法的文件名，或者是否为路径 */
	private boolean isValidFileOrDir(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String fileName = file.getName().toLowerCase();
			if (fileName.endsWith(".txt")) {
				return true;
			}
		}
		return false;
	}

	private String[] fileToStrArr(List<File> fl) {
		ArrayList<String> fnList = new ArrayList<String>();
		for (int i = 0; i < fl.size(); i++) {
			String nameString = fl.get(i).getName();
			fnList.add(nameString);
		}
		return fnList.toArray(new String[0]);
	}

	/* 文件列表 */
	private List<File> fileNameList;

	private Bundle bundle;

	private final String fileNameKey = "fileName";
}
