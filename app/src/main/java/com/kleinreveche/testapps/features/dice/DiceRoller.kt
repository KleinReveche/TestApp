package com.kleinreveche.testapps.features.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.kleinreveche.testapps.R

/**
 * DiceRoller demonstrates simple interactivity in an Android app.
 * It contains one button that updates an image view with a dice
 * vector image with a random value between 1 and 6.
 */
class DiceRoller : AppCompatActivity() {

    lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roller)

        // Get the Button view from the layout and assign a click
        // listener to it.
        val rollButton: Button = findViewById(R.id.btnRoll)
        rollButton.setOnClickListener { rollDice() }
        diceImage = findViewById(R.id.dice_image)
    }

    /**
     * Click listener for the Roll button.
     */
    private fun rollDice() {
        // Toast.makeText(this, "button clicked",
        //  Toast.LENGTH_SHORT).show()

        val drawableResource = when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
    }
}