package com.example.plantai;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etForgotPhone, etOtpCode, etNewPassword;
    private Button btnGetOtp, btnResetSubmit;
    private TextView tvBackToLoginForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etForgotPhone = findViewById(R.id.etForgotPhone);
        etOtpCode = findViewById(R.id.etOtpCode);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnGetOtp = findViewById(R.id.btnGetOtp);
        btnResetSubmit = findViewById(R.id.btnResetSubmit);
        tvBackToLoginForgot = findViewById(R.id.tvBackToLoginForgot);

        btnGetOtp.setOnClickListener(v -> {
            String phone = etForgotPhone.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your registered phone number.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "OTP 1234 sent to " + phone, Toast.LENGTH_LONG).show();
                etOtpCode.setText("1234");
            }
        });

        btnResetSubmit.setOnClickListener(v -> {
            String phone = etForgotPhone.getText().toString().trim();
            String otp = etOtpCode.getText().toString().trim();
            String newPass = etNewPassword.getText().toString().trim();

            if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(otp) || TextUtils.isEmpty(newPass)) {
                Toast.makeText(ForgotPasswordActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!"1234".equals(otp)) {
                Toast.makeText(ForgotPasswordActivity.this, "Invalid OTP Code.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (AuthManager.resetPassword(ForgotPasswordActivity.this, phone, newPass)) {
                Toast.makeText(ForgotPasswordActivity.this, "Password Reset Successful! Please Login.", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Failed to reset password.", Toast.LENGTH_SHORT).show();
            }
        });

        tvBackToLoginForgot.setOnClickListener(v -> finish());
    }
}
