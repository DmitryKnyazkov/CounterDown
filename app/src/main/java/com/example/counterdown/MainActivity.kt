package com.example.counterdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {
    var counter = 10
    var thraedForCounter = ThraedForCounter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val textCounter: TextView = findViewById(R.id.couter)
        val progressBar: ProgressBar = findViewById(R.id.progress_Bar)
        val button: Button = findViewById(R.id.button)
        val slider: Slider = findViewById(R.id.slider)




        fun setCounter(secund: Int) {
            thraedForCounter.counter = secund
            thraedForCounter.counterr = secund

            textCounter.setText(thraedForCounter.counter.toString())
        }

        slider.addOnChangeListener { slider, value, fromUser -> setCounter(value.toInt()) }

        button.setOnClickListener {
            if (button.text == "СТАРТ") {
                thraedForCounter.startCoroutine(textCounter, slider, button, progressBar, true)

                slider.isEnabled = false
                button.setText("СТОП")

            }
            else {
                thraedForCounter.startCoroutine(textCounter, slider,  button, progressBar, false)
                button.setText("СТАРТ")
                slider.isEnabled = true
//                scope.cancel()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("counter", counter)

    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        counter = savedInstanceState?.getInt("counter")?:0
    }
}