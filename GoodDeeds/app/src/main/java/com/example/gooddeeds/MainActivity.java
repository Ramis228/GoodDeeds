package com.example.gooddeeds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu;
    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    public static String nameUser ="1";
    ImageView eventButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);


    }
    public void init(){
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        menu = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();
        eventButton = findViewById(R.id.imageViewEvent);
    }
    //Функция выбора фрагмента
    public void changeFragment() {
        Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.mainFragment);
    }
    //Функция удаление фрагмента
    public void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
    public void changeFragmentCalendar() {
        CalendarFragment calendarFragment = new CalendarFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.navHostFragment, calendarFragment);
        ft.commit();
    }

    //Получение даты
    public void setTime(){
        Calendar calendar = Calendar.getInstance();

        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH+1);
        int year = calendar.get(Calendar.YEAR);

        MainFragment.data = month+":"+date+":"+year;

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        else {
            setTime();
        }
    }

    public void changeEvent(View view) {
        Intent goEvent = new Intent(MainActivity.this, EventActivity.class);
        startActivity(goEvent);
    }
}