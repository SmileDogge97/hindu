package com.anushka.retrofitdemo


import com.google.gson.annotations.SerializedName

//4) esto lo cree con el plugin de json to kotlin
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)