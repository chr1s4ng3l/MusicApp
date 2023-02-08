package com.example.musicapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.musicapp.databinding.FragmentViewBinding
import com.example.musicapp.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassicFragment : Fragment() {

    private val binding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
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
    ): View {


        musicViewModel.getAllClassic()

        musicViewModel.classicItems.observe(viewLifecycleOwner){
            println(it?.artistName)
        }



        return binding.root

    }


}