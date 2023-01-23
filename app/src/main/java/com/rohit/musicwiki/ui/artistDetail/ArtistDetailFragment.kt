package com.rohit.musicwiki.ui.artistDetail

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
import com.rohit.musicwiki.R
import com.rohit.musicwiki.databinding.FragmentArtistDetailBinding
import com.rohit.musicwiki.ui.home.TagsRecyclerAdapter
import com.rohit.musicwiki.utils.Result
import javax.inject.Inject

private const val TAG = "ArtistDetailFragment"
class ArtistDetailFragment : Fragment() {

    private lateinit var binding: FragmentArtistDetailBinding

    private lateinit var viewModel: ArtistDetailsViewModel
    private lateinit var tagsAdapter: TagsRecyclerAdapter

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


        checkArtistInfo()
        return binding.root
    }

    private fun checkArtistInfo() {
        viewModel.artistInfo.observe(viewLifecycleOwner, Observer {
            if (it.status== Result.Status.SUCCESS){
                Log.d(TAG, "checkTagInfo: ${it.data}")
                binding.apply {
                    followers.text = it.data?.artist?.stats?.listeners
                    playcount.text =  it.data?.artist?.stats?.playcount
                    descripTion.text =  it.data?.artist?.bio?.summary
                }
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }


}