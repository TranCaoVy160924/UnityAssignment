package com.example.blablabla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
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

public class Activity_EditBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        EditText bookId_EditText = findViewById(R.id.bookId_EditText);
        EditText title_EditText = findViewById(R.id.title_EditText);
        Button edit_Button = findViewById(R.id.edit_Button);
        EditText numOfPage_EditText = findViewById(R.id.numOfPage_EditText);
        EditText author_EditText = findViewById(R.id.author_EditText);
        EditText description_EditText = findViewById(R.id.description_EditText);

        BookDAO bookDao = new BookDAO(Activity_EditBook.this);
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        bookId_EditText.setText(Integer.toString(book.getId()));
        title_EditText.setText(book.getName());
        numOfPage_EditText.setText(Integer.toString(book.getNumberOfPage()));
        author_EditText.setText(book.getAuthor());
        description_EditText.setText(book.getDescription());

        edit_Button.setOnClickListener((View view) -> {
            try {
                String newName = title_EditText.getText().toString();
                String newAuthor = author_EditText.getText().toString();
                String description = description_EditText.getText().toString();
                int newNumOfPage = Integer.parseInt(numOfPage_EditText.getText().toString());

                book.setName(newName);
                book.setAuthor(newAuthor);
                book.setDescription(description);
                book.setNumberOfPage(newNumOfPage);
                bookDao.update(book);
                NotifyUpdate("Book updated", "A book was updated");

                finish();
            } catch (Exception ex) {
                Log.e("Book_Edit", "onEdit: " + ex.getMessage());
                Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void NotifyUpdate(String title, String message) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelId = getResources().getString(R.string.channel_id);
            String channelName = getResources().getString(R.string.channel_name);
            Context context = Activity_EditBook.this;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId).setSmallIcon(R.drawable.facebook_icon).setContentTitle(title).setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(1, builder.build());
        }
    }
}