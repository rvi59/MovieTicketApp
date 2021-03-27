package com.example.firemoviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firemoviesapp.Models.Results;
import com.example.firemoviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {


    Context context;
    List<Results> resultsList;

    public MoviesAdapter(Context context, List<Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.moviecard_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Results results = resultsList.get(position);

        String imgURL =results.getBackdrop_path();
        Picasso.get().load(imgURL).into(holder.imageView);
        holder.textView.setText(results.getOriginalTitle());
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Movie is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movieImg);
            textView = itemView.findViewById(R.id.movieTitle);
            cardView = itemView.findViewById(R.id.myCard);

        }
    }
}
