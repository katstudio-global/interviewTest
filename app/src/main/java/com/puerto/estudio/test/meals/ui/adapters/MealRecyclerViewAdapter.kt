package com.puerto.estudio.test.meals.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puerto.estudio.test.R
import com.puerto.estudio.test.meals.dto.Meal
import com.puerto.estudio.test.meals.ui.adapters.MealRecyclerViewAdapter.ViewHolder

class MealRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val mealList = mutableListOf<Meal>()
    var onYoutubeLinkClicked : ((link: String) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mealName: TextView
        val mealCategoryOrigin: TextView
        val mealYoutubeLink: TextView
        val mealPhoto: ImageView

        init {
            mealName = itemView.findViewById(R.id.mealNameId)
            mealCategoryOrigin = itemView.findViewById(R.id.mealCategoryAndOriginId)
            mealYoutubeLink = itemView.findViewById(R.id.mealYoutubeLinkId)
            mealPhoto = itemView.findViewById(R.id.mealPhotoId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item_list, null)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.mealName.text = meal.name
        holder.mealCategoryOrigin.text = "${meal.category} - ${meal.origin}"
        Glide.with(holder.itemView)
            .load(meal.photo)
            .into(holder.mealPhoto)

        if (meal.youtubeVideo.isNullOrEmpty())
            return

        holder.mealYoutubeLink.setOnClickListener {
            onYoutubeLinkClicked?.invoke(meal.youtubeVideo)
        }
    }

    override fun getItemCount(): Int = mealList.size

    fun addMealItems(mealListItems: List<Meal>?) {

        if (mealListItems.isNullOrEmpty())
            return

        mealList.addAll(mealListItems)
        notifyItemRangeChanged(0, mealList.size)
    }


}