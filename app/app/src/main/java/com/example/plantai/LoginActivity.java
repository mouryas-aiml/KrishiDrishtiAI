package com.example.plantai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etLoginPhone, etLoginPassword;
    private Button btnLogin;
    private TextView tvForgotPassword, tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AuthManager.isLoggedIn(this)) {
            startActivity(new Intent(this, Home.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        etLoginPhone = findViewById(R.id.etLoginPhone);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        btnLogin.setOnClickListener(v -> {
            String phone = etLoginPhone.getText().toString().trim();
            String password = etLoginPassword.getText().toString().trim();

            if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Please enter mobile number and password.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (AuthManager.loginUser(LoginActivity.this, phone, password)) {
                Toast.makeText(LoginActivity.this, "Welcome to Krishi Drishti!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, Home.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials. Try 9876543210 / 123456 or Register.", Toast.LENGTH_LONG).show();
            }
        });

        tvRegisterLink.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });
    }
}
