package com.manuelsoft.supermovies2.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @field:SerializedName("genres")
    val genreList : List<Genre>
)