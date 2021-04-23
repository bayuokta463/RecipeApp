package com.bayuokta.recipeapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bayuokta.recipeapp.model.Food
import com.bayuokta.recipeapp.network.ApiConfig
import com.bayuokta.recipeapp.network.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _getFood = MutableLiveData<ApiResponse<List<Food?>?>>()
    val getFood: LiveData<ApiResponse<List<Food?>?>> = _getFood

    init {
        findFood()
    }

    fun findFood(){
        val client = ApiConfig.getApiService().getFood()
        client.enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if (response.isSuccessful){
                    if (!response.body().isNullOrEmpty())
                        _getFood.value = ApiResponse.Success(response.body())
                    else
                        _getFood.value = ApiResponse.Empty
                }else{
                    _getFood.value = ApiResponse.Error(response.message())
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                _getFood.value = ApiResponse.Error(t.message.toString())
            }

        })
    }

}