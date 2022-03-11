package com.nitkkr.sanjay.nestedRecyclerView

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "happy"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GotAPI.getApiService().getHouses().enqueue(object : Callback<List<HouseResponse>> {
            override fun onResponse(call: Call<List<HouseResponse>>, response: Response<List<HouseResponse>>) {
                Log.d(TAG, "onResponse: ${response.body()?.size ?: 0 } items received")
            }

            override fun onFailure(call: Call<List<HouseResponse>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}