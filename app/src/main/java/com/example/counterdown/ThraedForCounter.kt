package com.example.counterdown

import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThraedForCounter(counterr: Int = 10): AppCompatActivity() {
    var counter = counterr
    val scope = CoroutineScope(Dispatchers.Main)
    val counterText by lazy { findViewById<TextView>(R.id.couter) }
    fun startCoroutine() {
        var job = scope.launch {
            while (counter > 0) {
                delay(1000)
                counter--
                counterText.setText(counter.toString())
            }
        }
    }
    fun stopCoroutine() {
        scope.cancel()
    }

}