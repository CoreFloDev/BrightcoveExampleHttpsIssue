package com.example.brightcoveexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TimesBrightcovePlayerActivity.class);
                intent.putExtras(TimesBrightcovePlayerActivity.startingBundle(
                        "5436121856001",
                        "BCpkADawqM1d6QTQTQZNvZeQPJoanIYcUVVRuuypZErRN3_-wE6wBEkRhk0JnCMFbIDR4pNtFoO6cbWqB_IL50zx9ZcSLdfMhcNAv46bQxrMyXybmBxe3BeueHE8n6I2qFRSbna8vguRIdZd",
                        "5859905780001"
                ));
                startActivity(intent);
            }
        });
    }
}
