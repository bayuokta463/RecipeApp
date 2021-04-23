package com.bayuokta.recipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayuokta.recipeapp.R
import com.bayuokta.recipeapp.databinding.ItemRecipeBinding
import com.bayuokta.recipeapp.model.Food
import com.bumptech.glide.Glide

class AdapterRecipes:RecyclerView.Adapter<AdapterRecipes.ViewHolder>() {

    private var listData = ArrayList<Food?>()
    var onItemClick:((Food?)->Unit)? = null

    fun setData(newListData:List<Food?>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recipe,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listData[position])

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecipeBinding.bind(itemView)
        fun bind(food:Food?){
            with(binding){
                tvTitleFoodRecipe.text = food?.name
                Glide.with(itemView.context)
                    .load(food?.image)
                        .placeholder(R.drawable.ic_local_dining)
                        .error(R.drawable.ic_broken_image)
                    .into(imgFoodRecipe)
            }
        }
        init {
            binding.cvContainer.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}