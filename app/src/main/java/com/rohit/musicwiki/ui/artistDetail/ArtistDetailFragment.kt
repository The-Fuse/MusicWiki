package com.rohit.musicwiki.ui.artistDetail

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
import com.rohit.musicwiki.databinding.FragmentArtistDetailBinding
import com.rohit.musicwiki.models.TabItem
import com.rohit.musicwiki.ui.genreDetail.TabsClickListener
import com.rohit.musicwiki.ui.genreDetail.TabsRecyclerAdapter
import com.rohit.musicwiki.ui.home.TagsClickListener
import com.rohit.musicwiki.ui.home.TagsRecyclerAdapter
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

private const val TAG = "ArtistDetailFragment"
class ArtistDetailFragment : Fragment() {

    private lateinit var binding: FragmentArtistDetailBinding

    private lateinit var viewModel: ArtistDetailsViewModel
    private lateinit var tagsAdapter: TagsRecyclerAdapter
    private lateinit var tabAdapter: TabsRecyclerAdapter
    private lateinit var albumTabAdapter: TabsRecyclerAdapter
    @Inject
    lateinit var viewModelFactory: ArtistDetailViewModelFactory

    private val args: ArtistDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistDetailBinding.inflate(inflater)

        (activity?.application as MainApplication).applicationComponent.injectArtistDetail(this)

        viewModel = ViewModelProvider(this,viewModelFactory)[ArtistDetailsViewModel::class.java]

        binding.tabItem = args.artistItem
        viewModel.fetchArtistInfo(args.artistItem.artistName)
        viewModel.fetchTopTrackByArtist(args.artistItem.artistName)
        viewModel.fetchTopAlbumByArtist(args.artistItem.artistName)

        checkArtistInfo()
        checkArtistTopTrack()
        checkArtistTopAlbum()

        return binding.root
    }

    private fun checkArtistTopAlbum() {
        albumTabAdapter = TabsRecyclerAdapter(TabsClickListener {
            this.findNavController().navigate(ArtistDetailFragmentDirections.actionArtistDetailFragmentToAlbumDetailFragment(it))
        })
        binding.artistTopAlbumsRecyclerView.apply {
            adapter = albumTabAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        }
        viewModel.artistTopAlbum.observe(viewLifecycleOwner, Observer {
            val data = mutableListOf<TabItem>()
            val trackData = it
            if (it.status == Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfo: ${it.data}")
                for (i in trackData.data?.topalbums?.album!!) {
                    val singleTabData = TabItem(i.name, i.image[1].text, i.artist.name)
                    data.add(singleTabData)
                }
                albumTabAdapter.submitList(data)
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }

    private fun checkArtistTopTrack() {
        tabAdapter = TabsRecyclerAdapter(TabsClickListener {})
        binding.artistTrackRecyclerView.apply {
            adapter = tabAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        }
        viewModel.artistTopTrack.observe(viewLifecycleOwner, Observer {
            val data = mutableListOf<TabItem>()
            val trackData = it
            if (it.status == Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfo: ${it.data}")
                for (i in trackData.data?.toptracks?.track!!) {
                    val singleTabData = TabItem(i.name, i.image[1].text, i.artist.name)
                    data.add(singleTabData)
                }
                tabAdapter.submitList(data)
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }

    private fun checkArtistInfo() {
        tagsAdapter = TagsRecyclerAdapter(TagsClickListener {
            this.findNavController().navigate(ArtistDetailFragmentDirections.actionArtistDetailFragmentToGenreDetailFragment(it))
        })
        binding.artistTagsRecyclerView.adapter = tagsAdapter
        binding.artistTagsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel.artistInfo.observe(viewLifecycleOwner, Observer {
            if (it.status== Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfo: ${it.data}")
                binding.apply {
                    followers.text = it.data?.artist?.stats?.listeners
                    playcount.text =  it.data?.artist?.stats?.playcount
                    descripTion.text =  it.data?.artist?.bio?.summary
                    tagsAdapter.submitList(it.data?.artist?.tags?.tag)
                }
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }


}