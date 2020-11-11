package com.example.booksapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.booksapplication.Adapters.BooksAdapter;
import com.example.booksapplication.Models.BookModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class CriminalActivity extends AppCompatActivity {

    public List<BookModel> books = new ArrayList<>();
    String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal);
        DatabaseHandler db = new DatabaseHandler(this);

        ListView listView = (ListView)findViewById(R.id.listView);

        for(BookModel book : db.getAllBooks()){
            if(book.get_genre().equals("Криминале")){
                books.add(book);
            }
        }

        BooksAdapter adapter = new BooksAdapter(this, books);
        listView.setAdapter(adapter);
        Listener listener = new Listener(books);
        listView.setOnItemClickListener(listener);
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
                Intent profileActivity = new Intent(CriminalActivity.this, ProfileActivity.class);
                profileActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(profileActivity);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Genres", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Favorite Books", Toast.LENGTH_SHORT).show();
                Intent favoritesActivity = new Intent(CriminalActivity.this, FavoritesActivity.class);
                startActivity(favoritesActivity);
                return true;
            case R.id.subitem0:
                Intent mainActiity = new Intent(CriminalActivity.this, MainActivity.class);
                startActivity(mainActiity);
                Toast.makeText(this, "All books", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Intent thrillersActivity = new Intent(CriminalActivity.this, ThrillersActivity.class);
                thrillersActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(thrillersActivity);
                Toast.makeText(this, "Thrillers selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Intent criminalActivity = new Intent(CriminalActivity.this, CriminalActivity.class);
                startActivity(criminalActivity);
                Toast.makeText(this, "Criminal selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Intent fantasyActivity = new Intent(CriminalActivity.this, FantasyActivity.class);
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
            bookName = booksList.get(position).get_bookName();
            Intent intent = new Intent(CriminalActivity.this, ReadActivity.class);
            intent.putExtra("BookName", bookName);
            startActivity(intent);
        }
    }

}
