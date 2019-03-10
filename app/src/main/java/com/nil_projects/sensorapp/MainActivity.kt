package com.nil_projects.sensorapp


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener
{
    var sensor : Sensor? = null
    var sensorManager : SensorManager? = null
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    lateinit var mp : MediaPlayer
    var isRunning = false


    override fun onSensorChanged(event: SensorEvent?) {
        try {

            if (event!!.values[0] < 30 && isRunning == false) {
                isRunning = true
    //            val fields = R.raw :: class.java!!.getFields()

  //              val sing = resources.getIdentifier(fields[0].name, "raw", packageName)

 //               var mp = MediaPlayer.create(this, sing)
                display_image.visibility = View.VISIBLE
//                mp.start()


            } else {
                isRunning = false
                display_image.visibility = View.INVISIBLE
      //          mp.reset()
            }

        }
        catch (e : Exception)
        {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display_image.visibility = View.INVISIBLE

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

}
