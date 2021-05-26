package com.mellagusty.movieapppopcorn.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.mellagusty.movieapppopcorn.R
import com.mellagusty.movieapppopcorn.adapter.SectionPagerAdapter
import com.mellagusty.movieapppopcorn.databinding.FragmentFavoriteBinding
import com.mellagusty.movieapppopcorn.viewmodel.PopViewModelRequest

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Pager Adapter for Movie Television
    private fun pagerAdapter(name: String) {
        val sectionsPagerAdapter = SectionPagerAdapter(requireActivity() as AppCompatActivity, name)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PopViewModelRequest.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)
        pagerAdapter("name")
    }
    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }
}