package com.example.data.movies

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.local.movies.MoviesDao
import com.example.data.local.movies.MoviesDatabase
import com.example.domain.models.trendingMovies.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MoviesDatabaseTest
{

    private lateinit var database: MoviesDatabase
    private lateinit var dao: MoviesDao
    private lateinit var context: Context

    @Before
    fun setUp() {

        context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).allowMainThreadQueries().build()
        dao = database.moviesDao()
    }

    @After
    fun close() {
        database.close()
    }

    @Test
    fun testWriteAndRead() {
        runTest {
            val id = 8986798
            val movie = Movie(false ,"Any" , listOf(3 ,4) ,id ,"en" ,"First movie" ,"First Movie overview" ,23443.54 ,"Any" ,"10/03/2002" ,"Title First movie" ,null ,false ,132.4 ,4356)
            runBlocking {
                dao.insertTrendingMovie(movie)
            }

            runBlocking {
                assertThat(dao.getAllTrendingMovies()[0].original_title == "First movie").isTrue()
                assertThat(dao.getAllTrendingMovies()[0].overview == "First Movie overview").isTrue()
                assertThat(dao.getAllTrendingMovies()[0].popularity == 23443.54).isTrue()
            }
        }
    }

    @Test
    fun testReadAllMovies()
    {
        runTest {

            runBlocking {
                dao.insertTrendingMovie(Movie(false ,"Any" , listOf(3 ,4) ,3245 ,"en" ,"First movie" ,"First Movie overview" ,23443.54 ,"Any" ,"10/03/2002" ,"Title First movie" ,null ,false ,132.4 ,4356))
                dao.insertTrendingMovie(Movie(false ,"Any" , listOf(3 ,4) ,987 ,"ar" ,"Second movie" ,"Second Movie overview" ,8990.67 ,"Any" ,"10/03/2012" ,"Title second movie" ,null ,false ,87667.4 ,34534))
            }

            runBlocking{
                assertThat(dao.getAllTrendingMovies()).containsExactly(Movie(false ,"Any" , listOf(3 ,4) ,3245 ,"en" ,"First movie" ,"First Movie overview" ,23443.54 ,"Any" ,"10/03/2002" ,"Title First movie" ,null ,false ,132.4 ,4356),
                    Movie(false ,"Any" , listOf(3 ,4) ,987 ,"ar" ,"Second movie" ,"Second Movie overview" ,8990.67 ,"Any" ,"10/03/2012" ,"Title second movie" ,null ,false ,87667.4 ,34534))
            }

        }
    }
}