package com.mediform.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.mediform.app.R


class OnboardingActivity : AppCompatActivity() {

    private lateinit var slideViewPager: ViewPager
    private lateinit var nextBtn: Button
    private lateinit var skipBtn: Button
    private lateinit var indicator1: ImageView
    private lateinit var indicator2: ImageView
    private lateinit var indicator3: ImageView
    private lateinit var onboardingItems: List<OnboardingItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val sharedPrefs = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("isFirstRun", false).apply()

        slideViewPager = findViewById(R.id.vp_slide)
        nextBtn = findViewById(R.id.btn_next)
        skipBtn = findViewById(R.id.btn_skip)
        indicator1 = findViewById(R.id.dot1)
        indicator2 = findViewById(R.id.dot2)
        indicator3 = findViewById(R.id.dot3)

        onboardingItems = listOf(
            OnboardingItem(
                R.drawable.onboarding1,
                "Welcome to MediForm"
            ),
            OnboardingItem(
                R.drawable.onboarding2,
                "CRUD Rekam Medis"
            ),
            OnboardingItem(
                R.drawable.onboarding3,
                "Let’s do it"
            )
        )

        val adapter = SlideAdapter(onboardingItems)
        slideViewPager.adapter = adapter

        slideViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // ??
            }

            override fun onPageSelected(position: Int) {
                updateIndicators(position)

                if (position == 2) {
                    nextBtn.text = "Mulai Sekarang"
                    skipBtn.isEnabled = false
                } else {
                    nextBtn.text = "Selanjutnya"
                    skipBtn.isEnabled = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        skipBtn.setOnClickListener {
            val intent = Intent(this@OnboardingActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        nextBtn.setOnClickListener {
            if (slideViewPager.currentItem < adapter.count - 1) {
                slideViewPager.currentItem = slideViewPager.currentItem + 1
            } else {
                val intent = Intent(this@OnboardingActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun updateIndicators(position: Int) {
        indicator1.setImageResource(if (position == 0) R.drawable.indicator_active else R.drawable.indicator_inactive)
        indicator2.setImageResource(if (position == 1) R.drawable.indicator_active else R.drawable.indicator_inactive)
        indicator3.setImageResource(if (position == 2) R.drawable.indicator_active else R.drawable.indicator_inactive)
    }
}
