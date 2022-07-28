package com.example.gooddeeds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.gooddeeds.ProfileFragment.educationEventButton;

import com.example.gooddeeds.databinding.FragmentDetailEducationEventBinding;
import com.example.gooddeeds.databinding.FragmentEducationEventBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static com.example.gooddeeds.ParseAdapter.image;
import static com.example.gooddeeds.ParseAdapter.detailUrl;
import static com.example.gooddeeds.ParseAdapter.title;
import static com.example.gooddeeds.EventActivity.MAIN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailEducationEventFragment extends Fragment {

    private ImageView imageView;
    private TextView titleTextView, detailTextView;
    private String detailString;
    private ImageView bntBack;
    private Button participate;
    FragmentDetailEducationEventBinding binding;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailEducationEventBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;

        imageView = v.findViewById(R.id.imageView);
        titleTextView = v.findViewById(R.id.titleEdEvent);
        detailTextView = v.findViewById(R.id.detailEdText);
        bntBack = v.findViewById(R.id.btnBack);
        participate = v.findViewById(R.id.participate);

        //Переход назад при нажатии на кнопку
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAIN.navController.navigate(R.id.action_detailEducationEventFragment_to_educationEventFragment);
            }
        });
        //Изменение цвета кнопки и появление текста об участии в событии
        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                participate.setBackgroundColor(getResources().getColor(R.color.grey));
                view.setClickable(false);
                Toast.makeText(getContext(), "Вы приняли участие в событии " + title, Toast.LENGTH_LONG).show();
            }

        });


        titleTextView.setText(title);
        Picasso.get().load(image).into(imageView);


        Content content = new Content();
        content.execute();
    }


    private class Content extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            detailTextView.setText(detailString);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        //Парсинг картинок и текста с сайта
        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String baseUrl = "https://afisha7.ru/mahachkala";
                String detailsUrl = detailUrl;
                String url = "https://afisha7.ru/" + detailsUrl;

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.event-desc");

                detailString = data.select("div.event-desc")
                        .select("p")
                        .text();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}