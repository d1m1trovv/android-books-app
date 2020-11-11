package com.example.booksapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksapplication.Models.UserModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView name = (TextView)findViewById(R.id.userNameText);
        final Button favBtn = (Button)findViewById(R.id.favoriteButton);
        final TextView userLogged = (TextView)findViewById(R.id.editText);
        final DatabaseHandler db = new DatabaseHandler(this);

        //final TextView logged = (TextView)findViewById(R.id.loggedAsText);

        for(UserModel uModel : db.getAllUsers()){
            if(uModel.getIsLogged().equals("true")){
                name.setText(uModel.get_name());
                Typeface secFont = Typeface.createFromAsset(getAssets(),  "fonts/thirdFont.ttf");

                name.setTypeface(secFont);
            }
        }



        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
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
                Intent profileActivity = new Intent(ProfileActivity.this, ProfileActivity.class);
                profileActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(profileActivity);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Genres", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Favorite Books", Toast.LENGTH_SHORT).show();
                Intent favoritesActivity = new Intent(ProfileActivity.this, FavoritesActivity.class);
                startActivity(favoritesActivity);
                return true;
            case R.id.subitem0:
                Intent mainActiity = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(mainActiity);
                Toast.makeText(this, "All books", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Intent thrillersActivity = new Intent(ProfileActivity.this, ThrillersActivity.class);
                thrillersActivity.putExtra("User", getIntent().getStringExtra("Username"));
                startActivity(thrillersActivity);
                Toast.makeText(this, "Thrillers selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Intent criminalActivity = new Intent(ProfileActivity.this, CriminalActivity.class);
                startActivity(criminalActivity);
                Toast.makeText(this, "Criminal selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem3:
                Intent fantasyActivity = new Intent(ProfileActivity.this, FantasyActivity.class);
                startActivity(fantasyActivity);
                Toast.makeText(this, "Fantasy selected", Toast.LENGTH_SHORT).show();
                return true;


            default:    return super.onOptionsItemSelected(item);
        }
    }

}
