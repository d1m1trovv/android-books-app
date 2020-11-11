package com.example.booksapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.booksapplication.Models.UserModel;
import com.example.booksapplication.R;
import com.example.booksapplication.Services.DatabaseHandler;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public UserModel loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button loginBtn = findViewById(R.id.button);
        final TextView loginText = findViewById(R.id.loginText);
        final TextView userName = findViewById(R.id.editText);
        final TextView password = findViewById(R.id.editText2);
        final DatabaseHandler db = new DatabaseHandler(this);


        Typeface primFont = Typeface.createFromAsset(getAssets(),  "fonts/primaryFont.ttf");

        loginText.setTypeface(primFont);

        Log.d("Chetene: ", "Chetene na kontaktite..");



        Log.d("Whod na danni: ", "Whod..");
        db.addUserName(new UserModel("TestUser","password"));

        List<UserModel> users = db.getAllUsers();

        for (UserModel un : users) {
            String log = "Id: " + un.get_id() + " ,Name: " + un.get_name() + " ,password: " +
                    un.get_password();

            Log.d("Name: ", log);

        }



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkUser =  db.getUser(userName.getText().toString(), password.getText().toString());
                if(checkUser) {
                    db.isUserLogged(userName.getText().toString(), "true");
                    Intent mainActivity = new Intent(LoginActivity.this,MainActivity.class);
                    mainActivity.putExtra("Username", userName.getText().toString());
                    startActivity(mainActivity);
                }
            }
        });

    }
}
