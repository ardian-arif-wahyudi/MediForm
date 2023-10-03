package com.mediform.app.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PasienData(

   @field:SerializedName("pasienId"            ) var pasienId            : Int?    = null,
   @field:SerializedName("nama"                ) var nama                : String? = null,
   @field:SerializedName("noRM"                ) var noRM                : String? = null,
   @field:SerializedName("tanggalLahir"        ) var tanggalLahir        : Date? = null,
   @field:SerializedName("agama"               ) var agama               : String? = null,
   @field:SerializedName("jenisKelamin"        ) var jenisKelamin        : String? = null,
   @field:SerializedName("alamat"              ) var alamat              : String? = null,
   @field:SerializedName("pekerjaan"           ) var pekerjaan           : String? = null,
   @field:SerializedName("statusAsKes"         ) var statusAsKes         : String? = null,
   @field:SerializedName("statusKawin"         ) var statusKawin         : String? = null,
   @field:SerializedName("namaPenanggungJawab" ) var namaPenanggungJawab : String? = null,
   @field:SerializedName("golonganDarah"       ) var golonganDarah       : String? = null,
   @field:SerializedName("riwayatPenyakit"     ) var riwayatPenyakit     : String? = null,
   @field:SerializedName("alergiObat"          ) var alergiObat          : String? = null,
   @field:SerializedName("keadaanKeluar"       ) var keadaanKeluar       : String? = null,
   @field:SerializedName("caraKeluar"          ) var caraKeluar          : String? = null,
   @field:SerializedName("dokterId"            ) var dokterId            : Int?    = null,
   @field:SerializedName("dokter"              ) var dokter              : String? = null


)

