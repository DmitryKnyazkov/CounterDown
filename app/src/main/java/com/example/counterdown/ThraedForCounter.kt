package com.example.counterdown

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.google.android.material.slider.Slider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThraedForCounter(counterr: Int = 10) {
    var counter = counterr
    val scope = CoroutineScope(Dispatchers.Main)
    var job = scope.launch{}

    //    val counterText by lazy { findViewById<TextView>(R.id.couter) }
    fun startCoroutine(
        textView: TextView,
        slider: Slider,
        button: Button,
        toDo: Boolean
    ) {
        if (toDo) {
            job = scope.launch {
                while (counter > 0) {
                    delay(1000)
                    counter--
                    textView.setText(counter.toString())
                }
                slider.isEnabled = true
                button.setText("СТАРТ")
            }
        } else {job.cancel()}
    }


}