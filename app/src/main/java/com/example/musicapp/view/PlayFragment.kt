package com.example.musicapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentPlayBinding
import com.example.musicapp.viewModel.MusicViewModel

class PlayFragment : Fragment() {

    private val binding by lazy {
        FragmentPlayBinding.inflate(layoutInflater)
    }

    private val musicViewModel: MusicViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.url.text = musicViewModel.urlTrack



        // Inflate the layout for this fragment
        return binding.root
    }


}