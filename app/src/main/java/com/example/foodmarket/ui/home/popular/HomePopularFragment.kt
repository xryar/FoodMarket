package com.example.foodmarket.ui.home.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentHomeNewTasteBinding
import com.example.foodmarket.model.dummy.HomeVerticalModel
import com.example.foodmarket.ui.detail.DetailActivity
import com.example.foodmarket.ui.home.newtaste.HomeNewTasteAdapter


class HomePopularFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private lateinit var binding: FragmentHomeNewTasteBinding

    private var foodList : ArrayList<HomeVerticalModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNewTasteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()

        var adapter = HomeNewTasteAdapter(foodList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

    }

    fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Cherry Healty", "10000" , "",5f))
        foodList.add(HomeVerticalModel("Burger Tamayo ", "50000" , "",4f))
        foodList.add(HomeVerticalModel("Bakwan Cihuy ", "70000", "",4.5f))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        val intent = Intent(activity, DetailActivity::class.java)
        startActivity(intent)
    }

}