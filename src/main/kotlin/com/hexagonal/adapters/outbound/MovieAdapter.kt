package com.example.adapters.outbound

import com.example.movies.Movie
import com.example.ports.MoviePort
import com.example.repositories.MovieRepository
import org.springframework.stereotype.Service

@Service
class MovieAdapter(private val movieRepository: MovieRepository) : MoviePort {
    override fun create(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    override fun getMovie(id: Long): Movie? {
        return movieRepository.findById(id).orElse(null)
    }

    override fun getAllMovies(): List<Movie> {
        return movieRepository.findAll().toList()
    }
}