package com.example.sqlnews;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminPanelActivity extends AppCompatActivity {
    RecyclerView recyclerNews;
    Button btnGoAddNews;
    com.example.sqlnews.DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        recyclerNews = findViewById(R.id.recNews);
        btnGoAddNews = findViewById(R.id.btnGoAddNews);
        dataBaseHelper = new com.example.sqlnews.DataBaseHelper(this);

        InitRecyclerNews();

        btnGoAddNews.setOnClickListener(view -> {
            Intent intent = new Intent(AdminPanelActivity.this, com.example.sqlnews.AddNewNewsActivity.class);
            startActivity(intent);
        });
    }

    private void InitRecyclerNews() {
        List<News> newsList = new ArrayList<News>();
        Cursor newsCursor = dataBaseHelper.getAllNews();
        if(newsCursor.getCount() == 0) return;
        newsCursor.moveToFirst();
        for(int i = 0; i < newsCursor.getCount(); i++)
        {
            News news = new News(newsCursor.getInt(0), newsCursor.getString(1), newsCursor.getString(2), newsCursor.getString(3));
            newsList.add(news);
            newsCursor.moveToNext();
        }
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        com.example.sqlnews.NewsAdapter adapter = new com.example.sqlnews.NewsAdapter(newsList, this);
        recyclerNews.setLayoutManager(llm);
        recyclerNews.setAdapter(adapter);
    }
}