package com.example.leafy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leafy.R
import com.example.leafy.model.Meal

class TrendingAdapter(private val onItemClick: (Meal) -> Unit) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private val meals = mutableListOf<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_trending, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
        holder.itemView.setOnClickListener { onItemClick(meal) }
    }

    override fun getItemCount(): Int = meals.size

    fun updateMeals(newMeals: List<Meal>) {
        meals.clear()
        meals.addAll(newMeals)
        notifyDataSetChanged()
    }

    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        private val mealName: TextView = itemView.findViewById(R.id.mealName)

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            Glide.with(itemView).load(meal.strMealThumb).into(mealImage)
        }
    }
}
