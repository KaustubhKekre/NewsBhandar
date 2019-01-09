package com.example.falcon.newsbhandar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.viewHolder> {
    List<Article> articles;

    public newsAdapter(List<Article> articles) {
        this.articles=articles;

    }

    @NonNull
    @Override
    public newsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.news_template,viewGroup,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final newsAdapter.viewHolder viewHolder, int i) {

        viewHolder.newsTitle.setText(articles.get(i).getTitle());
        Glide.with(viewHolder.newsImage.getContext()).load(articles.get(i).getUrlToImage()).into(viewHolder.newsImage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),detailedNews.class);

                intent.putExtra("imgUrl",articles.get(viewHolder.getAdapterPosition()).getUrlToImage());
                intent.putExtra("title",articles.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("description",articles.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("hyperlink",articles.get(viewHolder.getAdapterPosition()).getUrl());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
        CardView tempCard;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.newsImage);
            newsTitle =itemView.findViewById(R.id.newsTitle);
            tempCard=itemView.findViewById(R.id.tempCard);
        }
    }

}
