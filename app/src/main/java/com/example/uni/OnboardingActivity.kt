package com.example.uni

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.uni.databinding.ActivityMainBinding


class OnboardingActivity : AppCompatActivity() {

    private var currentPos: Int = 0
    private lateinit var dots: Array<TextView?>
    private lateinit var binding: ActivityMainBinding
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Call adapter
        sliderAdapter = SliderAdapter(this);
        binding.slider.adapter = sliderAdapter;

        //Dots
        addDots(0);
        binding.slider.addOnPageChangeListener(changeListener);
    }

    fun skip(view: View?) {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    fun next(view: View?) {
        binding.slider.setCurrentItem(currentPos + 1)
    }

    private fun addDots(position: Int) {
        dots = arrayOfNulls<TextView>(4)
        binding.dots.removeAllViews()
        for (i in 0 until dots.size) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.setTextSize(35f)
            binding.dots.addView(dots[i])
        }
        if (dots.size > 0) {
            dots.get(position)!!.setTextColor(resources.getColor(R.color.purple_200))
        }
    }

    var changeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDots(position)
            currentPos = position
            when (position) {
                0 -> {
                    binding.getStartedBtn.visibility = View.GONE
                }
                1 -> {
                    binding.getStartedBtn.visibility = View.GONE
                }
                2 -> {
                    binding.getStartedBtn.visibility = View.INVISIBLE
                }
                else -> {
                    animation =
                        AnimationUtils.loadAnimation(this@OnboardingActivity, R.anim.bottom_anim)
                    binding.getStartedBtn.setAnimation(animation)
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}