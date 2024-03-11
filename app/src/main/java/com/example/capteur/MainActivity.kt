package com.example.capteur

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boutonCapteur: Button = findViewById(R.id.boutonCapteur)
        val boutonCapteurAbsent: Button = findViewById(R.id.boutonCapteurAbsent)
        val boutonAccelerometre: Button = findViewById(R.id.boutonAccelerometre)
        val boutonDirection: Button = findViewById(R.id.boutonDirection)

        boutonCapteur.setOnClickListener{

            val intentCapteur = Intent(this, MainCapteur::class.java)
            startActivity(intentCapteur)

        }

        boutonCapteurAbsent.setOnClickListener{

            val intentCapteurAbsent = Intent(this, MainCapteurAbsent::class.java)
            startActivity(intentCapteurAbsent)
        }

        boutonAccelerometre.setOnClickListener{

            val intentAccelerometre = Intent(this, MainAccelerometre::class.java)
            startActivity(intentAccelerometre)

        }

        boutonDirection.setOnClickListener{

            val intentDirection = Intent(this, MainDirection::class.java)
            startActivity(intentDirection)
        }


    }
}



