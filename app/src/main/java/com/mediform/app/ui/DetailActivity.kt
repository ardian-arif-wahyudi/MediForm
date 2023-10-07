package com.mediform.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mediform.app.R
import com.mediform.app.data.PasienDataResponse
import com.mediform.app.databinding.ActivityDetailBinding
import com.mediform.app.retrofit.ApiClient
import com.mediform.app.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val backButton = findViewById<ImageButton>(R.id.back_btn)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val deleteButton = findViewById<ImageView>(R.id.delete_btn)
        deleteButton.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        apiService = ApiClient.getInstance()
        getData()
    }

    private fun getData() {
        val pasienId = intent.getIntExtra("pasienId", 0).toString()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getDataPasienById(pasienId)

                var noRM = response.noRM
                var nama = response.nama
                var tanggal = response.tanggalLahir
                var agama = response.agama
                var jeniskelamin = response.jenisKelamin
                var alamat = response.alamat
                var pekerjaan = response.pekerjaan
                var statusAskes = response.statusAsKes
                var statusKawin = response.statusKawin
                var namaPJ = response.namaPenanggungJawab
                var goldar = response.golonganDarah
                var riwayatPenyakit = response.riwayatPenyakit
                var alergiObat = response.alergiObat

                binding.apply {
                        tvNoRM.text = noRM
                        tvNama.text = nama
                        tvReligion.text = agama
                        tvDate.text = tanggal.toString()
                        tvGender.text = jeniskelamin
                        tvAddress.text = alamat
                        tvWork.text = pekerjaan
                        tvInsurance.text = statusAskes
                        tvMarried.text = statusKawin
                        tvCaretaker.text = namaPJ
                        tvBlood.text = goldar
                        tvMedicalHistory.text = riwayatPenyakit
                        tvDrugAllergy.text = alergiObat
                    }

            } catch (e: Exception) {
                val errorMessage = "Failed to fetch attraction data. Please check your internet connection."
                Toast.makeText(this@DetailActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", errorMessage, e)
            }
        }
    }
    private fun showDeleteConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete Data")
        alertDialog.setMessage("Are you sure you want to delete this data?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            // Panggil metode untuk menghapus data
            deleteData()
        }
        alertDialog.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun deleteData() {
        val pasienId = intent.getIntExtra("pasienId", 0).toString()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.deletePasienById(pasienId)
                // Handle response (you may want to check if the deletion was successful)

                // Setelah penghapusan berhasil, arahkan pengguna kembali ke HomeActivity
                val intent = Intent(this@DetailActivity, HomeActivity::class.java)
                startActivity(intent)

            } catch (e: Exception) {
                val errorMessage = "Failed to delete data. Please check your internet connection."
                Log.e("API_DELETE_ERROR", errorMessage, e)
            }
        }
    }
}