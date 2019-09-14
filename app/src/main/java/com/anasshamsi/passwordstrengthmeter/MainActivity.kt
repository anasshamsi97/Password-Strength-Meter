package com.anasshamsi.passwordstrengthmeter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {


    private lateinit var weak: TextView
    private lateinit var weakProgress: ProgressBar
    private lateinit var medium: TextView
    private lateinit var mediumProgress: ProgressBar
    private lateinit var strong: TextView
    private lateinit var strongProgress: ProgressBar
    private lateinit var veryStrong: TextView
    private lateinit var veryStrongProgress: ProgressBar
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        val password = findViewById<EditText>(R.id.password)
        weak = findViewById(R.id.weak)
        weakProgress = findViewById(R.id.weak_progress_bar)
        weakProgress.progressTintList = ColorStateList.valueOf(Color.rgb(255, 68, 68))
        medium = findViewById(R.id.medium)
        mediumProgress = findViewById(R.id.medium_progress_bar)
        mediumProgress.progressTintList = ColorStateList.valueOf(Color.rgb(255, 187, 51))
        strong = findViewById(R.id.strong)
        strongProgress = findViewById(R.id.strong_progress_bar)
        strongProgress.progressTintList = ColorStateList.valueOf(Color.rgb(51, 181, 229))
        veryStrong = findViewById(R.id.very_strong)
        veryStrongProgress = findViewById(R.id.very_strong_progress_bar)
        veryStrongProgress.progressTintList = ColorStateList.valueOf(Color.rgb(153, 204, 0))
        val passwordStrength = PasswordStrength()
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Handler().postDelayed({
                    changeUI(passwordStrength.calculate(s.toString()))
                }, 500)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        weak.visibility = View.INVISIBLE
        weakProgress.visibility = View.INVISIBLE
        medium.visibility = View.INVISIBLE
        mediumProgress.visibility = View.INVISIBLE
        strong.visibility = View.INVISIBLE
        strongProgress.visibility = View.INVISIBLE
        veryStrong.visibility = View.INVISIBLE
        veryStrongProgress.visibility = View.INVISIBLE
    }

    private fun changeUI(calculate: Int) {
        val strength = getString(calculate)
        when {
            strength.equals(getString(R.string.weak)) -> {
                if (weak.visibility.equals(View.INVISIBLE))
                    viewVisible(weak)
                if (weakProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(weakProgress)
                if (medium.visibility.equals(View.VISIBLE))
                    viewGone(medium)
                if (mediumProgress.visibility.equals(View.VISIBLE))
                    viewGone(mediumProgress)
                if (strong.visibility.equals(View.VISIBLE))
                    viewGone(strong)
                if (strongProgress.visibility.equals(View.VISIBLE))
                    viewGone(strongProgress)
                if (veryStrong.visibility.equals(View.VISIBLE))
                    viewGone(veryStrong)
                if (veryStrongProgress.visibility.equals(View.VISIBLE))
                    viewGone(veryStrongProgress)
            }
            strength.equals(getString(R.string.medium)) -> {
                if (weak.visibility.equals(View.INVISIBLE))
                    viewVisible(weak)
                if (weakProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(weakProgress)
                if (medium.visibility.equals(View.INVISIBLE))
                    viewVisible(medium)
                if (mediumProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(mediumProgress)
                if (strong.visibility.equals(View.VISIBLE))
                    viewGone(strong)
                if (strongProgress.visibility.equals(View.VISIBLE))
                    viewGone(strongProgress)
                if (veryStrong.visibility.equals(View.VISIBLE))
                    viewGone(veryStrong)
                if (veryStrongProgress.visibility.equals(View.VISIBLE))
                    viewGone(veryStrongProgress)
            }
            strength.equals(getString(R.string.strong)) -> {
                if (weak.visibility.equals(View.INVISIBLE))
                    viewVisible(weak)
                if (weakProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(weakProgress)
                if (medium.visibility.equals(View.INVISIBLE))
                    viewVisible(medium)
                if (mediumProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(mediumProgress)
                if (strong.visibility.equals(View.INVISIBLE))
                    viewVisible(strong)
                if (strongProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(strongProgress)
                if (veryStrong.visibility.equals(View.VISIBLE))
                    viewGone(veryStrong)
                if (veryStrongProgress.visibility.equals(View.VISIBLE))
                    viewGone(veryStrongProgress)
            }
            strength.equals(getString(R.string.very_strong)) -> {
                if (weak.visibility.equals(View.INVISIBLE))
                    viewVisible(weak)
                if (weakProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(weakProgress)
                if (medium.visibility.equals(View.INVISIBLE))
                    viewVisible(medium)
                if (mediumProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(mediumProgress)
                if (strong.visibility.equals(View.INVISIBLE))
                    viewVisible(strong)
                if (strongProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(strongProgress)
                if (veryStrong.visibility.equals(View.INVISIBLE))
                    viewVisible(veryStrong)
                if (veryStrongProgress.visibility.equals(View.INVISIBLE))
                    viewVisible(veryStrongProgress)
            }
        }

    }

    private fun viewGone(textView: ProgressBar) {
        textView.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    textView.visibility = View.INVISIBLE
                }
            })
    }

    private fun viewVisible(progressBar: ProgressBar) {
        progressBar.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        val incomeMoneyAnimator = ValueAnimator.ofInt(0, 100)
        incomeMoneyAnimator.duration = 600
        progressBar.max = 100
        incomeMoneyAnimator.addUpdateListener { animation ->
            progressBar.progress = animation.animatedValue as Int
        }
        incomeMoneyAnimator.start()
    }

    private fun viewVisible(textView: TextView) {
        textView.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
    }

    private fun viewGone(textView: TextView) {
        textView.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    textView.visibility = View.INVISIBLE
                }
            })
    }
}