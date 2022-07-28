package com.example.gooddeeds;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class CalendarFragment extends Fragment {
    View v;
    CalendarView calendarView;

    //Переход на новый фрагмент при нажатии на дату
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        init();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String curDate = String.valueOf(i);
                String curDate1 = String.valueOf(i1+1);
                String curDate2 = String.valueOf(i2);
                MainFragment.data = curDate1+":"+curDate+":"+curDate2;
                ((MainActivity)getActivity()).changeFragment();
//                getActivity().onBackPressed();


            }
        });

    }
    public  void init(){
        calendarView = v.findViewById(R.id.calendarView);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
    }


}