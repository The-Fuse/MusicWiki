package com.rohit.musicwiki.ui.genreDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rohit.musicwiki.MainApplication
import com.rohit.musicwiki.databinding.FragmentTabBinding
import com.rohit.musicwiki.models.TabItem
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

private const val TAG = "TabFragment"
class TabFragment(private val tagName: String, private val tabName: String) : Fragment() {

    private lateinit var binding: FragmentTabBinding
    private lateinit var tab: String
    private lateinit var tagN: String

    private lateinit var viewModel: TabViewModel
    private lateinit var adapter: TabsRecyclerAdapter

    @Inject
    lateinit var viewModelFactory: TabViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabBinding.inflate(inflater)

        (activity?.application as MainApplication).applicationComponent.injectTab(this)

        viewModel = ViewModelProvider(this,viewModelFactory)[TabViewModel::class.java]
        tab = tabName
        tagN = tagName

        when(tabName){
            "Artist" -> viewModel.fetchTopArtists(tagN)
            "Album" -> viewModel.fetchTopAlbums(tagN)
            "Track" -> viewModel.fetchTopTracks(tagN)
        }

        when(tabName){
            "Artist" -> checkArtistData()
            "Album" -> checkAlbumsData()
            "Track" -> checkTracksData()
        }

        return binding.root
    }

    private fun checkArtistData() {
        adapter = TabsRecyclerAdapter(TabsClickListener {
            this.findNavController().navigate(GenreDetailFragmentDirections.actionGenreDetailFragmentToArtistDetailFragment(it))
        })
        binding.tabRecyclerView.adapter = adapter
        viewModel.artistData.observe(viewLifecycleOwner, Observer {
            val data = mutableListOf<TabItem>()
            val artistData = it
            if (it.status==Result.Status.SUCCESS) {
                for (i in artistData.data?.topartists?.artist!!) {
                    val singleTabData = TabItem(null, i.image[1].text, i.name)
                    data.add(singleTabData)
                }
                adapter.submitList(data)
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkArtistData: ${it.message}")
            }
        })
    }

    private fun checkAlbumsData() {
        adapter = TabsRecyclerAdapter(TabsClickListener {
            this.findNavController().navigate(GenreDetailFragmentDirections.actionGenreDetailFragmentToAlbumDetailFragment(it))
        })
        binding.tabRecyclerView.adapter = adapter
        viewModel.albumsData.observe(viewLifecycleOwner, Observer {
            val data = mutableListOf<TabItem>()
            val albumsData = it
            if (it.status==Result.Status.SUCCESS) {
                for (i in albumsData.data?.albums?.album!!) {
                    val singleTabData = TabItem(i.name, i.image[1].text, i.artist.name)
                    data.add(singleTabData)
                }
                adapter.submitList(data)
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkAlbumsData: ${it.message}")
            }
        })
    }

    private fun checkTracksData() {
        adapter = TabsRecyclerAdapter(TabsClickListener {
            //Toast.makeText(this@TabFragment,"Tracks details are not available", Toast.LENGTH_SHORT).show()
        })
        binding.tabRecyclerView.adapter = adapter
        viewModel.tracksData.observe(viewLifecycleOwner, Observer {
            val data = mutableListOf<TabItem>()
            val trackData = it
            if (it.status==Result.Status.SUCCESS) {
                for (i in trackData.data?.tracks?.track!!) {
                    val singleTabData = TabItem(i.name, i.image[1].text, i.artist.name)
                    data.add(singleTabData)
                }
                adapter.submitList(data)
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTracksData: ${it.message}")
            }
        })
    }

}