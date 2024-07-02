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

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val meals = mutableListOf<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_trending, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = meals.size

    fun updateMeals(newMeals: List<Meal>) {
        meals.clear()
        meals.addAll(newMeals)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        private val mealName: TextView = itemView.findViewById(R.id.mealName)
        private val mealCategory: TextView = itemView.findViewById(R.id.mealCategory)
        private val mealInstructions: TextView = itemView.findViewById(R.id.mealInstructions)

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            mealCategory.text = meal.strCategory
            mealInstructions.text = meal.strInstructions
            Glide.with(itemView).load(meal.strMealThumb).into(mealImage)
        }
    }
}
