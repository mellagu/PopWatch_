package com.mellagusty.movieapppopcorn.ui.tvshow

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
import com.mellagusty.movieapppopcorn.databinding.FragmentListFavoriteBinding
import com.mellagusty.movieapppopcorn.ui.favorite.FavoriteViewModel
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest

class TvFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentListFavoriteBinding
    private lateinit var adapter: PopWatchPagedAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentListFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //call the view model
        val factory = PopViewModelRequest.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        //call the function
        showRecycleCard()
        getFavoriteTv()
    }

    private fun getFavoriteTv() {
        viewModel.getFavoriteTV().observe(viewLifecycleOwner, Observer { serial ->
            adapter.submitList(serial)
        })
    }

    private fun showRecycleCard() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(activity)
        adapter = PopWatchPagedAdapter {
            //intent for click data card
            val intent = Intent(requireContext(), TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV, it)
            startActivity(intent)
        }
        binding.rvFavorite.adapter = adapter
    }

    companion object {

        const val ORIGINAL_NAME = "original_name"

        @JvmStatic
        fun newInstance(name: String) =
                TvFavoriteFragment().apply {
                    arguments = Bundle().apply {
                        putString(ORIGINAL_NAME, name)
                    }
                }
    }
}