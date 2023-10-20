package com.example.ports

import com.example.movies.Review

interface ReviewPort {
    fun createReview(review: Review): Review
    fun getReview(id: Long): Review?
    fun getAllReviewsForMovie(movieId: Long): List<Review>
}