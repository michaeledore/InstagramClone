package com.mimyboutique.android.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private EditText edtKickBoxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickBoxers;
    private  Button btnTransition;


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
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        btnsave.setOnClickListener(SignUp.this);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("Mq6k2y0Ick", new GetCallback <ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null ){
                            txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

       btnGetAllData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               allKickBoxers = "";

               ParseQuery<ParseObject>queryAll = ParseQuery.getQuery("KickBoxer");
             //  queryAll.whereGreaterThan("punchPower",100);
               queryAll.whereGreaterThanOrEqualTo("punchPower",3000);
               queryAll.setLimit(1);
               queryAll.findInBackground(new FindCallback <ParseObject>() {
                   @Override
                   public void done(List<ParseObject> objects, ParseException e) {
                       if (e == null){
                           if (objects.size() > 0 ){
                               for(ParseObject KickBoxer : objects){
                                   allKickBoxers =  allKickBoxers + KickBoxer.get("name") + "/n";

                               }
                               FancyToast.makeText(SignUp.this, allKickBoxers ,
                                       FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                           }else{
                               FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG).show();

                           }
                       }

                   }
               });
           }
       });
       btnTransition.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =  new Intent(SignUp.this,SignUpLoginActivity.class);
               startActivity(intent);

           }
       });
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

