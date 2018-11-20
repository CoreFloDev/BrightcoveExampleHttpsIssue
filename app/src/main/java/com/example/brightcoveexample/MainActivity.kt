package com.example.brightcoveexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button.setOnClickListener {
            val intent = Intent(this, TimesBrightcovePlayerActivity::class.java)
            intent.putExtras(TimesBrightcovePlayerActivity.startingBundle(
                "5436121856001",
                "BCpkADawqM1d6QTQTQZNvZeQPJoanIYcUVVRuuypZErRN3_-wE6wBEkRhk0JnCMFbIDR4pNtFoO6cbWqB_IL50zx9ZcSLdfMhcNAv46bQxrMyXybmBxe3BeueHE8n6I2qFRSbna8vguRIdZd",
                "5859905780001"
            ))
            startActivity(intent)
        }
    }

}
