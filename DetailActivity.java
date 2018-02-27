package com.example.akipuja.Inclass7;
/*
  Naga Poorna Pujitha,
          Akshay Karai
          Group34
*/
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView detailTitle, detailActivityDate, detailActivityDesc;
    ImageView detailActivityImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.akipuja.Inclass7.R.layout.activity_detail);
        setTitle("Detail Activity");

        detailTitle =(TextView) findViewById(com.example.akipuja.Inclass7.R.id.titleTVD);
        detailActivityDate =(TextView) findViewById(com.example.akipuja.Inclass7.R.id.dateTVD);
        detailActivityDesc =(TextView) findViewById(com.example.akipuja.Inclass7.R.id.descTVD);
        detailActivityImage =(ImageView) findViewById(com.example.akipuja.Inclass7.R.id.imageViewD);

        if (getIntent() != null && getIntent().getExtras() != null) {

            if (getIntent().getExtras().containsKey("detailedNews")) {
                NewsArticle presentArticle = (NewsArticle) getIntent().getExtras().getSerializable("detailedNews");
                detailTitle.setText(presentArticle.getTitle());
                detailActivityDate.setText(presentArticle.getPublishedAt());
                detailActivityDesc.setText(presentArticle.getDescription());
                if(isConnected()) {
                    Picasso.with(DetailActivity.this).load(presentArticle.getUrlToImage()).into(detailActivityImage);
                }else{
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
}
