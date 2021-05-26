package com.mellagusty.movieapppopcorn.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mellagusty.movieapppopcorn.ui.movie.MovieFavoriteFragment
import com.mellagusty.movieapppopcorn.ui.tvshow.TvFavoriteFragment

class SectionPagerAdapter(activity: AppCompatActivity, val name: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = TvFavoriteFragment()
        }
        return (fragment as Fragment).apply {
            arguments = Bundle().apply {
                putString(MovieFavoriteFragment.ORIGINAL_TITLE, name)
                putString(TvFavoriteFragment.ORIGINAL_NAME, name)
            }

        }

    }

}