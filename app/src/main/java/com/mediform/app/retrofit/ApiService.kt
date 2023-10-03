package com.mediform.app.retrofit

import com.mediform.app.data.PasienData
import com.mediform.app.data.PasienDataResponse
import com.mediform.app.retrofit.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/Pasiens/1")
    suspend fun getDataPasien(): PasienDataResponse

    @GET("api/Pasiens")
    suspend fun getAllDataPasien(): List<PasienData>

}