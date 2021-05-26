package com.mellagusty.movieapppopcorn.ui.tvshow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mellagusty.movieapppopcorn.R
import com.mellagusty.movieapppopcorn.data.local.TvPoster
import com.mellagusty.movieapppopcorn.data.remote.Poster
import com.mellagusty.movieapppopcorn.databinding.ActivityTvShowDetailBinding
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var tvShowModel: TvShowViewModel
    private lateinit var tvPoster: TvPoster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvshow = intent.getParcelableExtra<Poster>(EXTRA_TV) as Poster

        val factory = PopViewModelRequest.getInstance(this)
        tvShowModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        //Set For Back Arrow
        binding.back.setOnClickListener {
            onBackPressed()
        }
        //call the function
        tvShowModel.setDetailTvShow(tvshow.id)
        observeViewModel()
        favorite(tvshow)

    }

    private fun favorite(tvshow: Poster) {
        tvPoster = TvPoster(
            tvshow.id,
            tvshow.first_air_date?:"",
                tvshow.poster_path,
            tvshow.original_name?:""

        )
        val id = tvPoster.id

        //Check for Favorite
        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = id.let { tvShowModel.checkFavoriteTV(it) }
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
                tvShowModel.addToFavoriteTV(
                    tvPoster
                )
                Toast.makeText(this, getString(R.string.addFavorite), Toast.LENGTH_LONG).show()

            } else {

                tvShowModel.removeTVFavorite(id)
                Toast.makeText(this, getString(R.string.deletedFavorite), Toast.LENGTH_LONG).show()
            }
            binding.toggleFavorite.isChecked = _isChecked
        }

    }

    private fun observeViewModel() {
        tvShowModel.getDetailTVShow().observe(this, { tvdetail ->
            binding.tvLanguage.text = tvdetail.original_language
            binding.tvType.text = tvdetail.type
            binding.tvStatus.text = tvdetail.status
            binding.tvStar.text = tvdetail.vote_average.toString()
            binding.tvGenres.text = tvdetail.genres.joinToString { it.name }
            binding.tvEpisodeTotal.text = tvdetail.number_of_episodes.toString()
            binding.tvOriginalName.text = tvdetail.original_name
            binding.tvTagline.text = tvdetail.tagline
            binding.tvOverview.text = tvdetail.overview
            Glide.with(this)
                .load(tvdetail.baseUrl + tvdetail.poster_path)
                .into(binding.ivTvDetail)

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
        const val EXTRA_TV = "extra_tv"
    }
}