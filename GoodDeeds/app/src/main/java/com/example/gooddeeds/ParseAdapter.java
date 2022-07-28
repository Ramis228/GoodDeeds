package com.example.gooddeeds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.gooddeeds.EventActivity.MAIN;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public static String image, title, detailUrl;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }

    View v;
//    TextView test;



    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_education_parse_item, parent, false);
        this.v = view;
//        participate = v.findViewById(R.id.participateee);
////        test = v.findViewById(R.id.testtest);
//        participate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                participate.setText("asasasasas");
//            }
//        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.titleEdEvent);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getBindingAdapterPosition();
            ParseItem parseItem = parseItems.get(position);

//            MAIN.navController.navigate((R.id.action_educationEventFragment_to_detailEducationEventFragment));

            image = parseItem.getImgUrl();
            title = parseItem.getTitle();
            detailUrl = parseItem.getDetailUrl();


            MAIN.navController.navigate(R.id.action_educationEventFragment_to_detailEducationEventFragment);
        }
    }


}
