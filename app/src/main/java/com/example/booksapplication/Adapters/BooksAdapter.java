package com.example.booksapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.sip.SipSession;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksapplication.Activities.MainActivity;
import com.example.booksapplication.Models.BookModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.booksapplication.R.layout.*;

public class BooksAdapter extends ArrayAdapter<String> {

    private Context context;
    private BookModel bookModel = new BookModel();
    int[] IMAGES;
    String[] NAMES;
    DatabaseHandler db;
    public List<BookModel> booksList;

    public BooksAdapter(Context context, List<BookModel> booksList){
        super(context, books_list_view);
        this.context = context;
        this.booksList = booksList;
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public long getItemId(int position) {
        return booksList.get(position).get_id();
    }

    @NotNull
    @Override
    public View getView(int position, View view, @NotNull ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(books_list_view, viewGroup, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView_name = (TextView)view.findViewById(R.id.textView);
        TextView textView_pages = (TextView)view.findViewById(R.id.textView2);
        TextView textView_genre = (TextView)view.findViewById(R.id.textView3);

        BookModel book = booksList.get(position);

        imageView.setImageResource(book.get_image());
        textView_name.setText(book.get_bookName());
        textView_pages.setText(book.get_pagesCount());
        textView_genre.setText(book.get_genre());
        return view;
    }

    private Bitmap decodeByteArray(byte [] byteArray){
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }
}


