package com.c4pn91.opchallenge.presentation.viewmodel

import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.domain.usecase.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PopularMoviesViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var viewModel: PopularMoviesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPopularMoviesUseCase = mockk()
        viewModel = PopularMoviesViewModel(getPopularMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getPopularMovies is called, state should be Loading and then Success`() = testScope.runBlockingTest {
        val movies = listOf(Movie(id = 1, title = "Movie 1"), Movie(id = 2, title = "Movie 2"))
        coEvery { getPopularMoviesUseCase.invoke() } returns Result.success(movies)

        assertEquals(PopularMoviesState.Init, viewModel.state.value)

        viewModel.getPopularMovies()

        // Avanzamos el tiempo en la coroutine
        testScheduler.apply { advanceTimeBy(1000); runCurrent() }

        assertEquals(PopularMoviesState.Success, viewModel.state.value)
        assertEquals(movies, viewModel.movies.value)
    }

    @Test
    fun `when getPopularMovies is called and fails, state should be Loading and then Failure`() = testScope.runBlockingTest {
        val errorMessage = "Network Error"
        coEvery { getPopularMoviesUseCase.invoke() } returns Result.failure(Exception(errorMessage))

        assertEquals(PopularMoviesState.Init, viewModel.state.value)

        viewModel.getPopularMovies()

        // Avanzamos el tiempo en la coroutine
        testScheduler.apply { advanceTimeBy(1000); runCurrent() }

        assertEquals(PopularMoviesState.Failure(errorMessage), viewModel.state.value)
    }
}