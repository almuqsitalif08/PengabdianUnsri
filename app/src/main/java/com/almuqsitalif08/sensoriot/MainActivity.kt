package com.almuqsitalif08.sensoriot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.almuqsitalif08.sensoriot.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val database =
        FirebaseDatabase.getInstance("https://sensor-d1b2c-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val myRef1 = database.getReference("arduino1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            this@MainActivity.finish()
        }

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
        }

        showData(
            database,
            "arduino1",
            binding.etLocate1,
            binding.tvThermal1,
            binding.tvHumidity1,
            binding.tvSmoke1,
            binding.tvFlame1,
            binding.tvSoil1
        )

        showData(
            database,
            "arduino2",
            binding.etLocate2,
            binding.tvThermal2,
            binding.tvHumidity2,
            binding.tvSmoke2,
            binding.tvFlame2,
            binding.tvSoil2
        )

        showData(
            database,
            "arduino3",
            binding.etLocate3,
            binding.tvThermal3,
            binding.tvHumidity3,
            binding.tvSmoke3,
            binding.tvFlame3,
            binding.tvSoil3
        )
    }

    private fun showData(
        databaseFUnction: FirebaseDatabase,
        reference: String,
        locate: EditText,
        thermal: TextView,
        humidity: TextView,
        smoke: TextView,
        flame: TextView,
        soil: TextView
    ) {
        val myRef = databaseFUnction.getReference(reference)

        locate.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val loc = locate.text.toString()
                myRef.child("locate").setValue(loc)
            }
        }

        myRef.child("locate").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        locate.setText(data.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }

        })

        myRef.child("dht11").child("show").child("thermal").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        thermal.text = data.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }
        })

        myRef.child("dht11").child("show").child("humidity").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        humidity.text = data.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }
        })

        myRef.child("mq2").child("show").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        smoke.text = data.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }
        })

        myRef.child("flame").child("show").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        flame.text = data.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }
        })

        myRef.child("soil").child("show").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value
                if (data != null) {
                    if (data.toString().isNotEmpty()){
                        soil.text = data.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error.details + " " + error.message)
            }
        })
    }
}