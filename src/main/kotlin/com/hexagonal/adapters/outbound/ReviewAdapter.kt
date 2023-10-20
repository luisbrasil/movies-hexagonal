package com.example.adapters.outbound

import com.example.movies.Review
import com.example.ports.ReviewPort
import com.example.repositories.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewAdapter(private val reviewRepository: ReviewRepository) : ReviewPort {
    override fun createReview(review: Review): Review {
        return reviewRepository.save(review)
    }

    override fun getReview(id: Long): Review? {
        return reviewRepository.findById(id).orElse(null)
    }

    override fun getAllReviewsForMovie(movieId: Long): List<Review> {
        return reviewRepository.findAllByMovieId(movieId)
    }
}