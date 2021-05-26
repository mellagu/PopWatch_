package com.mellagusty.movieapppopcorn.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mellagusty.movieapppopcorn.R
import com.mellagusty.movieapppopcorn.data.local.MoviePoster
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.databinding.ActivityMovieDetailBinding
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieViewModel
    private lateinit var moviePoster: MoviePoster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = PopViewModelRequest.getInstance(this)
        movieDetailViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        //Set For Back Arrow
        binding.back.setOnClickListener {
            onBackPressed()
        }

        val movie = intent.getParcelableExtra<Poster>(EXTRA_MOVIE) as Poster
        //call the function
        movieDetailViewModel.setDetailMovie(movie.id)
        observeViewModel()
        favorite(movie)

    }

    private fun favorite(movie: Poster) {
        moviePoster = MoviePoster(
            movie.id,
            movie.release_date ?: "",
            movie.poster_path,
            movie.original_title ?: ""
        )
        val id = moviePoster.id
        //Check for favorite
        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = id.let { movieDetailViewModel.checkFavoriteMovie(it) }
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFavorite.isChecked = true
                        _isChecked = true
                    } else {
                        binding.toggleFavorite.isChecked = false
                        _isChecked = false
                    }

                }
            }

        }
        //Add or delete in Favorite
        binding.toggleFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                movieDetailViewModel.addToFavoriteMovie(
                    moviePoster
                )

                Toast.makeText(this, getString(R.string.addFavorite), Toast.LENGTH_LONG).show()
            } else {

                movieDetailViewModel.removeMovieFavorite(id)
                Toast.makeText(this, getString(R.string.deletedFavorite), Toast.LENGTH_LONG).show()
            }
            binding.toggleFavorite.isChecked = _isChecked
        }
    }

    private fun observeViewModel() {
        movieDetailViewModel.gerDetailMovie().observe(this, { moviedetail ->
            binding.tvLanguage.text = moviedetail.original_language
            binding.tvGenres.text = moviedetail.genres.joinToString { it.name }
            binding.tvOriginalName.text = moviedetail.original_title
            binding.tvOverview.text = moviedetail.overview
            binding.tvTagline.text = moviedetail.tagline
            binding.tvStatus.text = moviedetail.status
            binding.tvStar.text = moviedetail.vote_average.toString()
            Glide.with(this)
                .load(moviedetail.baseUrl + moviedetail.poster_path)
                .into(binding.ivMovieDetail)

            showLoading(false)

        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}
