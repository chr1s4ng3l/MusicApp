package com.example.musicapp.utils

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val musicViewModel: MusicViewModel by lazy {
        ViewModelProvider(requireActivity())[MusicViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setPositiveButton("RETRY") { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

}