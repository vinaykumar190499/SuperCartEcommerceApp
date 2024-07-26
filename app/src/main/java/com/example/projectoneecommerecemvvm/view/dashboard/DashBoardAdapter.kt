package com.example.projectoneecommerecemvvm.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectoneecommerecemvvm.databinding.ActivityDashBoardBinding
import com.example.projectoneecommerecemvvm.databinding.DashboardItemsBinding
import com.example.projectoneecommerecemvvm.model.data.dashboard.Category
import com.example.projectoneecommerecemvvm.model.remote.Constants
import com.squareup.picasso.Picasso

class DashBoardAdapter(
    private val dashBoardList: List<Category>,
    private val onSelectCategory: (Category) -> Unit
) : RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {

    private lateinit var binding: DashboardItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        binding = DashboardItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashBoardViewHolder(binding)
    }

    override fun getItemCount(): Int = dashBoardList.size

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.bind(dashBoardList[position])
    }

    inner class DashBoardViewHolder(binding: DashboardItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            with(binding) {
                txtCategory.text = category.category_name
                Picasso.get().load("${Constants.IMG_BASE_URL}${category.category_image_url}").into(categoryImg)
            }
            binding.dashboardCard.setOnClickListener{
                onSelectCategory(category)
            }
        }
    }
}