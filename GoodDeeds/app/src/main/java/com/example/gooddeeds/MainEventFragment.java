package com.example.gooddeeds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import static com.example.gooddeeds.EventActivity.MAIN;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gooddeeds.databinding.FragmentMainEventBinding;


public class MainEventFragment extends Fragment {

    private MainEventFragment mainEventFragment;
    FragmentMainEventBinding binding;

    View v;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        binding.txtEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAIN.navController.navigate(R.id.action_mainEventFragment_to_educationEventFragment);

            }
        });
        binding.btnBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAIN.navController.navigate(R.id.action_mainEventFragment_to_mainActivity);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_main_event, container, false);
        binding = FragmentMainEventBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

}