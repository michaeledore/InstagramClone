package com.mimyboutique.android.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavSport;
    Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profiles_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavouriteSport);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        final ParseUser parseUser =  ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }
        if (parseUser.get("profileBio") == null){
            edtProfileBio.setText("");
        }
        else{
            edtProfileBio.setText(parseUser.get("profileBio").toString());
        }

        if (parseUser.get("profileFavSport") == null){
            edtProfileFavSport.setText("");
        }else{
            edtProfileFavSport.setText(parseUser.get("profileFavSport").toString());
        }
        if (parseUser.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }else{
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }

        //edtProfileName.setText(parseUser.get("profileName") + "");
       // edtProfileHobbies.setText(parseUser.get("profileHobbies") +"");
       // edtProfileProfession.setText(parseUser.get("profileProfession") + "");
       // edtProfileFavSport.setText(parseUser.get("profileFavSport") +"");
       // edtProfileBio.setText(parseUser.get("profileBio") + "");

        btnUpdateInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            parseUser.put("profileName",edtProfileName.getText().toString());
            parseUser.put("profileBio",edtProfileBio.getText().toString());
            parseUser.put("profileFavSport",edtProfileFavSport.getText().toString());
            parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());
            parseUser.put("profileProfession",edtProfileProfession.getText().toString());


            parseUser.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){
                        FancyToast.makeText(getContext(),"Info Updated",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();

                    }else {
                        FancyToast.makeText(getContext(), e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR, true).show();
                    }
                }
            });
            }
        });
        return view;

    }

}
