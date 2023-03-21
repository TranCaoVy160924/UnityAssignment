package com.example.blablabla.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blablabla.database.MyDbContext;
import com.example.blablabla.model.Book;

import java.util.ArrayList;

public class BookDAO {
    MyDbContext dbContext;

    public BookDAO(Context context) {
        dbContext = new MyDbContext(context);
    }

    // select function
    public ArrayList<Book> readAll() {
        SQLiteDatabase db = dbContext.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Books", null);

        return getBookFromCursor(cursor);
    }

    private ArrayList<Book> getBookFromCursor(Cursor cursor) {
        ArrayList<Book> books = new ArrayList<>();
        cursor.moveToFirst();

        try {
            while (!cursor.isAfterLast()) {
                int bookId = cursor.getInt(0);
                String bookName = cursor.getString(1);
                String description = cursor.getString(2);
                String image = cursor.getString(3);
                int numberOfPage = cursor.getInt(4);
                String author = cursor.getString(5);
                books.add(new Book(bookId, bookName, description, image, numberOfPage, author));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return books;
    }

    public boolean create(Book book) {
        SQLiteDatabase db = dbContext.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", book.getId());
        values.put("Name", book.getName());

        long row = db.insert("Books", null, values);

        return row > 0;
    }

    public boolean update(Book patch) {
        SQLiteDatabase db = dbContext.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("Name", patch.getName());

        long row = db.update("Books", values, "Id = ?",
                new String[]{String.valueOf(patch.getId())});

        return row > 0;
    }

    public boolean delete(int id) {
        SQLiteDatabase db = dbContext.getReadableDatabase();
        long row = db.delete("Books", "Id = ?", new String[]{String.valueOf(id)});
        return row > 0;
    }
}
