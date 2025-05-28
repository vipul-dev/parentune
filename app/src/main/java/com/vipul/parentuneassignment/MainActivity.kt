package com.vipul.parentuneassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.vipul.parentuneassignment.adapter.MyPagerAdapter
import com.vipul.parentuneassignment.databinding.ActivityMainBinding
import com.vipul.parentuneassignment.utils.Resource
import com.vipul.parentuneassignment.viewmodel.PlansViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: PlansViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    private val pagerAdapter by lazy {
        MyPagerAdapter(this)
    }

    private val onPageChange = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            setCurrentOnBoardingIndicator(position)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(binding.root)

        binding.toolbar.icBack.setOnClickListener {
            finish()
        }

        callPlansApi()
        observeLiveData()
        setViewPagerTransform()

    }

    private fun setViewPagerTransform() {
        binding.viewPager.setPageTransformer { page, position ->
            val scale = 0.85f + (1 - Math.abs(position)) * 0.15f
            page.scaleY = scale
            page.alpha = 0.7f + (1 - Math.abs(position)) * 0.3f
        }
    }

    private fun observeLiveData() {
        viewmodel.plans.observe(this) {
            when (it) {
                is Resource.Failure -> binding.progressBar.visibility = View.GONE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.viewPager.adapter = pagerAdapter.apply {
                        setDataList(it.value.data)
                        setupOnBoardingIndicators()
                    }
                    binding.viewPager.registerOnPageChangeCallback(onPageChange)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(onPageChange)
    }

    private fun callPlansApi() {
        binding.progressBar.visibility = View.VISIBLE
        viewmodel.getPlans()
    }

    private fun setupOnBoardingIndicators() {
        binding.layoutOnBoardingIndicators.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(pagerAdapter.getDataList().size)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(6, 0, 6, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(this)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.carousel_indicator_un_active
                )
            )
            indicators[i]!!.layoutParams = layoutParams
            binding.layoutOnBoardingIndicators.addView(indicators[i])
        }
    }



    private fun setCurrentOnBoardingIndicator(position: Int) {
        val childCount = binding.layoutOnBoardingIndicators.childCount

        for (i in 0 until childCount) {
            val imageView = binding.layoutOnBoardingIndicators.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.carousel_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.carousel_indicator_un_active
                    )
                )
            }
        }
    }
}

