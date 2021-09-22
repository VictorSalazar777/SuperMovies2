package com.manuelsoft.supermovies2.network.entries

import com.google.gson.annotations.SerializedName
import com.manuelsoft.supermovies2.model.Genre

data class GenresEntry(
    @field:SerializedName("genres")
    val genreList: List<Genre>
)