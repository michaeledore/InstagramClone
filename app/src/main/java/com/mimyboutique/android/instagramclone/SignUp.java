package com.mimyboutique.android.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private EditText edtKickBoxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsave = findViewById(R.id.btnsave);
        edtKickBoxerName = findViewById(R.id.edtKickBoxerName);
        edtKickPower = findViewById(R.id.edtkickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtpunchPower);
        edtPunchSpeed = findViewById(R.id.edtKickSpeed);

        btnsave.setOnClickListener(SignUp.this);


    }

    @Override
    public void onClick(View v) {
        try {

            final ParseObject KickBoxer = new ParseObject("KickBoxer");
            KickBoxer.put("name", edtKickBoxerName.getText().toString());
            KickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            KickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            KickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            KickBoxer.put("KickPower", Integer.parseInt(edtKickPower.getText().toString()));
            KickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                      //  Toast.makeText(SignUp.this,"name",Toast.LENGTH_LONG).show();

                      //  FancyToast.makeText(SignUp.this,edtkickBoxerName.getId())
                 //        FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true);

                     FancyToast.makeText(SignUp.this, KickBoxer.get("name") + "is saved to server",
                                FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                     }else {
                        FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception e){

            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG).show();

        }

    }

//   public void  helloWorldTapped(View view){
////       ParseObject boxer = new ParseObject("Boxer");
////       boxer.put("punch_speed",200);
////       boxer.saveInBackground(new SaveCallback() {
////           @Override
////           public void done(ParseException e) {
////               if (e == null){
////                   Toast.makeText(SignUp.this,"Boxer saved successfully",Toast.LENGTH_LONG).show();
////               }
////           }
////       });



   }

