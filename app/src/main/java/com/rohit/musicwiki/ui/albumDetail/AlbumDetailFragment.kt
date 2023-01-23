package com.rohit.musicwiki.ui.albumDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohit.musicwiki.MainApplication
import com.rohit.musicwiki.R
import com.rohit.musicwiki.databinding.FragmentAlbumDetailBinding
import com.rohit.musicwiki.ui.home.HomeFragmentDirections
import com.rohit.musicwiki.ui.home.TagsClickListener
import com.rohit.musicwiki.ui.home.TagsRecyclerAdapter
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

private const val TAG = "AlbumDetailFragment"
class AlbumDetailFragment : Fragment() {

    private lateinit var binding: FragmentAlbumDetailBinding

    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var tagAdapter: TagsRecyclerAdapter

    @Inject
    lateinit var viewModelFactory: AlbumDetailViewModelFactory

    private val args: AlbumDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumDetailBinding.inflate(inflater)

        (activity?.application as MainApplication).applicationComponent.injectAlbumDetail(this)

        viewModel = ViewModelProvider(this,viewModelFactory)[AlbumDetailsViewModel::class.java]

        binding.tabItem = args.albumItem

        viewModel.fetchAlbumInfo(args.albumItem.artistName,args.albumItem.title!!)
        checkAlbumInfo()

        return binding.root
    }

    private fun checkAlbumInfo() {
        tagAdapter = TagsRecyclerAdapter(TagsClickListener {
            this.findNavController().navigate(AlbumDetailFragmentDirections.actionAlbumDetailFragmentToGenreDetailFragment(it))
        })
        binding.albumTagsRecyclerView.apply {
            adapter = tagAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.albumInfo.observe(viewLifecycleOwner, Observer {
            if (it.status== Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfoforAlbum: ${it.data?.album?.tags?.tag}")
                tagAdapter.submitList(it.data?.album?.tags?.tag)
                binding.description.text = it.data?.album?.wiki?.summary
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }


}