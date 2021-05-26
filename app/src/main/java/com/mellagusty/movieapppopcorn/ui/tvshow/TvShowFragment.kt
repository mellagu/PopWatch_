package com.mellagusty.movieapppopcorn.ui.tvshow

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
import com.mellagusty.movieapppopcorn.databinding.FragmentTvshowBinding
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: PopWatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PopViewModelRequest.getInstance(requireActivity())
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        //calling function
        showRecycleCard()
        tvShowViewModel.setPopularTvShow()
        observeMainViewModel()
    }

    private fun showRecycleCard() {
        binding.rvCard.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        adapter = PopWatchAdapter {
            //intent for click data card
            val intent = Intent(requireContext(), TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV, it)
            startActivity(intent)
        }
        binding.rvCard.adapter = adapter
    }

    private fun observeMainViewModel() {
        tvShowViewModel.getPopularTvShow().observe(viewLifecycleOwner, { tvshow ->
            Log.d("Tv show Fragment", tvshow[0].id)
            if (tvshow != null) {
                adapter.setListData(tvshow)
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }

    }


}