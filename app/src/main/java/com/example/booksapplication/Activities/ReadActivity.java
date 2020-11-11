package com.example.booksapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksapplication.Models.BookModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import org.w3c.dom.Text;

public class ReadActivity extends AppCompatActivity {

    public BookModel isFavoriteBook = new BookModel();
    public String keepBookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Chronometer chrono = findViewById(R.id.chrono);
        WebView webView = findViewById(R.id.webView);
        final Button addFavoriteButton = findViewById(R.id.add);
        final DatabaseHandler db = new DatabaseHandler(this);
        final TextView name = findViewById(R.id.bookNameText);
        final int bookId;

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("BookName"));
        bookId = intent.getIntExtra("Id", 0);

        if(db.getBookById(bookId).isFavorite().equals("true")){
            addFavoriteButton.setText("In favorites");
        }

        switch(name.getText().toString()){

            case "Метеор":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Изгубеният символ":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Шестото Клеймо":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Шифърът на Леонардо":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Ад":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Произход":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Котка сред гълъби":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Пътник за Франкфурт":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            case "Убийство в Ориент Експрес":
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://novel80.com/244424-origin.html");
                break;
            default: break;
        }



        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*for(BookModel book : db.getAllBooks()){
                    if(book.get_id() == bookId) {
                        db.makeBookFavorite(book.get_id(), "true");
                        String log = "isFavorite = " + book.isFavorite();
                        Log.d("isFavorite" , log);
                        addFavoriteButton.setText("In favorites");
                    }
                }*/
                if(addFavoriteButton.getText().toString().equals("Add to favorites")) {
                    db.makeBookFavorite(bookId, "true");
                    String log = "isFavorite = " + db.getBookById(bookId).isFavorite();
                    Log.d("isFavorite", log);
                    addFavoriteButton.setText("In favorites");
                }
                else if(addFavoriteButton.getText().toString().equals("In favorites")){
                    db.makeBookFavorite(bookId, "false");
                    String log = "isFavorite = " + db.getBookById(bookId).isFavorite();
                    Log.d("isFavorite", log);
                    addFavoriteButton.setText("Add to favorites");
                }
            }
        });

        chrono.start();
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
                Intent profileActivity = new Intent(ReadActivity.this, ProfileActivity.class);
                profileActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(profileActivity);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Genres", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Favorite Books", Toast.LENGTH_SHORT).show();
                Intent favoritesActivity = new Intent(ReadActivity.this, FavoritesActivity.class);
                startActivity(favoritesActivity);
                return true;
            case R.id.subitem0:
                Intent mainActiity = new Intent(ReadActivity.this, MainActivity.class);
                startActivity(mainActiity);
                Toast.makeText(this, "All books", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Intent thrillersActivity = new Intent(ReadActivity.this, ThrillersActivity.class);
                thrillersActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(thrillersActivity);
                Toast.makeText(this, "Thrillers selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Intent criminalActivity = new Intent(ReadActivity.this, CriminalActivity.class);
                startActivity(criminalActivity);
                Toast.makeText(this, "Criminal selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Intent fantasyActivity = new Intent(ReadActivity.this, FantasyActivity.class);
                startActivity(fantasyActivity);
                Toast.makeText(this, "Fantasy selected", Toast.LENGTH_SHORT).show();
                return true;

            default:    return super.onOptionsItemSelected(item);
        }
    }

}
