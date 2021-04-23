package com.bayuokta.recipeapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.bayuokta.recipeapp.databinding.ActivityDetailRecipeBinding
import com.bayuokta.recipeapp.model.Food
import com.bumptech.glide.Glide

class DetailRecipeActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = intent.getParcelableExtra<Food>(EXTRA_DATA)
        binding.apply {

            topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }

            Glide.with(this@DetailRecipeActivity)
                    .load(recipe?.image)
                    .into(imgHeaderRecipe)

            tvTitleFoodRecipe.text = recipe?.name
            tvDescFoodRecipe.text = recipe?.desc
        }
    }
}