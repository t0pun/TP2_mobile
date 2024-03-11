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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainCapteur : AppCompatActivity() {

    private lateinit var sensorListView: ListView
    private lateinit var boutonRetour: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.liste_capteur)


        sensorListView = findViewById(R.id.listCapteur)
        boutonRetour = findViewById(R.id.buttonRetourCapteur)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val sensorNames = mutableListOf<String>()
        val sensorTypes = mutableListOf<String>()

        for (sensor in sensorList) {
            sensorNames.add(sensor.name)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorNames)
        sensorListView.adapter = adapter


        boutonRetour.setOnClickListener{
            finish()
        }

        sensorListView.setOnItemClickListener { parent, view, position, id ->
            //val item = sensorListView.getItemAtPosition(position) as String
            //Toast.makeText(this, "Élément cliqué : $sensorTypes", Toast.LENGTH_SHORT).show()
            val selectedSensor = sensorList[position]
            displaySensorDetails(selectedSensor)

        }

    }

    private fun displaySensorDetails(sensor: Sensor) {
        val sensorDetails = "Nom : ${sensor.name}\n" +
                "Type : ${sensor.type}\n" +
                "Fabricant : ${sensor.vendor}\n" +
                "Version : ${sensor.version}\n" +
                "Consommation d'énergie : ${sensor.power} mA\n" +
                "Résolution : ${sensor.resolution}\n" +
                "Plage maximale : ${sensor.maximumRange}"

        //Toast.makeText(this, sensorDetails, Toast.LENGTH_LONG).show()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Détails du capteur")
        alertDialogBuilder.setMessage(sensorDetails)

        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}