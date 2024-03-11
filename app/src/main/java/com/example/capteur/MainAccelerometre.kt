package com.example.capteur

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.sqrt


class MainAccelerometre : AppCompatActivity(), SensorEventListener {

    private lateinit var backgroundLayout: ConstraintLayout
    private var mSensorManager: SensorManager? = null
    private lateinit var mSensor: Sensor
    private lateinit var boutonRetourAccelerometre: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometre)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        backgroundLayout = findViewById(R.id.backgroundLayout)
        boutonRetourAccelerometre = findViewById(R.id.boutonRetourAccelerometre)

        if (mSensorManager?.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {
            mSensor = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!!;
        }else{
            Toast.makeText(this, "Il n'y a pas d'accelerometre",Toast.LENGTH_SHORT).show()
        }

        boutonRetourAccelerometre.setOnClickListener{
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
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        // Calcul de la magnitude de l'accélération
        val acceleration = sqrt((x * x + y * y + z * z).toDouble())

        val color: Int
        color = if (acceleration < 3) {
            Color.GREEN
        } else if (acceleration < 8) {
            Color.BLACK
        } else {
            Color.YELLOW
        }

        window.decorView.setBackgroundColor(color)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}



