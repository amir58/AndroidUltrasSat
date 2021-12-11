package com.amirmohammed.androidultrassat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amirmohammed.androidultrassat.models.Post;
import com.amirmohammed.androidultrassat.news.ArticlesItem;
import com.amirmohammed.androidultrassat.news.NewsApis;
import com.amirmohammed.androidultrassat.news.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://newsapi.org/v2/top-headlines?country=eg&category=business&apiKey=fa72aea7f1af46a6a45be8aa23e21b64
// baseUrl => https://newsapi.org/
// endPoint => v2/top-headlines?country=eg&category=business&apiKey=fa72aea7f1af46a6a45be8aa23e21b64
public class MaterialActivity extends AppCompatActivity {
    private static final String TAG = "Posts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        RecyclerView recyclerView = findViewById(R.id.rv_news);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApis newsApis = retrofit.create(NewsApis.class);

        newsApis.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                List<ArticlesItem> articlesItemList = response.body().getArticles();
                NewsAdapter newsAdapter = new NewsAdapter(articlesItemList);

                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
    }

}