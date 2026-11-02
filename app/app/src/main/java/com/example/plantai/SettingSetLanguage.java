package com.example.plantai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class SettingSetLanguage extends AppCompatActivity {

    private String selected_lang = "en";
    private Button save, cancel;
    private RadioGroup radioGroup;
    private TextView textView;
    private ImageView btnBack;
    private static final String SHARED = "Settings";
    private static final String SHARED_APP_LANG = "app_lang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_set_language);

        save = findViewById(R.id.SaveBtn);
        cancel = findViewById(R.id.CancelBtn);
        radioGroup = findViewById(R.id.lang_grp);
        textView = findViewById(R.id.sYL);
        btnBack = findViewById(R.id.btnBackSetLang);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        selected_lang = load_setting();
        setLang();

        if ("en".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_0);
        } else if ("kn".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_k);
        } else if ("te".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_te);
        } else if ("ta".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_ta);
        } else if ("hi".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_1);
        } else if ("mr".equals(selected_lang)) {
            radioGroup.check(R.id.rodio_2);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rodio_0) selected_lang = "en";
            else if (checkedId == R.id.rodio_k) selected_lang = "kn";
            else if (checkedId == R.id.rodio_te) selected_lang = "te";
            else if (checkedId == R.id.rodio_ta) selected_lang = "ta";
            else if (checkedId == R.id.rodio_1) selected_lang = "hi";
            else if (checkedId == R.id.rodio_2) selected_lang = "mr";
        });
    }

    void save_setting(String lang_code) {
        SharedPreferences mySharedPreference = getSharedPreferences(SHARED, MODE_PRIVATE);
        String currentLang = mySharedPreference.getString(SHARED_APP_LANG, "en");

        SharedPreferences.Editor editor = mySharedPreference.edit();
        editor.putString(SHARED_APP_LANG, lang_code);
        editor.apply();

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(lang_code));

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 100);
    }

    public void saveClick(View view) {
        save_setting(selected_lang);
    }

    public void cancelClick(View view) {
        finish();
    }

    void setLang() {
        if (cancel != null) cancel.setText(getString(R.string.cancel));
        if (save != null) save.setText(getString(R.string.save));
        if (textView != null) textView.setText(getString(R.string.select_language));
    }

    String load_setting() {
        SharedPreferences mySharedPreference = getSharedPreferences(SHARED, MODE_PRIVATE);
        return mySharedPreference.getString(SHARED_APP_LANG, "en");
    }
}
