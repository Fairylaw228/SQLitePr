package com.example.sqlnews;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckNewsActivity extends AppCompatActivity {
    private int IdNews;
    TextView txtHeader, txtContent;
    com.example.sqlnews.DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_news);
        Bundle bundle = getIntent().getExtras();
        IdNews = bundle.getInt("IdNews");
        dataBaseHelper = new com.example.sqlnews.DataBaseHelper(this);
        txtHeader = findViewById(R.id.txtHeader);
        txtContent = findViewById(R.id.txtContent);
        setNews();
    }

    private void setNews() {
        Cursor news = dataBaseHelper.getNewsById(IdNews);
        news.moveToFirst();
        txtHeader.setText(news.getString(1));
        txtContent.setText(news.getString(2));
    }
}