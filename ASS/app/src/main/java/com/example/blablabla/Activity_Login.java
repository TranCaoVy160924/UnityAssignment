package com.example.blablabla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blablabla.database.MyDbContext;

public class Activity_Login extends AppCompatActivity {
    private EditText usernameTxt;
    private EditText passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyDbContext myDbContext = new MyDbContext(this.getApplicationContext());

        Button loginButton = findViewById(R.id.login_button);
        usernameTxt = findViewById(R.id.input_username);
        passwordTxt = findViewById(R.id.input_password);

        loginButton.setOnClickListener(this::onLoginClick);
    }

    private void onLoginClick(View view) {
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        if (username.equals(password)) {
            showToast("Login Success");
        } else {
            showToast("Username and password not match");
        }

        openMainAct(username);
    }

    private void showToast(String text) {
        Toast.makeText(Activity_Login.this, text, Toast.LENGTH_SHORT).show();
    }

    private void openMainAct(String username) {
        Intent intentForMainActiviy = new Intent(Activity_Login.this, MainActivity.class);
        intentForMainActiviy.putExtra("USERNAME", username);
        startActivity(intentForMainActiviy);
        startActivityForResult(intentForMainActiviy, 1);
    }
}