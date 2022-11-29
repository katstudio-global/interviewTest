package com.puerto.estudio.test.meals.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.puerto.estudio.test.R
import com.puerto.estudio.test.meals.main.MealCore
import com.puerto.estudio.test.meals.main.MealCoreImpl
import com.puerto.estudio.test.meals.services.MealNetworkServiceImpl
import com.puerto.estudio.test.meals.ui.adapters.MealRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MealsActivity : AppCompatActivity() {

    //Esto se debería de realizar mediante un ioc, para hacerlo de forma automatica y sea mucho más fácil de mantener
    //(dagger hilt, dagger, koin) etc
    private val mealCore: MealCore = MealCoreImpl(MealNetworkServiceImpl())
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var textViewError: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var mealRecyclerViewAdapter: MealRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)
        initViews()
        loadMealItems()
    }

    private fun loadMealItems() {
        lifecycleScope.launch(Dispatchers.IO){
            val mealList = mealCore.getMealList()

            withContext(Dispatchers.Main){
                if (mealList.isFailure){
                    textViewError.text = mealList.exceptionOrNull()?.message
                }else {
                    mealRecyclerViewAdapter.addMealItems(mealList.getOrNull())
                }
                progressBarLoading.visibility = View.INVISIBLE
            }
        }
    }

    private fun initViews() {
        progressBarLoading = findViewById(R.id.mealProgressbarId)
        textViewError = findViewById(R.id.mealErrorTextId)
        recyclerView = findViewById(R.id.mealRecyclerViewId)
        mealRecyclerViewAdapter = MealRecyclerViewAdapter()
        recyclerView.adapter = mealRecyclerViewAdapter
        mealRecyclerViewAdapter.onYoutubeLinkClicked = {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(it)
                )
            )
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }
}