package com.example.moviecultureapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView txtName , txtMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        //txtName = findViewById(R.id.profile_name);
        //txtMail = findViewById(R.id.profile_email);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email","public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                checkLoginStatus();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                txtName.setText("");
                txtMail.setText("");
                Toast.makeText(MainActivity.this,"Usser Logged out", Toast.LENGTH_LONG).show();

            }else{
                checkLoginStatus();
            }
        }
    };

    private void loadUserProfile(AccessToken accessToken ){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Toast.makeText(MainActivity.this,"Login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivityForResult(intent,0);
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name, last_name, email , id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken() != null){
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }

    }
}
