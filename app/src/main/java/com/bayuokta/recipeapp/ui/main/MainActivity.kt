package com.bayuokta.recipeapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bayuokta.recipeapp.ui.detail.DetailRecipeActivity
import com.bayuokta.recipeapp.R
import com.bayuokta.recipeapp.adapter.AdapterRecipes
import com.bayuokta.recipeapp.databinding.ActivityMainBinding
import com.bayuokta.recipeapp.model.Food
import com.bayuokta.recipeapp.network.ApiResponse


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()

        binding.srlMain.setOnRefreshListener {
            mainViewModel.findFood()
            swipeRefresh()
        }

    }

    private fun fetchData() = mainViewModel.getFood.observe(this, { data ->
        when (data) {
            ApiResponse.Empty -> {
                emptyData()
            }
            is ApiResponse.Error -> {
                networkError()
            }
            is ApiResponse.Success -> {
                networkSuccess()

                val adapterRecipes = AdapterRecipes()
                adapterRecipes.setData(data.data)

                adapterRecipes.onItemClick = { food ->
                    val dataFood = Food(
                        name = food?.name,
                        image = food?.image,
                        desc = food?.desc

                    )
                    val detailRecipeActivity = Intent(this, DetailRecipeActivity::class.java)
                    detailRecipeActivity.putExtra(DetailRecipeActivity.EXTRA_DATA,dataFood)
                    startActivity(detailRecipeActivity)
                }

                binding.rvRecipes.layoutManager = GridLayoutManager(this,resources.getInteger(R.integer.span))
                binding.rvRecipes.adapter = adapterRecipes

            }
        }

    })

    private fun networkError() =
        binding.apply {
            rvRecipes.visibility = View.GONE
            binding.loading.root.visibility = View.GONE
            animationEmptyData.visibility = View.GONE
            tvEmptyData.visibility = View.GONE

            animationErrorNetwork.visibility = View.VISIBLE
        }

    private fun networkSuccess() =
        binding.apply {
            binding.loading.root.visibility = View.GONE
            animationEmptyData.visibility = View.GONE
            tvEmptyData.visibility = View.GONE
            animationErrorNetwork.visibility = View.GONE

            rvRecipes.visibility = View.VISIBLE

        }

    private fun emptyData() =
        binding.apply {
            binding.loading.root.visibility = View.GONE
            animationErrorNetwork.visibility = View.GONE
            rvRecipes.visibility = View.GONE

            animationEmptyData.visibility = View.VISIBLE
            tvEmptyData.visibility = View.VISIBLE
        }

    private fun swipeRefresh() =
        binding.apply {
            srlMain.isRefreshing = false
            rvRecipes.visibility = View.GONE
            animationErrorNetwork.visibility = View.GONE
            rvRecipes.visibility = View.GONE
            animationEmptyData.visibility = View.GONE
            tvEmptyData.visibility = View.GONE

            loading.root.visibility = View.VISIBLE
        }


}