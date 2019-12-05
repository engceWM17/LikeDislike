package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    //var like:Int=0
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences: SharedPreferences
    var dislike:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity","OnCreate")

        counterViewModel= ViewModelProviders.of(this).get(CounterViewModel::class.java)

        sharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE)
        sharedPreferences=getPreferences(Context.MODE_PRIVATE)

        imageViewLike.setOnClickListener{
            counterViewModel.incrementLike()
            textViewLike.text=counterViewModel.likeCount.toString()
            //like++
            //textViewDislike.text=like.toString()
        }
         imageViewDislike.setOnClickListener{
             dislike++
             textViewDislike.text=dislike.toString()
         }



    }
    override fun onStart() {
        Log.d("MainActivity","OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity","OnResume")
        val like=sharedPreferences.getInt(getString(R.string.like),0)
        counterViewModel.likeCount=like

        textViewLike.text=counterViewModel.likeCount.toString()
        textViewDislike.text=dislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity","OnPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like),counterViewModel.likeCount)
            apply()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity","OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity","OnDestroy")
        super.onDestroy()
    }
}
