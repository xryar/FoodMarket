package com.example.foodmarket.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.R
import com.example.foodmarket.model.dummy.HomeModel

class HomeAdapter(
    private  val listData: List<HomeModel>,
    private  val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home_horizontal, viewGroup ,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return  listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val RbFood: RatingBar
        val ImgPoster: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = itemView.findViewById(R.id.tv_title)
            RbFood = itemView.findViewById(R.id.rb_food)
            ImgPoster = itemView.findViewById(R.id.img_poster)
        }

        fun bind(data: HomeModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.title
                RbFood.rating = data.rating

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ImgPoster)

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }

    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: HomeModel)
    }

}