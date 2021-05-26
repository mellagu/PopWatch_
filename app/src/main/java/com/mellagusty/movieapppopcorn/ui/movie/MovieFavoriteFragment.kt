package com.mellagusty.movieapppopcorn.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mellagusty.movieapppopcorn.adapter.PopWatchPagedAdapter
import com.mellagusty.movieapppopcorn.databinding.FragmentFavoriteListBinding
import com.mellagusty.movieapppopcorn.ui.favorite.FavoriteViewModel
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest


class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteListBinding
    private lateinit var adapter: PopWatchPagedAdapter
    private lateinit var viewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //call the view model
        val factory = PopViewModelRequest.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory).get(FavoriteViewModel::class.java)

        //call the function
        showRecycleCard()
        getFavoriteMovie()

    }

    private fun getFavoriteMovie() {
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer { movie ->
                adapter.submitList(movie)
        })
    }

    private fun showRecycleCard() {
        binding.rvFavorite.layoutManager =
            LinearLayoutManager(activity)
        adapter = PopWatchPagedAdapter {
            //intent for click data card
            val intent = Intent(requireContext(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        binding.rvFavorite.adapter = adapter
    }


    companion object {
        const val ORIGINAL_TITLE = "original_title"

        @JvmStatic
        fun newInstance(name: String): MovieFavoriteFragment {
            return MovieFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ORIGINAL_TITLE, name)
                }
            }
        }
    }
}