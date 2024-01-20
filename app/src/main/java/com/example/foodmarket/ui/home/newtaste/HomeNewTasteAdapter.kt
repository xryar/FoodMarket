package com.example.foodmarket.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.R
import com.example.foodmarket.model.dummy.HomeVerticalModel
import com.example.foodmarket.utils.Helpers.formatPrice

class HomeNewTasteAdapter(
    private  val listData: List<HomeVerticalModel>,
    private  val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home_vertical, viewGroup ,false)

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
        val tvPrice: TextView
        val RbFood: RatingBar
        val ImgPoster: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvPrice = itemView.findViewById(R.id.tv_price)
            RbFood = itemView.findViewById(R.id.rb_food)
            ImgPoster = itemView.findViewById(R.id.img_poster_vertical)
        }

        fun bind(data: HomeVerticalModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.title
                tvPrice.formatPrice(data.price)
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
        fun onClick(v: View, data: HomeVerticalModel)
    }

}