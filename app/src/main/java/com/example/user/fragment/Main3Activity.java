package com.example.user.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;


public class Main3Activity extends AppCompatActivity {
    private String str;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent it = getIntent();
        str = it.getStringExtra("ppking");

        textView = (TextView)findViewById(R.id.testView);
        textView.setText(str);

    }
    public void logout(View view){
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();
        Intent it = new Intent(this , MainActivity.class);
        startActivity(it);
    }

}
