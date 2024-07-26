package com.example.projectoneecommerecemvvm.view.smartphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectoneecommerecemvvm.model.data.subcategory.Subcategory

class SmartPhoneViewPagerAdapter (fragmentActivity: FragmentActivity, val subCategoryList: List<Subcategory>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = subCategoryList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = SmartPhoneFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("SMARTPHONE_CATEGORY", subCategoryList[position])
        }
        return fragment
    }

}