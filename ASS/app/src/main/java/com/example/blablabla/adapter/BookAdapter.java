package com.example.blablabla.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blablabla.Activity_EditBook;
import com.example.blablabla.MainActivity;
import com.example.blablabla.R;
import com.example.blablabla.dao.BookDAO;
import com.example.blablabla.model.Book;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

//public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
//    ArrayList<Book> data;
//    Context context;
//
//    public BookAdapter(ArrayList<Book> data, Context context) {
//        this.data = data;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//        View itemView = layoutInflater.inflate(R.layout.book_item_layout, viewGroup, false);
//        return new BookViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BookViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(data.get(i).getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public class BookViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//
//        public BookViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.bookName_textView);
//        }
//    }
//}

public class BookAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> data;
    BookDAO bookDAO;

    public BookAdapter(Context context, ArrayList<Book> data) {
        this.context = context;
        this.data = data;
        this.bookDAO = new BookDAO(this.context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        BookViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.book_item_layout, null);
        }

        holder = new BookViewHolder(convertView);
        convertView.setTag(holder);

        Book currentBook = data.get(position);
//        Picasso.get().load(currentBook.getImage()).into(holder.bookImage_imageView);
        holder.bookName_textView.setText(currentBook.getName());
        holder.numOfPage_textView.setText(Integer.toString(currentBook.getNumberOfPage()));
        holder.author_textView.setText(currentBook.getAuthor());

        holder.edit_Button.setOnClickListener((View view) -> {
            editBook(view, position);
        });
        holder.delete_Button.setOnClickListener((View view) -> {
            deleteBook(view, position);
        });

        return convertView;
    }

    private void editBook(View view, int position) {
        Intent intent = new Intent(context, Activity_EditBook.class);
        intent.putExtra("book", data.get(position));
        context.startActivity(intent);
    }

    private void deleteBook(View view, int position) {
        Book deletingBook = data.get(position);
        Log.i("Delete_Book", "deleteBook: " + deletingBook.getId());
        data.remove(deletingBook);
        bookDAO.delete(deletingBook.getId());
        this.notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
//        ImageView bookImage_imageView;
        TextView bookName_textView;
        TextView numOfPage_textView;
        TextView author_textView;
        Button edit_Button;
        Button delete_Button;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
//            bookImage_imageView = itemView.findViewById(R.id.bookImage_imageView);
            bookName_textView = itemView.findViewById(R.id.bookName_textView);
            numOfPage_textView = itemView.findViewById(R.id.numOfPage_textView);
            author_textView = itemView.findViewById(R.id.author_textView);
            edit_Button = itemView.findViewById(R.id.edit_Button);
            delete_Button = itemView.findViewById((R.id.delete_Button));
        }
    }
}
