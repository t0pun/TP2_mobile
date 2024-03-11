package com.example.capteur

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.sqrt


class MainDirection : AppCompatActivity(), SensorEventListener {

    //private lateinit var sensorManager: SensorManager
    private lateinit var backgroundLayout: ConstraintLayout
    private var mSensorManager: SensorManager? = null
    private lateinit var mSensor: Sensor
    private lateinit var directionText: TextView
    private lateinit var boutonDirection: Button



    private lateinit var flecheHaut: ImageView
    private lateinit var flecheBas: ImageView
    private lateinit var flecheGauche: ImageView
    private lateinit var flecheDroite: ImageView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direction)


        flecheHaut = findViewById(R.id.flecheHaut)
        flecheBas = findViewById(R.id.flecheBas)
        flecheGauche = findViewById(R.id.flecheGauche)
        flecheDroite = findViewById(R.id.flecheDroite)
        boutonDirection = findViewById(R.id.buttonDirection)

        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

        flecheHaut.layoutParams = layoutParams
        flecheBas.layoutParams = layoutParams
        flecheGauche.layoutParams = layoutParams
        flecheDroite.layoutParams = layoutParams


        directionText= findViewById(R.id.directionTextView)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        if (mSensorManager?.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {
            mSensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!!;
        }else {
            Toast.makeText(this, "Il n'y a pas d'accelerometre", Toast.LENGTH_SHORT).show()
        }

        boutonDirection.setOnClickListener{
            finish()
        }


    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(
            this,
            mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
            SensorManager.SENSOR_DELAY_UI
        )
    }


    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this)
    }



    override fun onSensorChanged(event: SensorEvent) {
        // Récupération des valeurs de l'accéléromètre
        val x = event.values[0]
        val y = event.values[1]
        //val z = event.values[2]

        val direction = getDirection(x, y)

        //directionText.text = direction
        updateArrowVisibility(direction)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun getDirection(accelerationX: Float, accelerationY: Float): String {
        val seuilHorizontal = 0.5f
        val seuilVertical = 0.5f
        var direction = "immobile"

        if (Math.abs(accelerationX) > seuilHorizontal) {
            if (accelerationX > 0) direction="Droite" else direction="Gauche"
        } else if (Math.abs(accelerationY) > seuilVertical) {
            if (accelerationY > 0) direction="Haut" else direction="Bas"
        }

        return direction
    }

    private fun updateArrowVisibility(direction: String) {
        // Réinitialiser la visibilité de toutes les flèches
        flecheHaut.visibility = ImageView.INVISIBLE
        flecheBas.visibility = ImageView.INVISIBLE
        flecheGauche.visibility = ImageView.INVISIBLE
        flecheDroite.visibility = ImageView.INVISIBLE

        // Afficher la flèche correspondante à la direction détectée
        when (direction) {
            "Haut" -> flecheHaut.visibility = ImageView.VISIBLE
            "Bas" -> flecheBas.visibility = ImageView.VISIBLE
            "Gauche" -> flecheGauche.visibility = ImageView.VISIBLE
            "Droite" -> flecheDroite.visibility = ImageView.VISIBLE
        }

    }
}
