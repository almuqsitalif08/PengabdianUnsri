package com.almuqsitalif08.sensoriot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.almuqsitalif08.sensoriot.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            this@HistoryActivity.finish()
        }

        binding.btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}