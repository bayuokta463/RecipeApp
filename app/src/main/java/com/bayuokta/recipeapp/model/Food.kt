package com.bayuokta.recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("image")
    val image:String? = null,
    @SerializedName("desc")
    val desc:String? = null
):Parcelable
