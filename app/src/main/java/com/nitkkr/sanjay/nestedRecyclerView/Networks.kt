package com.nitkkr.sanjay.nestedRecyclerView

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://game-of-thrones-quotes.herokuapp.com/v1/"
const val HOUSES_END_POINT = "houses"

object RetroFitProvider {
    private val retrofitClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getClient(): Retrofit {
        return retrofitClient
    }
}

interface ApiService {
    @GET(HOUSES_END_POINT)
    fun getHouses(): Call<List<HouseResponse>>
}

@Parcelize
data class HouseResponse(

    @field:SerializedName("members")
    val members: List<MemberItemResponse>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class MemberItemResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

object GotAPI {
    private val api: ApiService by lazy { RetroFitProvider.getClient().create(ApiService::class.java) }

    fun getApiService(): ApiService = api
}