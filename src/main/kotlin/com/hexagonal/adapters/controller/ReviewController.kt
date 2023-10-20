package com.example.controllers

import com.example.movies.Review
import com.example.ports.ReviewPort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reviews")
class ReviewController(private val reviewPort: ReviewPort) {

    @PostMapping
    fun createReview(@RequestBody review: Review): ResponseEntity<Review> {
        val createdReview = reviewPort.createReview(review)
        return ResponseEntity.ok(createdReview)
    }

    @GetMapping("/{id}")
    fun getReview(@PathVariable id: Long): ResponseEntity<Review?> {
        val review = reviewPort.getReview(id)
        return if (review != null) {
            ResponseEntity.ok(review)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/movie/{movieId}")
    fun getAllReviewsForMovie(@PathVariable movieId: Long): ResponseEntity<List<Review>> {
        val reviews = reviewPort.getAllReviewsForMovie(movieId)
        return ResponseEntity.ok(reviews)
    }
}