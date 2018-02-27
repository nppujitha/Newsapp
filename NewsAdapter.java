package com.example.akipuja.Inclass7;
/*
  Naga Poorna Pujitha,
          Akshay Karai
          Group34
*/
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsArticle>{

    NewsArticle newsArticle;
    TextView title, date;
    ImageView imageView;
    public NewsAdapter(@NonNull Context context, int resource, List<NewsArticle> objects) {
        super(context, resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        newsArticle=getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(com.example.akipuja.Inclass7.R.layout.news_listview, parent, false);

        title = (TextView) convertView.findViewById(com.example.akipuja.Inclass7.R.id.tvTitle);
        date = (TextView) convertView.findViewById(com.example.akipuja.Inclass7.R.id.tvDate);
        imageView = (ImageView) convertView.findViewById(com.example.akipuja.Inclass7.R.id.imageViewD);

        title.setText(newsArticle.title);
        date.setText(newsArticle.publishedAt);
        Picasso.with(convertView.getContext()).load(newsArticle.urlToImage).into(imageView);
        return convertView;

    }

}
