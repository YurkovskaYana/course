package com.example.core.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.core.R;
import com.example.core.data.User;
import com.example.core.data.database.UserDao;
import com.example.core.data.database.UserDatabase;

public class AuthorizationActivity extends AppCompatActivity {
    EditText auEmail, auPassword;
    Button auSignIn, auSignUp;
    UserDao db;
    UserDatabase dataBase;

    private boolean validEmail(){
        return !TextUtils.isEmpty(auEmail.getText()) && Patterns.EMAIL_ADDRESS.matcher(auEmail.getText()).matches();
    }

    private boolean validPassword(){
        return !TextUtils.isEmpty(auPassword.getText());
    }

    public void showMessage(@StringRes int str){
        Toast.makeText(AuthorizationActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        setTitle("Sign in");

        auEmail = findViewById(R.id.au_email);
        auPassword = findViewById(R.id.au_password);
        auSignIn = findViewById(R.id.au_sign_in);

        auSignUp = findViewById(R.id.au_sign_up);

        dataBase = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build();

        db = dataBase.getUserDao();


        auSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthorizationActivity.this, RegistrationActivity.class));
            }
        });

        auSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = auEmail.getText().toString().trim();
                String password = auPassword.getText().toString().trim();

                User user = db.getUser(email, password);
                if (user != null && validEmail() && validPassword()) {
                    Intent i = new Intent(AuthorizationActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(!validEmail()){
                    showMessage(R.string.reg_email_input_error);
                }
                else if(!validPassword()){
                    showMessage(R.string.reg_password_input_error);
                }
                else{
                    showMessage(R.string.reg_unknown_user);
                }
            }
        });

    }

}
