package com.example.firstapplication.data.remote


import com.example.firstapplication.model.SupportModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


object RetrofitService {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://notificator.ir/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService{

    @FormUrlEncoded
    @POST("send")
    suspend fun sendMessage(
        @Field("to") token: String =  "DbdJPYcVGTmL24ka6rQZXT06YSvM4fg6LD0yffqE",
        @Field("text") text: String
    ): SupportModel
}

//object RetrofitService {
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://notificator.ir/api/v1/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
//}
//
//data class ResponseModel(val ok : Boolean, val message: String)
//
//interface ApiService {
//    @GET("send")
//    suspend fun sendMessage(
//        @Query("to") to: String = "DbdJPYcVGTmL24ka6rQZXT06YSvM4fg6LD0yffqE",
//        @Query("text") text: String
//    ): ResponseModel
//}
