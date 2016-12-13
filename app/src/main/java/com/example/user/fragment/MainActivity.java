package com.example.user.fragment;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Handler;
import android.os.Message;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private LoginButton loginButton;
    LoginManager lmg;
    private CallbackManager callbackManager;
    AccessToken accessToken;
    private ProfileTracker profileTracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessToken = AccessToken.getCurrentAccessToken();
//        if(accessToken !=null) {
//            Log.v("ppking", ":" + accessToken);
//            Log.v("ppking", "userid:" + accessToken.getUserId());
//        }




        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");



        lmg.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.v("ppking" , "oldProfile : "+ oldProfile );
                Log.v("ppking" , "currentProfile : "+ currentProfile );

                TextView textView = (TextView)findViewById(R.id.textView);
                if(currentProfile !=null) {
                    textView.setText(currentProfile.getFirstName());
                    //dc5d306d
                }
            }
        };


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void login(View v){

    }



}
