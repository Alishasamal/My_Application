package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context context;
    private ArrayList<Article> articles = new ArrayList<>();

    public NewsAdapter(Context context,ArrayList<Article> articles){
        this.articles = articles;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_card_view,parent,false);
        NewsHolder holder = new NewsHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        final Article currentArticle = articles.get(position);


        holder.news_title.setText(currentArticle.title);
        holder.news_content.setText(currentArticle.content);
        holder.news_description.setText(currentArticle.description);
        holder.news_published.setText(currentArticle.publishedAt);
        Glide.with(context).load(currentArticle.urlToImage).into(holder.news_image);


    }

    @Override
    public int getItemCount() {

        return articles.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        private ImageView news_image;
        private TextView news_title;
        private TextView news_content;
        private TextView news_description;
        private TextView news_published;

        public NewsHolder(@NonNull View itemView) {

            super(itemView);

            news_image =(ImageView)itemView.findViewById(R.id.iv_image);
            news_title =(TextView)itemView.findViewById(R.id.tv_title);
            news_content =(TextView)itemView.findViewById(R.id.tv_content);
            news_description =(TextView)itemView.findViewById(R.id.tv_desc);
            news_published =(TextView)itemView.findViewById(R.id.tv_publish);
        }
    }
}
