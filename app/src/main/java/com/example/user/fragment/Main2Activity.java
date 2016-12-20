package com.example.user.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;


public class Main2Activity extends AppCompatActivity {
    private LoginManager lmg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void logout(View view){
        lmg.logOut();
        Intent it = new Intent(this , MainActivity.class);
        startActivity(it);
    }
}
