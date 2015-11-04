package com.goafter.demorememberpass;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etAccount, etPassword;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button btnLogin;
    private CheckBox cbRemember;

    @Override
    public void onClick(View v) {
        if (cbRemember.isChecked()) {
            editor.putString("account", etAccount.getText().toString());
            editor.putString("password", etPassword.getText().toString());
            editor.putBoolean("remember_pass", true);


        } else {
            editor.clear();
        }
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAccount = (EditText) findViewById(R.id.etAccount);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        pref = getSharedPreferences("shadow", Context.MODE_PRIVATE);
        editor = pref.edit();
        Boolean isRemember = pref.getBoolean("remember_pass", false);
        if (isRemember) {
            etAccount.setText(pref.getString("account", ""));
            etPassword.setText(pref.getString("password", ""));
            cbRemember.setChecked(true);
        }
    }
}
