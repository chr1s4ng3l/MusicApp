package com.example.musicapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import com.example.musicapp.adapter.MusicAdapter
import com.example.musicapp.data.model.MusicItems
import com.example.musicapp.databinding.FragmentViewBinding
import com.example.musicapp.utils.BaseFragment
import com.example.musicapp.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PopFragment"

@AndroidEntryPoint
class PopFragment : BaseFragment() {

    private val binding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
    }

    private val musicAdapter by lazy {
        MusicAdapter {
            //Send the urlTrack
            musicViewModel.urlTrack = it
            findNavController().navigate(R.id.action_Pop_to_PlayFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Swipe Refresh
        binding.swipeRefresh.setOnRefreshListener {
            musicViewModel.isLoading.postValue(false)
            getSongsPop()
            binding.swipeRefresh.isRefreshing = false
        }

        //RecyclerView
        binding.musicRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = musicAdapter

            getSongsPop()


            musicViewModel.isLoading.observe(viewLifecycleOwner) {
                binding.progress.isVisible = it
            }

            return binding.root

        }
    }

    private fun getSongsPop() {
        //ViewModel here
        musicViewModel.pop.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    musicViewModel.isLoading.postValue(true)
                }
                is UIState.SUCCESS<MusicItems> -> {
                    musicViewModel.isLoading.postValue(false)
                    Log.d(TAG, "onCreateView: ${state.response}")
                    musicAdapter.updateItems(
                        state.response.results ?: emptyList(),
                        requireContext()
                    )
                }
                is UIState.ERROR -> {
                    musicViewModel.isLoading.postValue(false)
                    state.error.localizedMessage?.let {
                        showError(it) {

                        }
                    }
                }
            }
        }
    }
}