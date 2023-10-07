package com.mediform.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mediform.app.R
import com.mediform.app.data.PasienData
import com.mediform.app.retrofit.ApiClient
import com.mediform.app.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var rv_pasien: RecyclerView
    private lateinit var apiService: ApiService
    private lateinit var pasienAdapter: PasienDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        apiService = ApiClient.getInstance()
        rv_pasien = findViewById(R.id.rv_pasien)
        rv_pasien.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false )
        fetchDataPasien()
    }

    private fun fetchDataPasien() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAllDataPasien()
                if (response.isNotEmpty()) {
                    // Jika response tidak kosong, Anda dapat langsung menggunakan datanya
                    pasienAdapter = PasienDataAdapter(response)
                    rv_pasien.adapter = pasienAdapter
                    pasienAdapter.setOnItemClickListener(object : PasienDataAdapter.OnItemClickListener {
                        override fun onItemClick(pasien: PasienData) {
                            // Tangani acara klik item
                            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("pasienId", pasien.pasienId)
                            startActivity(intent)
                        }
                    })
                } else {
                    Toast.makeText(this@HomeActivity, "No data available", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@HomeActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.d("API_FETCH_ERROR", e.toString())
            }
        }
    }

}