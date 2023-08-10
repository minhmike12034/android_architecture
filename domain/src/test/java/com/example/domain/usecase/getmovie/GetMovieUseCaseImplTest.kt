package com.example.domain.usecase.getmovie

import com.example.domain.either.Either
import com.example.domain.entity.MovieEntity
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class GetMovieUseCaseImplTest {

    private val movieRepository: MovieRepository = mock()

    private val getMovieUseCase = GetMovieUseCaseImpl(movieRepository = movieRepository)

    @Test
    fun `given movie repository returning an result, when execute, then return the result from the movie repository`() {
        runBlocking {
            // given
            val movieId = "123"
            val movieEntity = MovieEntity(id = movieId)
            val expected = Either.success(movieEntity)
            whenever(movieRepository.getMovie(movieId)).thenReturn(expected)

            // when
            val actual = getMovieUseCase.execute(movieId)

            // then
            verify(movieRepository, times(1)).getMovie(movieId)
            assertEquals(expected, actual)
        }
    }
}
