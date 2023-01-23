package com.rohit.musicwiki.ui.genreDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.rohit.musicwiki.MainApplication
import com.rohit.musicwiki.databinding.FragmentGenreDetailBinding
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

private const val TAG = "GenreDetailFragment"
class GenreDetailFragment : Fragment() {

    private lateinit var binding: FragmentGenreDetailBinding

    private lateinit var viewModel: GenreDetailsViewModel

    @Inject
    lateinit var viewModelFactory: GenreDetailViewModelFactory

    private val args: GenreDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreDetailBinding.inflate(inflater)

        (activity?.application as MainApplication).applicationComponent.injectGenreDetail(this)

        viewModel = ViewModelProvider(this,viewModelFactory)[GenreDetailsViewModel::class.java]

        val viewPager = binding.pager
        viewPager.adapter = GenreDetailPagerAdapter(this.childFragmentManager,args.tagItem.name)
        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(viewPager)
        viewModel.fetchTags(args.tagItem.name)

        checkTagInfo()
        return binding.root
    }

    private fun checkTagInfo() {
        viewModel.tagInfo.observe(viewLifecycleOwner, Observer {
            if (it.status== Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfo: ${it.data}")
                binding.apply {
                    tagDescription.text = it.data?.tag?.wiki?.content ?: "Error"
                    tagName.text = it.data?.tag?.name ?: "Error"
                }
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }


}