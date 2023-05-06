package com.example.data.local


import com.example.data.local.movies.MoviesDatabaseTest
import com.example.data.local.moviesDetail.MoviesDetailDatabaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@SuiteClasses(MoviesDatabaseTest::class ,MoviesDetailDatabaseTest::class)
class databasesTestSuite