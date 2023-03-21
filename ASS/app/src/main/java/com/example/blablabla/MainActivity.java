package com.example.blablabla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blablabla.adapter.BookAdapter;
import com.example.blablabla.dao.BookDAO;
import com.example.blablabla.database.MyDbContext;
import com.example.blablabla.model.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Intent intentFromLoginAct;
    BookDAO bookDao;
    ArrayList<Book> data = new ArrayList<>();
    BookAdapter bookAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFromLoginAct = getIntent();
        if (intentFromLoginAct != null) {
            Toast.makeText(this, intentFromLoginAct.getStringExtra("USERNAME"), Toast.LENGTH_LONG).show();
        }

        listView = findViewById(R.id.bookList_ListView);
        bookDao = new BookDAO(MainActivity.this);

        try {
            data = bookDao.readAll();
            bookAdapter = new BookAdapter(MainActivity.this, data);
            listView.setAdapter(bookAdapter);
        } catch (Exception ex) {
            Log.e("Main Activity", "onCreate: " + ex.getMessage());
        }

        Button addBook_Button = findViewById(R.id.addBook_Button);
        addBook_Button.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, Activity_Create_Book.class);
            startActivity(intent);
        });

        Button viewLocation_Button = findViewById(R.id.viewLocation_Button);
        viewLocation_Button.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, Activity_Location.class);
            startActivity(intent);
        });
    }

    protected void onResume() {
        super.onResume();
        data.clear();
        data.addAll(bookDao.readAll());
        bookAdapter.notifyDataSetChanged();
        listView.setAdapter(bookAdapter);
    }
}