package com.example.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.domain.models.detailMovie.Genre
import com.example.domain.models.detailMovie.ProductionCompany
import com.example.domain.models.detailMovie.ProductionCountry
import com.example.domain.models.detailMovie.SpokenLanguage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class Converters {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @TypeConverter
    fun fromIntList(intList: List<Int>): String {
        return Gson().toJson(intList)
    }

    @TypeConverter
    fun toIntList(intListString: String): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(intListString, type)
    }

    @TypeConverter
    fun fromGenreList(genreList: List<Genre>): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreList: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genreList, type)
    }

    @TypeConverter
    fun fromProductionList(productionCompanyList: List<ProductionCompany>): String {
        return Gson().toJson(productionCompanyList)
    }

    @TypeConverter
    fun toProductionList(productionCompanyList: String): List<ProductionCompany> {
        val type = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson(productionCompanyList, type)
    }


    @TypeConverter
    fun fromSpokenLanguageList(spokenLanguageList: List<SpokenLanguage>): String {
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageList: String): List<SpokenLanguage> {
        val type = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson(spokenLanguageList, type)
    }


    @TypeConverter
    fun fromProductionCountryList(productionCountryList: List<ProductionCountry>): String {
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountryList(productionCountryList: String): List<ProductionCountry> {
        val type = object : TypeToken<List<ProductionCountry>>() {}.type
        return Gson().fromJson(productionCountryList, type)
    }



}