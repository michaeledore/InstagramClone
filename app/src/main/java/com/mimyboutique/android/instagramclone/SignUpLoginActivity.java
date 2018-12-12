package com.mimyboutique.android.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtUserNameLogin, edtPasswordLogin, edtPasswordSignUp;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp= findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username") + " sign up successful",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                        }else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(),
                       new LogInCallback() {
                           @Override
                           public void done(ParseUser user, ParseException e) {
                               if (user != null && e == null){
                                   FancyToast.makeText(SignUpLoginActivity.this,user.get
                                           ("username") + " is logged in successful",Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                                   startActivity(intent);
                               }else {
                                   FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),
                                           FancyToast.LENGTH_LONG,FancyToast.ERROR,
                                           true).show();
                               }
                           }
                       });
            }
        });
    }
}
