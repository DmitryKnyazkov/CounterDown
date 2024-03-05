package com.example.counterdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.slider.Slider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var counter = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textCounter: TextView = findViewById(R.id.couter)
//        val progressBar: ProgressBar = findViewById(R.id.progress_Bar)
        val button: Button = findViewById(R.id.button)
        val slider: Slider = findViewById(R.id.slider)

        var thraedForCounter = ThraedForCounter()

//        fun counting() {
//            var job = scope.launch {
//
//                while (counter > 0) {
//                    delay(1000)
//                    counter--
//                    textCounter.setText(counter.toString())
//                }
//                slider.isEnabled = true
//            }
//        }

        fun setCounter(secund: Int) {
//            counter = secund
            thraedForCounter.counter = secund

            textCounter.setText(thraedForCounter.counter.toString())
        }

        slider.addOnChangeListener { slider, value, fromUser -> setCounter(value.toInt()) }

        button.setOnClickListener {
            if (button.text == "СТАРТ") {
                thraedForCounter.startCoroutine()

                slider.isEnabled = false
                button.setText("СТОП")
//                counting()
            }
            else {
                thraedForCounter.stopCoroutine()
                button.setText("СТАРТ")
                slider.isEnabled = true
//                scope.cancel()
            }
        }

    }
}