package com.example.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
class MovieController(private val moviePort: MoviePort) {

    @PostMapping
    fun createMovie(@RequestBody movie: Movie): ResponseEntity<Movie> {
        val createdMovie = moviePort.create(movie)
        return ResponseEntity.ok(createdMovie)
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Long): ResponseEntity<Movie?> {
        val movie = moviePort.getMovie(id)
        return if (movie != null) {
            ResponseEntity.ok(movie)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllMovies(): ResponseEntity<List<Movie>> {
        val movies = moviePort.getAllMovies()
        return ResponseEntity.ok(movies)
    }
}