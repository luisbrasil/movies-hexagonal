package com.example.ports

import com.example.movies.Movie

interface MoviePort {
    fun create(movie: Movie): Movie
    fun getMovie(id: Long): Movie?
    fun getAllMovies(): List<Movie>
}