package com.example.gooddeeds;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class MainFragment extends Fragment {
    View v;
    public static DatabaseReference firebaseDatabase;
    FirebaseAuth mAuth;
    List<Book> listBook;
    EditText edReligion,edFamily,edNeighbor,edRelative,edNeedy,edEducation,edHealth,edEconomy,edNature,edOther;
    TextView religion,family,neighbor,relative,needy,education,health,economy,nature,other,suraText;
    int count =0;
    Book book;
    FirebaseUser user;
    public static String data="123";
    public static String eventData = "Event";
    String checkCompletedReligion = "No",checkCompletedFamily= "No",checkCompletedNeighbor= "No",checkCompletedRelative= "No",checkCompletedNeedy= "No",checkCompletedEducation= "No",checkCompletedHealth= "No",checkCompletedEconomy= "No",checkCompletedNature= "No",checkCompletedOther= "No";
    Random ran = new Random();

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            MainActivity.nameUser = user.getUid();
        }
        init();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("User").child(MainActivity.nameUser);

        colorBackTextViewAndEditText();
        new MyThread().start();
        suraText.setText(sura.suras[ran.nextInt(10)].toString());

    }

    public void init(){
        religion = v.findViewById(R.id.textReligion);
        family = v.findViewById(R.id.textFamily);
        neighbor = v.findViewById(R.id.textNeighbor);
        relative = v.findViewById(R.id.textRelative);
        needy = v.findViewById(R.id.textNeedy);
        education = v.findViewById(R.id.textEducation);
        health = v.findViewById(R.id.textHealth);
        economy = v.findViewById(R.id.textEconomy);
        nature = v.findViewById(R.id.textNature);
        other = v.findViewById(R.id.textOther);
        suraText = v.findViewById(R.id.textView2);


        edReligion = v.findViewById(R.id.edReligion);
        edFamily = v.findViewById(R.id.edFamily);
        edNeighbor = v.findViewById(R.id.edNeighbor);
        edRelative = v.findViewById(R.id.edRelative);
        edNeedy = v.findViewById(R.id.edNeedy);
        edEducation = v.findViewById(R.id.edEducation);
        edHealth = v.findViewById(R.id.edHealth);
        edEconomy = v.findViewById(R.id.edEconomy);
        edNature = v.findViewById(R.id.edNature);
        edOther = v.findViewById(R.id.edOther);

        listBook = new ArrayList<>();



    }

    public void getFromDB() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listBook.size() > 0) listBook.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    book = ds.getValue(Book.class);
                    listBook.add(book);
                    if (data.equals(book.dataGood)) {
                        edReligion.setText(book.religion.toString());
                        edFamily.setText(book.family.toString());
                        edNeighbor.setText(book.neighbor.toString());
                        edRelative.setText(book.relative.toString());
                        edNeedy.setText(book.needy);
                        edEducation.setText(book.education);
                        edHealth.setText(book.health);
                        edEconomy.setText(book.economy);
                        edNature.setText(book.nature);
                        edOther.setText(book.other);
                        count++;
                        checkCompletedReligion = book.checkCompletedReligion;
                        checkCompletedFamily = book.checkCompletedFamily;
                        checkCompletedNeighbor = book.checkCompletedNeighbor;
                        checkCompletedRelative = book.checkCompletedRelative;
                        checkCompletedNeedy = book.checkCompletedNeedy;
                        checkCompletedEducation = book.checkCompletedEducation;
                        checkCompletedHealth = book.checkCompletedHealth;
                        checkCompletedEconomy = book.checkCompletedEconomy;
                        checkCompletedNature = book.checkCompletedNature;
                        checkCompletedOther = book.checkCompletedOther;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        firebaseDatabase.addValueEventListener(valueEventListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        clearEd();
        getFromDB();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseDatabase =  FirebaseDatabase.getInstance().getReference("User").child(MainActivity.nameUser).child(data);
        book = new Book(edReligion.getText().toString(),edFamily.getText().toString(),edNeighbor.getText().toString(),edRelative.getText().toString(),edNeedy.getText().toString(),edEducation.getText().toString(),edHealth.getText().toString(),edEconomy.getText().toString(),edNature.getText().toString(),edOther.getText().toString(),data,checkCompletedReligion,checkCompletedFamily,checkCompletedNeighbor,checkCompletedRelative,checkCompletedNeedy,checkCompletedEducation,checkCompletedHealth,checkCompletedEconomy,checkCompletedNature,checkCompletedOther,"No");
        firebaseDatabase.setValue(book);
    }

    public void clearEd(){
        edReligion.setText("");
        edFamily.setText("");
        edNeighbor.setText("");
        edRelative.setText("");
        edNeedy.setText("");
        edEducation.setText("");
        edHealth.setText("");
        edEconomy.setText("");
        edNature.setText("");
        edOther.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void colorBackTextViewAndEditText(){
        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedReligion.toString().equals("No")){
                    edReligion.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
                    religion.setBackground(getResources().getDrawable(R.drawable.background_view));
                    checkCompletedReligion="Yes";
                }
                else{
                    edReligion.setBackground(getResources().getDrawable(R.drawable.background_button));
                    religion.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedReligion="No";
                }
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCompletedFamily.equals("No")) {
                    edFamily.setBackground(getResources().getDrawable(R.drawable.background_edred));
                    family.setBackground(getResources().getDrawable(R.drawable.background_viewred));
                    checkCompletedFamily="Yes";
                } else {
                    edFamily.setBackground(getResources().getDrawable(R.drawable.background_button));
                    family.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedFamily="No";
                }
            }
        });

        neighbor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedNeighbor.equals("No")){
                    edNeighbor.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
                    neighbor.setBackground(getResources().getDrawable(R.drawable.background_view));
                    checkCompletedNeighbor="Yes";
                }
                else {
                    edNeighbor.setBackground(getResources().getDrawable(R.drawable.background_button));
                    neighbor.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedNeighbor="No";
                }
            }
        });

        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedRelative.equals("No")){
                    edRelative.setBackground(getResources().getDrawable(R.drawable.background_edred));
                    relative.setBackground(getResources().getDrawable(R.drawable.background_viewred));
                    checkCompletedRelative = "Yes";
                }
                else {
                    edRelative.setBackground(getResources().getDrawable(R.drawable.background_button));
                    relative.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedRelative="No";
                }
            }
        });

        needy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedNeedy.equals("No")){
                    edNeedy.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
                    needy.setBackground(getResources().getDrawable(R.drawable.background_view));
                    checkCompletedNeedy = "Yes";
                }
                else {
                    edNeedy.setBackground(getResources().getDrawable(R.drawable.background_button));
                    needy.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedNeedy = "No";
                }
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedEducation.equals("No")){
                    edEducation.setBackground(getResources().getDrawable(R.drawable.background_edred));
                    education.setBackground(getResources().getDrawable(R.drawable.background_viewred));
                    checkCompletedEducation="Yes";
                }
                else {
                    edEducation.setBackground(getResources().getDrawable(R.drawable.background_button));
                    education.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedEducation="No";
                }
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedHealth.equals("No")){
                    edHealth.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
                    health.setBackground(getResources().getDrawable(R.drawable.background_view));
                    checkCompletedHealth="Yes";
                }
                else {
                    edHealth.setBackground(getResources().getDrawable(R.drawable.background_button));
                    health.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedHealth="No";
                }
            }
        });

        economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedEconomy.equals("No")){
                    edEconomy.setBackground(getResources().getDrawable(R.drawable.background_edred));
                    economy.setBackground(getResources().getDrawable(R.drawable.background_viewred));
                    checkCompletedEconomy="Yes";
                }
                else {
                    edEconomy.setBackground(getResources().getDrawable(R.drawable.background_button));
                    economy.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedEconomy="No";
                }
            }
        });

        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedNature.equals("No")){
                    edNature.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
                    nature.setBackground(getResources().getDrawable(R.drawable.background_view));
                    checkCompletedNature="Yes";
                }
                else {
                    edNature.setBackground(getResources().getDrawable(R.drawable.background_button));
                    nature.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedNature="No";
                }
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkCompletedOther.equals("No")){
                    edOther.setBackground(getResources().getDrawable(R.drawable.background_edred));
                    other.setBackground(getResources().getDrawable(R.drawable.background_viewred));
                    checkCompletedOther="Yes";
                }
                else {
                    edOther.setBackground(getResources().getDrawable(R.drawable.background_button));
                    other.setBackground(getResources().getDrawable(R.drawable.background_button));
                    checkCompletedOther="No";
                }
            }
        });


    }

    public void checkColorBackTextViewAndEditText(){
        if(checkCompletedReligion.equals("Yes")){
            edReligion.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
            religion.setBackground(getResources().getDrawable(R.drawable.background_view));
        }
        else{
            edReligion.setBackground(getResources().getDrawable(R.drawable.background_button));
            religion.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if (checkCompletedFamily.equals("Yes")) {
            edFamily.setBackground(getResources().getDrawable(R.drawable.background_edred));
            family.setBackground(getResources().getDrawable(R.drawable.background_viewred));
        }
        else {
            edFamily.setBackground(getResources().getDrawable(R.drawable.background_button));
            family.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedNeighbor.equals("Yes")){
            edNeighbor.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
            neighbor.setBackground(getResources().getDrawable(R.drawable.background_view));
        }
        else {
            edNeighbor.setBackground(getResources().getDrawable(R.drawable.background_button));
            neighbor.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedRelative.equals("Yes")){
            edRelative.setBackground(getResources().getDrawable(R.drawable.background_edred));
            relative.setBackground(getResources().getDrawable(R.drawable.background_viewred));
        }
        else {
            edRelative.setBackground(getResources().getDrawable(R.drawable.background_button));
            relative.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedNeedy.equals("Yes")){
            edNeedy.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
            needy.setBackground(getResources().getDrawable(R.drawable.background_view));
        }
        else {
            edNeedy.setBackground(getResources().getDrawable(R.drawable.background_button));
            needy.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedEducation.equals("Yes")){
            edEducation.setBackground(getResources().getDrawable(R.drawable.background_edred));
            education.setBackground(getResources().getDrawable(R.drawable.background_viewred));
        }
        else {
            edEducation.setBackground(getResources().getDrawable(R.drawable.background_button));
            education.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedHealth.equals("Yes")){
            edHealth.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
            health.setBackground(getResources().getDrawable(R.drawable.background_view));
        }
        else {
            edHealth.setBackground(getResources().getDrawable(R.drawable.background_button));
            health.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedEconomy.equals("Yes")){
            edEconomy.setBackground(getResources().getDrawable(R.drawable.background_edred));
            economy.setBackground(getResources().getDrawable(R.drawable.background_viewred));
        }
        else {
            edEconomy.setBackground(getResources().getDrawable(R.drawable.background_button));
            economy.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedNature.equals("Yes")){
            edNature.setBackground(getResources().getDrawable(R.drawable.background_edgreen));
            nature.setBackground(getResources().getDrawable(R.drawable.background_view));
        }
        else {
            edNature.setBackground(getResources().getDrawable(R.drawable.background_button));
            nature.setBackground(getResources().getDrawable(R.drawable.background_button));
        }
        if(checkCompletedOther.equals("Yes")){
            edOther.setBackground(getResources().getDrawable(R.drawable.background_edred));
            other.setBackground(getResources().getDrawable(R.drawable.background_viewred));
        }
        else {
            edOther.setBackground(getResources().getDrawable(R.drawable.background_button));
            other.setBackground(getResources().getDrawable(R.drawable.background_button));
        }

    }

    class MyThread extends  Thread{
        @Override
        public void run() {
            while (true){
                // colorBackTextViewAndEditText();
                checkColorBackTextViewAndEditText();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}