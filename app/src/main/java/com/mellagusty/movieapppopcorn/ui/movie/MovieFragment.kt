package com.mellagusty.movieapppopcorn.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mellagusty.movieapppopcorn.adapter.PopWatchAdapter
import com.mellagusty.movieapppopcorn.databinding.FragmentMovieBinding
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: PopWatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PopViewModelRequest.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        //call the function
        showRecycleCard()
        movieViewModel.setNowPlayingMovie()
        observeMainViewModel()

    }


    private fun observeMainViewModel() {
        movieViewModel.getNowPlayingMovie().observe(viewLifecycleOwner, { movie ->
            Log.d("Movie Fragment", movie[0].id)
            if (movie != null) {
                adapter.setListData(movie)
                false.showLoading()
            }

        })
    }

    private fun showRecycleCard() {
        binding.rvCard.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        adapter = PopWatchAdapter {
            //intent for click data card
            val intent = Intent(requireContext(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        binding.rvCard.adapter = adapter

    }

    //Show the progress bar while load
    private fun Boolean.showLoading() {
        if (this) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}