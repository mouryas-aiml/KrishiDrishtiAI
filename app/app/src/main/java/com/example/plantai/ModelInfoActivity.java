package com.example.plantai;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModelInfoActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvShaHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_info);

        btnBack = findViewById(R.id.btnBackModelInfo);
        tvShaHash = findViewById(R.id.tvShaHash);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (tvShaHash != null) {
            tvShaHash.setText("FCEC1F25F20D26C376E9516247443800439E14B8D1591320657D51311DE12FFC");
        }
    }
}
