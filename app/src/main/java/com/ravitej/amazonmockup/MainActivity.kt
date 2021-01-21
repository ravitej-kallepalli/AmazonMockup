package com.ravitej.amazonmockup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ravitej.amazonmockup.databinding.ActivityMainBinding
import com.ravitej.amazonmockup.screens.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //If the savedInstanceState is not null that means the fragment container is not empty
        if (savedInstanceState == null) {
            launchMovieListScreen()
        }
    }

    private fun launchMovieListScreen() {
        supportFragmentManager.beginTransaction()
                .add(binding.container.id, MovieListFragment.getInstance())
                .commit()
    }
}