package com.example.counterdown

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.slider.Slider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThraedForCounter(counterr: Int = 10) {
    var counterr = counterr
    var counter = counterr
    val scope = CoroutineScope(Dispatchers.Main)
    var job = scope.launch{}

    fun startCoroutine(
        textView: TextView,
        slider: Slider,
        button: Button,
        progressBar: ProgressBar,
        toDo: Boolean
    ) {
        if (toDo) {
            job = scope.launch {
                while (counter > 0) {
                    delay(1000)
                    counter--
                    textView.setText(counter.toString())
                    progressBar.setProgress(counter*100/counterr)
                }
                slider.isEnabled = true
                button.setText("СТАРТ")
            }
        } else {job.cancel()}
    }


}