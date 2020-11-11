package com.example.booksapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.booksapplication.Adapters.BooksAdapter;
import com.example.booksapplication.Models.BookModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String bookNameText;
    int bookId;
    BookModel clickedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView image = (ImageView)findViewById(R.id.imageView);
        final DatabaseHandler db = new DatabaseHandler(this);

        ListView listView = (ListView)findViewById(R.id.listView);

        BooksAdapter adapter = new BooksAdapter(this, db.getAllBooks());
        Listener listener = new Listener(db.getAllBooks());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        db.addBook(new BookModel(R.drawable.image1, "Метеор", "350", "Трилър", "false"));
        db.addBook(new BookModel(R.drawable.image2, "Шестото клеймо", "350", "Трилър", "false"));
        db.addBook(new BookModel(R.drawable.image3, "Изгубеният символ", "350", "Трилър", "false"));
        db.addBook(new BookModel(R.drawable.image4, "Изгубеният символ", "350", "Трилър", "false"));
        db.addBook(new BookModel(R.drawable.image5, "Шифърът на Леонардо", "350", "Криминале", "false"));
        db.addBook(new BookModel(R.drawable.image6, "Ад", "350", "Криминале", "false"));
        db.addBook(new BookModel(R.drawable.image7, "Произход", "350", "Криминале", "false"));
        db.addBook(new BookModel(R.drawable.image8, "Котка сред гълъби", "350", "Фантастика", "false"));
        db.addBook(new BookModel(R.drawable.image9, "Пътник за Франкфурт", "350", "Фантастика", "false"));
        db.addBook(new BookModel(R.drawable.image10, "Убийство в Ориент Експрес", "350", "Фантастика", "false"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @SuppressLint("ShowToast")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
                Intent profileActivity = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileActivity);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Genres", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Favorite Books", Toast.LENGTH_SHORT).show();
                Intent favoritesActivity = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(favoritesActivity);
                return true;
            case R.id.subitem0:
                Intent mainActiity = new Intent(MainActivity.this, MainActivity.class);
                startActivity(mainActiity);
                Toast.makeText(this, "All books", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Intent thrillersActivity = new Intent(MainActivity.this, ThrillersActivity.class);
                thrillersActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(thrillersActivity);
                Toast.makeText(this, "Thrillers selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Intent criminalActivity = new Intent(MainActivity.this, CriminalActivity.class);
                startActivity(criminalActivity);
                Toast.makeText(this, "Criminal selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Intent fantasyActivity = new Intent(MainActivity.this, FantasyActivity.class);
                startActivity(fantasyActivity);
                Toast.makeText(this, "Fantasy selected", Toast.LENGTH_SHORT).show();
                return true;

            default:    return super.onOptionsItemSelected(item);
        }

    }

    public class Listener implements AdapterView.OnItemClickListener {

        List<BookModel> booksList;

        public Listener(List<BookModel> books) {
            booksList = books;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bookNameText = booksList.get(position).get_bookName();
            bookId = booksList.get(position).get_id();
            Intent intent = new Intent(MainActivity.this, ReadActivity.class);
            intent.putExtra("BookName", bookNameText);
            intent.putExtra("Id", bookId);
            startActivity(intent);
        }
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
