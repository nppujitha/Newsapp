package com.example.akipuja.Inclass7;
/*
  Naga Poorna Pujitha,
          Akshay Karai
          Group34
*/

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements GetNewsTask.INewsArticle {

    ArrayList<NewsArticle> newsArticles=new ArrayList<>();
    ListView newsListView;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.akipuja.Inclass7.R.layout.activity_news);

        newsListView = (ListView)findViewById(com.example.akipuja.Inclass7.R.id.newsListView);

        if(getIntent()!= null && getIntent().getExtras()!=null){
            if(getIntent().getExtras().containsKey("category")){
                String sCategory=getIntent().getExtras().getString("category");
                setTitle(sCategory);
                if(isConnected()) {
                    new GetNewsTask(NewsActivity.this, NewsActivity.this).execute("https://newsapi.org/v2/top-headlines?country=us&apiKey=bbee98d8505449b6ae2f0f5e4bdb22b3&category=" + sCategory);
                }else{
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        }

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),DetailActivity.class);
                NewsArticle na = new NewsArticle();
                Log.d("demo1", "onItemClick: "+newsArticles.get(i).getTitle());
                na.title = newsArticles.get(i).getTitle();
                na.publishedAt = newsArticles.get(i).getPublishedAt();
                na.description = newsArticles.get(i).getDescription();
                na.urlToImage = newsArticles.get(i).getUrlToImage();
                intent.putExtra("detailedNews",na);
                startActivity(intent);
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    @Override
    public void handleNewsArticle(ArrayList<NewsArticle> s) {
        if(s!=null&&!s.isEmpty()) {
            newsArticles = s;
            adapter = new NewsAdapter(this, com.example.akipuja.Inclass7.R.layout.news_listview, newsArticles);
            newsListView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No News", Toast.LENGTH_SHORT).show();
        }
    }
}
