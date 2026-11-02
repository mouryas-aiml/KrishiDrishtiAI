package com.example.plantai;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Settings extends AppCompatActivity {

    private static final String SHARED = "Settings";
    private static final String SHARED_APP_LANG = "app_lang";
    private String selected_lang;

    private TextView title, info, delTitle, delInfo;
    private ImageView btnBack;

    private AlertDialog.Builder builder;
    private String ok, cancel, titleAlert, msgAlert;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        title = findViewById(R.id.setting_head);
        info = findViewById(R.id.setting_info);

        delTitle = findViewById(R.id.delreport_head);
        delInfo = findViewById(R.id.delreport_info);

        btnBack = findViewById(R.id.btnBackSettings);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        selected_lang = load_setting();
        setLang();

        myDB = new DatabaseHelper(this);
        builder = new AlertDialog.Builder(this);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    public void clickLangSetting(View view) {
        Intent intent = new Intent(this, SettingSetLanguage.class);
        startActivity(intent);
    }

    public void clickModelInfo(View view) {
        Intent intent = new Intent(this, ModelInfoActivity.class);
        startActivity(intent);
    }

    public void clickFarmProfile(View view) {
        Intent intent = new Intent(this, FarmProfileActivity.class);
        startActivity(intent);
    }

    public void clickLogout(View view) {
        AuthManager.logout(this);
        Toast.makeText(this, "Logged out successfully.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }

    public void clickDelReportSetting(View view) {
        builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDB.deleteAllData();
                File dir = new File(Environment.getExternalStorageDirectory()
                        + "/Android/KrishiDrishti/AllImages");
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    if (children != null) {
                        for (String child : children) {
                            new File(dir, child).delete();
                        }
                    }
                }
                Toast.makeText(Settings.this, "Scan History Cleared!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setTitle(titleAlert);
        dialog.setMessage(msgAlert);
        dialog.show();
    }

    private void setLang() {
        if ("en".equals(selected_lang)) info.setText(getString(R.string.english));
        else if ("kn".equals(selected_lang)) info.setText(getString(R.string.kannada));
        else if ("te".equals(selected_lang)) info.setText(getString(R.string.telugu));
        else if ("ta".equals(selected_lang)) info.setText(getString(R.string.tamil));
        else if ("hi".equals(selected_lang)) info.setText(getString(R.string.hindi));
        else info.setText(getString(R.string.marathi));

        title.setText(getString(R.string.select_language));
        delTitle.setText(getString(R.string.menu_history));
        delInfo.setText(getString(R.string.del_history));

        ok = getString(R.string.ok);
        cancel = getString(R.string.cancel);
        titleAlert = getString(R.string.menu_history);
        msgAlert = getString(R.string.del_history);
    }

    String load_setting() {
        SharedPreferences mySharedPreference = getSharedPreferences(SHARED, MODE_PRIVATE);
        return mySharedPreference.getString(SHARED_APP_LANG, "en");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        if (item.getItemId() == R.id.op_all_diseases) {
            intent = new Intent(this, AllDiseases.class);
        } else if (item.getItemId() == R.id.op_reports) {
            intent = new Intent(this, Reports.class);
        } else if (item.getItemId() == R.id.op_setting) {
            intent = new Intent(this, Settings.class);
        } else if (item.getItemId() == R.id.op_about) {
            intent = new Intent(this, About.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
