package com.mediform.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediform.app.R
import com.mediform.app.data.PasienData
import com.mediform.app.data.PasienDataResponse
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class PasienDataAdapter(private var pasienData: List<PasienData>) : RecyclerView.Adapter<PasienDataAdapter.ViewHolder>() {


    // Definisikan interface listener
    interface OnItemClickListener {
        fun onItemClick(pasienData: PasienData)
    }

    // Deklarasikan variabel untuk menyimpan instance listener
    private var onItemClickListener: OnItemClickListener? = null

    // Metode setter untuk mengatur listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rekam_medis, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pasienItem = pasienData[position]
        holder.bind(pasienItem)
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.US) // Set locale to English (United States)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT+7") // Atur zona waktu ke GMT+7
        //val formattedDate = dateFormat.format(pasienItem.createdAt)

        //holder.dateTextView.text = formattedDate
    }

    override fun getItemCount(): Int {
        return pasienData.size
    }
    fun updateData(newData: List<PasienData>) {
        pasienData = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noRMTextView: TextView = itemView.findViewById(R.id.textViewRM)
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewNama)

        init {
            // Setel click listener pada item view
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Dapatkan objek attraction yang diklik
                    val pasien = pasienData[position]
                    // Panggil metode onItemClick pada listener
                    onItemClickListener?.onItemClick(pasien)
                }
            }
        }


        fun bind(pasienData: PasienData) {
            noRMTextView.text = pasienData.noRM.toString()
            nameTextView.text = pasienData.nama.toString()

        }
    }
}