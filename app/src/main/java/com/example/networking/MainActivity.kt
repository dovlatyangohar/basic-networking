package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networking.api.CatFacts
import com.example.networking.api.CatFactsService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintView.setOnClickListener {
            getFact()
        }

    }

    private fun getFact(){

        val catFactsService = retrofit.create(CatFactsService::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call: Call<CatFacts> = catFactsService.getFact()
            val response: Response<CatFacts> = call.execute()
            val fact = response.body()!!
            Log.i("FACT", fact.toString())
            withContext(Dispatchers.Main){
                factTextView.text = fact.fact
            }
        }
    }
}