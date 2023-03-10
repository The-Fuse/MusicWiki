package com.rohit.musicwiki.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohit.musicwiki.MainApplication
import com.rohit.musicwiki.R
import com.rohit.musicwiki.databinding.FragmentHomeBinding
import com.rohit.musicwiki.models.Tag
import javax.inject.Inject
import com.rohit.musicwiki.utils.Result

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: TagsRecyclerAdapter
    private lateinit var completeData: List<Tag>
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        (activity?.application as MainApplication).applicationComponent.injectHome(this)

        viewModel = ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]

        binding.expandButton.setOnClickListener {
            adapter.submitList(completeData)
        }
        viewModel.fetchTags()
        checkTags()
        return binding.root
    }

    private fun checkTags() {
        adapter = TagsRecyclerAdapter(TagsClickListener {
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGenreDetailFragment(it))
        })
        binding.tagsRecyclerView.adapter = adapter
        viewModel.tags.observe(viewLifecycleOwner, Observer {
            if (it.status==Result.Status.SUCCESS){
                val data = it.data?.toptags?.tag
                if (data != null) {
                    completeData = data
                    adapter.submitList(data.take(10))
                }
            }else if (it.status == Result.Status.ERROR){
                Log.d(TAG, "checkTags: ${it.message}")
            }
        })
    }

}