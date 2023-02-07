package com.example.musicapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.musicapp.R
import com.example.musicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Main fragment RockFragment
        replaceFragment(RockFragment())


        //Item fragment selected
        binding.bottonNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Classic -> replaceFragment(ClassicFragment())
                R.id.Pop -> replaceFragment(PopFragment())
                R.id.Rock -> replaceFragment(RockFragment())

            }
            true
        }



}

//Set fragments method
private fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frame_layout, fragment)
    fragmentTransaction.commit()
}
}