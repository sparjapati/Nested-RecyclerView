package com.nitkkr.sanjay.nestedRecyclerView

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nitkkr.sanjay.nestedRecyclerView.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "happy"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val houses = ArrayList<HouseResponse>()
    private lateinit var adapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GotAPI.getApiService().getHouses().enqueue(object : Callback<List<HouseResponse>> {
            override fun onResponse(call: Call<List<HouseResponse>>, response: Response<List<HouseResponse>>) {
                Log.d(TAG, "onResponse: ${response.body()?.size ?: 0} items received")
                response.body()?.let { houses.addAll(it) }
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<HouseResponse>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ParentAdapter(houses, object : ParentAdapter.ViewHolder.OnClickListener {
            override fun onTitleClicked(position: Int) {
                Toast.makeText(this@MainActivity, "title ${position + 1} clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onChildItemClicked(parentPosition: Int, childPosition: Int) {
                Toast.makeText(this@MainActivity, "item ${childPosition + 1} in row no. ${parentPosition + 1}  clicked", Toast.LENGTH_SHORT).show()
            }

        })
        binding.parentRecyclerView.adapter = adapter
    }
}