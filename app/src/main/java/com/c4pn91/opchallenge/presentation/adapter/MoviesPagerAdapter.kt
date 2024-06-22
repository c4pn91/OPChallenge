package com.c4pn91.opchallenge.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.c4pn91.opchallenge.presentation.view.movies.childs.PopularMoviesFragment
import com.c4pn91.opchallenge.presentation.view.movies.childs.UpcomingMoviesFragment
import com.c4pn91.opchallenge.presentation.view.movies.childs.TopRatedMoviesFragment


class MoviesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularMoviesFragment()
            1 -> TopRatedMoviesFragment()
            2 -> UpcomingMoviesFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}