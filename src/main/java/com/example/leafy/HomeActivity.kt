package com.example.leafy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.adapter.TrendingAdapter
import com.example.leafy.api.RetrofitClient
import com.example.leafy.api.TheMealDBApi
import com.example.leafy.model.MealResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        try {
            searchView = findViewById(R.id.searchView)
            recyclerView = findViewById(R.id.rvTrending)
            recyclerView.layoutManager = LinearLayoutManager(this)
            trendingAdapter = TrendingAdapter { meal ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("MEAL", meal)
                startActivity(intent)
            }
            recyclerView.adapter = trendingAdapter

            bottomNavigationView = findViewById(R.id.bottom_navigation)
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationView.setupWithNavController(navController)

            fetchMeals("a")

            setupSearchView()
        } catch (e: Exception) {
            Log.e("HomeActivity", "Error initializing views: ${e.message}")
            Toast.makeText(this, "Error initializing views", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fetchMeals(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    fetchMeals(it)
                }
                return false
            }
        })
    }

    private fun fetchMeals(query: String) {
        val theMealDBApi = RetrofitClient.getClient().create(TheMealDBApi::class.java)
        theMealDBApi.searchMeals(query).enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {
                    val meals = response.body()?.meals ?: emptyList()
                    trendingAdapter.updateMeals(meals)
                } else {
                    showError("Response not successful")
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                showError("API call failed: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        Log.e("HomeActivity", message)
        Toast.makeText(this, "Failed to fetch meals: $message", Toast.LENGTH_SHORT).show()
    }
}
