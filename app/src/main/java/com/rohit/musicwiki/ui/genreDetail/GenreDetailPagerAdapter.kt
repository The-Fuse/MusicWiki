package com.rohit.musicwiki.ui.genreDetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GenreDetailPagerAdapter(fm: FragmentManager, private val tagName: String): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TabFragment(tagName,"Album")
            }
            1 -> {
                return TabFragment(tagName,"Artist")
            }
            2 -> {
                return TabFragment(tagName,"Track")
            }
            else -> {
                return TabFragment(tagName,"Album")
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Albums"
            }
            1 -> {
                return "Artists"
            }
            2 -> {
                return "Tracks"
            }
        }
        return super.getPageTitle(position)
    }
}