package  com.jd.viewpager2examplekotlin.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.jd.viewpager2examplekotlin.LoginActivity
import com.jd.viewpager2examplekotlin.R

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItemAdapter : OnBoardingItemsAdapter
    private lateinit var indicatorContainer: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setOnBoardingItems()
        setIndicator()
        setCurrentIndicator(0)
    }

    private fun setOnBoardingItems(){
        onboardingItemAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.raw.lottie_delivery_boy_bumpy_ride,
                    onBoardingTitle = "Manage Your Task",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
                OnBoardingItem(
                    onBoardingImage = R.raw.lottie_messaging,
                    onBoardingTitle = "Manage Your Task",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
                OnBoardingItem(
                    onBoardingImage = R.raw.lottie_girl_with_a_notebook,
                    onBoardingTitle = "Manage Your Task",
                    onBoardingDesc = "Lorem Ipsum Dolor Sit Amet"
                ),
            )
        )
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onBoardingViewPager.adapter = onboardingItemAdapter

        onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.img_right).setOnClickListener {
            if(onBoardingViewPager.currentItem +1 < onboardingItemAdapter.itemCount){
                onBoardingViewPager.currentItem += 1
            }else{
                navigateToLogin()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToLogin()
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigateToLogin()
        }
    }


    private fun navigateToLogin(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }


    private fun setIndicator(){
        indicatorContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }
}