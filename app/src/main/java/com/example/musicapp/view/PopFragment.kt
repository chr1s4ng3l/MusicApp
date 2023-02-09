package com.example.musicapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            Toast.makeText(parentFragment?.context, "URL: $it", Toast.LENGTH_LONG).show()
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
        binding.musicRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = musicAdapter

            //ViewModel here
            musicViewModel.pop.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UIState.LOADING -> {}
                    is UIState.SUCCESS<MusicItems> -> {
                        Log.d(TAG, "onCreateView: ${state.response}")
                        musicAdapter.updateItems(state.response.results ?: emptyList(), context)
                    }
                    is UIState.ERROR -> {
                        state.error.localizedMessage?.let {
                            showError(it) {

                            }
                        }
                    }
                }
            }

            return binding.root

        }
    }
}