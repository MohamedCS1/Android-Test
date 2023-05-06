package com.example.data.local.moviesDetail

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MoviesDetailDatabaseTest{

    private lateinit var database: MoviesDetailDatabase
    private lateinit var dao: MovieDetailDao
    private lateinit var context: Context

    @Before
    fun setUp() {

        context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MoviesDetailDatabase::class.java).allowMainThreadQueries().build()
        dao = database.moviesDetailDao()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun testWriteAndRead() {
        runTest {
            val id = 8986798
            val detailMovieResponse = DetailMovieResponse(true ,"Any" ,null ,213 , listOf() ,null ,"Any" ,id ,"Any" ,"en" ,"First movie original title" ,"First movie overview" ,34554.32 ,"Any" ,
                listOf() ,
                listOf() ,"10/03/2002" ,null ,null ,
                listOf() ,"released" ,null ,"First movie title" ,null ,null ,null)
            runBlocking {
                dao.insertDetailTrendingMovie(detailMovieResponse)
            }

            runBlocking {
                Truth.assertThat(dao.getDetailTrendingMovie(id.toLong()).original_title == "First movie original title").isTrue()
                Truth.assertThat(dao.getDetailTrendingMovie(id.toLong()).overview == "First movie overview").isTrue()
                Truth.assertThat(dao.getDetailTrendingMovie(id.toLong()).popularity == 34554.32).isTrue()
            }
        }
    }

}