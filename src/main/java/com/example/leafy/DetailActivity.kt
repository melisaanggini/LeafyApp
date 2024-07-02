package com.example.leafy

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.leafy.model.Meal

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val meal: Meal? = intent.getParcelableExtra("MEAL")

        val mealImageView: ImageView = findViewById(R.id.mealThumbnail)
        val mealNameTextView: TextView = findViewById(R.id.mealName)
        val mealCategoryTextView: TextView = findViewById(R.id.mealCategory)
        val mealInstructionsTextView: TextView = findViewById(R.id.mealInstructions)

        meal?.let {
            mealNameTextView.text = it.strMeal
            mealCategoryTextView.text = it.strCategory
            mealInstructionsTextView.text = it.strInstructions
            Glide.with(this).load(it.strMealThumb).into(mealImageView)
        }
    }
}
