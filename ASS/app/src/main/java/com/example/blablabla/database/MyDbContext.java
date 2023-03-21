package com.example.blablabla.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDbContext extends SQLiteOpenHelper {
    public MyDbContext(@Nullable Context context) {
        super(context, "BookManagement", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table
        String sql = "CREATE TABLE Books(" +
                "Id INTEGER primary key AUTOINCREMENT not null ," +
                "Name TEXT, " +
                "Description TEXT, " +
                "Image TEXT, " +
                "NumberOfPage INTEGER, " +
                "Author TEXT)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha1', 'book1', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667963379/cracuv280w1w6qtb4hfd.jpg', 1, 'haha')");
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha2', 'book2', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667961949/phvwsqtcvzzt2yfojqwu.jpg', 2, 'haha')");
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha3', 'book3', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667882415/kpz4e8ocp0gndup4wy0h.jpg', 3, 'haha')");
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha4', 'book4', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667882399/g6fomxsuf0ylf9g8wa63.jpg', 4, 'haha')");
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha5', 'book5', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667881405/vufgkabzlvrozyqbjid5.jpg', 5, 'haha')");
        sqLiteDatabase.execSQL("INSERT INTO BOOKS (Name, Description, Image, NumberOfPage, Author) " +
                "values ('haha6', 'book6', 'https://res.cloudinary.com/dddvmxs3h/image/upload/v1667881231/wwkpd0nwhdi4zvaum6oi.jpg', 6, 'haha')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BOOKS");
    }
}
