package com.example.blablabla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blablabla.dao.BookDAO;
import com.example.blablabla.model.Book;
import com.squareup.picasso.Picasso;

public class Activity_Create_Book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        EditText title_EditText = findViewById(R.id.title_EditText);
        Button edit_Button = findViewById(R.id.edit_Button);
        EditText numOfPage_EditText = findViewById(R.id.numOfPage_EditText);
        EditText author_EditText = findViewById(R.id.author_EditText);
        EditText description_EditText = findViewById(R.id.description_EditText);

        BookDAO bookDao = new BookDAO(Activity_Create_Book.this);
        Intent intent = getIntent();

        edit_Button.setOnClickListener((View view) -> {
            try {
                String newName = title_EditText.getText().toString();
                String newAuthor = author_EditText.getText().toString();
                String description = description_EditText.getText().toString();
                int newNumOfPage = Integer.parseInt(numOfPage_EditText.getText().toString());

                Book newBook = new Book(0, newName, description, "haha", 10, newAuthor);

                bookDao.create(newBook);
                finish();
            } catch (Exception ex) {
                Log.e("Book_Edit", "onEdit: " + ex.getMessage());
                Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
            }
        });
    }
}