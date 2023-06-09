package com.example.mysubmissionawal.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private var bundle: Bundle? = null

    fun setBundle(bundle: Bundle){
        this.bundle = bundle
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        if(position == 0){
            fragment = FollowersFragment()
            fragment.arguments = bundle
            return fragment
        }else{
            fragment = FollowingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}