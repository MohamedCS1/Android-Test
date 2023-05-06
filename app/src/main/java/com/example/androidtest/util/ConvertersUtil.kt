package com.example.androidtest.util

import com.example.domain.models.detailMovie.Genre

object ConvertersUtil {
    fun turnGenresToCategoriesString(arrayGenres: List<Genre>):String
    {
        val categories = arrayListOf<String>()

        for (genre in arrayGenres)
        {
            genre.name?.let { categories.add(it) }
        }

        return categories.joinToString(" - ")
    }
}