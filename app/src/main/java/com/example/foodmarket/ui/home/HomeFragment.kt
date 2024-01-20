package com.example.foodmarket.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.RecoverySystem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.databinding.FragmentHomeBinding
import com.example.foodmarket.model.dummy.HomeModel
import com.example.foodmarket.ui.detail.DetailActivity

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback {

    private lateinit var binding: FragmentHomeBinding

    private var foodList : ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = HomeAdapter(foodList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

        val sectionPageAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        binding.viewPager.adapter =  sectionPageAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeModel("Cherry Healty", "" , 5f))
        foodList.add(HomeModel("Burger Tamayo ", "" , 4f))
        foodList.add(HomeModel("Bakwan Cihuy ", "" , 4.5f))
    }

    override fun onClick(v: View, data: HomeModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        startActivity(intent)
    }

}