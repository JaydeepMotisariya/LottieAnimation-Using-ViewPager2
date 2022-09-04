package com.jd.viewpager2examplekotlin.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.jd.viewpager2examplekotlin.R

class OnBoardingItemsAdapter(private val onBoardingItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])

    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }


    inner class OnBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageOnBoarding = view.findViewById<LottieAnimationView>(R.id.imageOnboarding)
        private val titleOnBoarding = view.findViewById<TextView>(R.id.titleOnboarding)
        private val descOnBoarding = view.findViewById<TextView>(R.id.descOnboarding)


        fun bind(onboardingItem: OnBoardingItem) {
            imageOnBoarding.setAnimation(onboardingItem.onBoardingImage)
            titleOnBoarding.text = onboardingItem.onBoardingTitle
            descOnBoarding.text = onboardingItem.onBoardingDesc

        }
    }

}