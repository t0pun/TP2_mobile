package com.example.capteur

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainCapteurAbsent : AppCompatActivity() {


    private lateinit var sensorListView: ListView
    private lateinit var buttonAbsent: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_capteur_absent)

        sensorListView = findViewById(R.id.listCapteurAbsent)
        buttonAbsent = findViewById(R.id.buttonAbsent)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val unsupportedSensorNames = mutableListOf<String>()

        val supportedSensorTypes = listOf(
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_LIGHT,
            Sensor.TYPE_LINEAR_ACCELERATION,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_ORIENTATION,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_PROXIMITY,
            Sensor.TYPE_RELATIVE_HUMIDITY,
            Sensor.TYPE_ROTATION_VECTOR,
            Sensor.TYPE_TEMPERATURE
        )

        for (sensorType in supportedSensorTypes) {
            val sensor = sensorManager.getDefaultSensor(sensorType)
            if (sensor == null) {
                unsupportedSensorNames.add(getSensorName(sensorType))
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, unsupportedSensorNames)
        sensorListView.adapter = adapter


        buttonAbsent.setOnClickListener{
            finish()
        }

    }

    private fun getSensorName(sensorType: Int): String {
        return when (sensorType) {
            Sensor.TYPE_ACCELEROMETER -> "Accéléromètre"
            Sensor.TYPE_AMBIENT_TEMPERATURE -> "Capteur temperature ambiante"
            Sensor.TYPE_GRAVITY -> "Gravité"
            Sensor.TYPE_GYROSCOPE -> "Gyroscope"
            Sensor.TYPE_LIGHT -> "Capteur de lumière"
            Sensor.TYPE_LINEAR_ACCELERATION -> "Acceleration linéaire"
            Sensor.TYPE_MAGNETIC_FIELD -> "Champ magnétique"
            Sensor.TYPE_ORIENTATION -> "Orientation"
            Sensor.TYPE_PRESSURE -> "Pression"
            Sensor.TYPE_PROXIMITY -> "Proximité"
            Sensor.TYPE_RELATIVE_HUMIDITY -> "Capteur humidité"
            Sensor.TYPE_ROTATION_VECTOR -> "Vecteur de rotation"
            Sensor.TYPE_TEMPERATURE -> "temperature"

            else -> "Capteur inconnu"
        }

    }


}