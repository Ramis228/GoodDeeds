package com.example.gooddeeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    //    TextView username;
//    Button changeProfile;
    View v;
    //    FirebaseUser firebaseUser;
    public static Button educationEventButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
//        username.setText(firebaseUser.getEmail().toString());
//        changeProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        educationEventButton = v.findViewById(R.id.button3);
//        if (){
//            educationEventButton.setVisibility(View.VISIBLE);
//        }
    }
    //    public void init(){
//        username = v.findViewById(R.id.textView8);
//        changeProfile = v.findViewById(R.id.button2);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//    }

}