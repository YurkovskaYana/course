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

public class RegistrationActivity extends AppCompatActivity {
    EditText reUsername, reEmail, rePassword, rePasswordAgain;
    Button reSignUp;
    private UserDao userDao;

    private boolean validEmail(){
        return !TextUtils.isEmpty(reEmail.getText()) && Patterns.EMAIL_ADDRESS.matcher(reEmail.getText()).matches();
    }

    private boolean validPassword(){
        return !TextUtils.isEmpty(rePassword.getText());
    }

    private boolean validPasswordAgain(){
        String password = rePassword.getText().toString();
        String passwordAgain = rePasswordAgain.getText().toString();
        return password.equals(passwordAgain);
    }


    public void showMessage(@StringRes int str){
        Toast.makeText(RegistrationActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Sign up");

        reEmail = findViewById(R.id.re_email);
        rePassword = findViewById(R.id.re_password);
        rePasswordAgain = findViewById(R.id.re_password_again);
        reSignUp = findViewById(R.id.re_sign_up);

        userDao = Room.databaseBuilder(this, UserDatabase.class, "User").allowMainThreadQueries().build().getUserDao();

        reSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reEmail.getText().toString().trim();
                String password = rePassword.getText().toString().trim();

                if (validEmail() && validPassword() && validPasswordAgain()) {
                    User user = new User(password, email);
                    userDao.insert(user);
                    Intent moveToLogin = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
                    startActivity(moveToLogin);
                    showMessage(R.string.reg_congratulation);

                }
                else if(!validEmail()){
                    showMessage(R.string.reg_email_input_error);
                }
                else if(!validPassword()){
                    showMessage(R.string.reg_password_input_error);
                }
                else if(!validPasswordAgain()){
                    showMessage(R.string.reg_password_again_input_error);
                }
            }
        });
    }

}


