package com.example.user.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.roger.gifloadinglibrary.GifLoadingView;


public class MainActivity extends AppCompatActivity {
    private LoginButton loginButton;
    LoginManager lmg;
    private CallbackManager callbackManager;
    AccessToken accessToken;
    private ProfileTracker profileTracker;
    private GifLoadingView mGifLoadingView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.testView);
        Typeface typeface =Typeface.createFromAsset(getAssets(), "font/font1.ttf");
        textView.setTypeface(typeface);








        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        //取得權限為User_status
//        loginButton.setReadPermissions("user_birthday");
//
//        GraphRequest request = GraphRequest.newMeRequest(
//                accessToken,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.v("ppking" , " :::" +object);
//
//                    }
//                }
//        );
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,link");
//        request.setParameters(parameters);
//        request.executeAsync();
//
//
//        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(
//                    AccessToken oldAccessToken,
//                    AccessToken currentAccessToken) {
//                // Set the access token using
//                // currentAccessToken when it's loaded or set.
//            }
//        };
//        // If the access token is available already assign it.
//        accessToken = AccessToken.getCurrentAccessToken();
//
//        Log.v("ppking" , " accessToken : "+accessToken);



        lmg.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Intent it = new Intent(MainActivity.this , Main2Activity.class);
//                startActivity(it);

                // gif 畫面設定
                mGifLoadingView = new GifLoadingView();
                mGifLoadingView.setImageResource(R.drawable.load);
                mGifLoadingView.show(getFragmentManager(),"");


                //間隔時間5秒跳頁

                new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                       final Intent mainIntent = new Intent(MainActivity.this, Main2Activity.class);
                       startActivity(mainIntent);
                       finish();        // 當跳到另一 Activity 時，讓 MainActivity 結束。
                                        // 這樣可以避免使用者按 back 後，又回到該 activity。
                          }
                        }, 5000);
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

//                TextView textView = (TextView)findViewById(R.id.testView);
//                if(currentProfile !=null) {
//                    textView.setText(currentProfile.getFirstName());
//                    //dc5d306d
//                }
            }
        };


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void finish() {
        LoginManager loginManager = LoginManager.getInstance();
        loginManager.logOut();
        super.finish();

    }
}