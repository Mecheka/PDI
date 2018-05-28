package com.pdi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    //SharedPreferences file
    private static final String SPF_URL = "vidsurl";
    private static final String URL = "url";

    private EditText editUrl;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initInstance();
    }

    private void initInstance() {

        editUrl = findViewById(R.id.editURL);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        SharedPreferences urlPreferences = getSharedPreferences(SPF_URL, Context.MODE_PRIVATE);
        editUrl.setText(urlPreferences.getString(URL, ""));

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:

                saveUrl();

                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    private void saveUrl() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String url = editUrl.getText().toString();

                        if (url.isEmpty()) {
                            editUrl.setError("Please enter url");
                            editUrl.requestFocus();
                            return;
                        }

                        SharedPreferences urlPreferences = getSharedPreferences(SPF_URL, Context.MODE_PRIVATE);
                        urlPreferences.edit().putString(URL, url).apply();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
