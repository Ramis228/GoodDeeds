package com.example.gooddeeds;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gooddeeds.databinding.FragmentEducationEventBinding;
import static com.example.gooddeeds.EventActivity.MAIN;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;

//import static com.example.finalproject.MainFragment.checkEducationEvent;
//import static com.example.finalproject.MainFragment.checkHealthEvent;
//import static com.example.finalproject.MainFragment.checkReligionEvent;
//import static com.example.finalproject.MainFragment.checkNatureEvent;

import javax.net.ssl.ManagerFactoryParameters;


public class EducationEventFragment extends Fragment {

    EducationEventFragment educationEventFragment;
    FragmentEducationEventBinding binding;

    View v;

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEducationEventBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MAIN.navController.navigate(R.id.action_educationEventFragment_to_mainEventFragment);
            }
        });

        this.v = view;
        progressBar = v.findViewById(R.id.progressBar);
        recyclerView = v.findViewById(R.id.recycleView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ParseAdapter(parseItems, getContext());
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void, Void, Void>{

        //Отоброжение кружка загрузки
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in));
        }
        //Скртытие кружка загрузки
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        //Парсинг текста и картинок с сайта
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://afisha7.ru/mahachkala";
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.item-post");

                int size = data.size();

                for (int i = 0; i < size; i++)
                {
                    String imgUrl = data.select("div.item-post")
                            .select("img.img-fadein")
                            .eq(i)
                            .attr("data-src");

                    String title = data.select("h2.mt-5")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("div.col-md-7")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    detailUrl = data.select("div.col-md-7")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    parseItems.add(new ParseItem(imgUrl, title, detailUrl));
                    Log.d("items", "img" + imgUrl + ". title: " + title);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}