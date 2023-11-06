package com.almuqsitalif08.sensoriot

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.almuqsitalif08.sensoriot.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity(){
    private lateinit var binding: ActivityFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object: CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@FullscreenActivity, MainActivity::class.java))
                this@FullscreenActivity.finish()
            }

        }.start()
    }
}