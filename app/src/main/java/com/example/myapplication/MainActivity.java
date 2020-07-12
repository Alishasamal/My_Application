package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    ArrayList<Article> articles;
    RecyclerView.LayoutManager layoutManager;
    private Button mBtnViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rc_view);

        articles = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);

        mBtnViewNews = findViewById(R.id.btn_getNews);
    }



    //?sources=google-news&apiKey=4c82d7e8131841f484c6cf169bb83ae4
    public void onGetNewsClicked(View view) {
        APInterface apiInterface = APIClient.getClient().create(APInterface.class);

        Call<String> getNews = apiInterface.getNews("google-news", "4c82d7e8131841f484c6cf169bb83ae4");
        getNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseValue = response.body();
                try {
                    JSONObject responseObject = new JSONObject(responseValue);
                    JSONArray articlesArray = responseObject.getJSONArray("articles");

                    ArrayList<Article> articles = new ArrayList<>();
                    for (int i = 0; i < articlesArray.length(); i++) {
                        Article newArticle = Article.parseJSONObject(articlesArray.optJSONObject(i));
                        articles.add(newArticle);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Here You go", Toast.LENGTH_SHORT).show();

                newsAdapter = new NewsAdapter(MainActivity.this,articles);


                recyclerView.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Connect", Toast.LENGTH_SHORT).show();

            }
        });

    }

}