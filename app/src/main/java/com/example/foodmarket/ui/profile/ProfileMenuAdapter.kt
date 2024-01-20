package com.example.foodmarket.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.model.dummy.HomeModel
import com.example.foodmarket.model.dummy.ProfileMenuModel

class ProfileMenuAdapter(
    private  val listData: List<ProfileMenuModel>,
    private  val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_menu_profile, viewGroup ,false)

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
        val ImgIcon: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = itemView.findViewById(R.id.tv_title)
            ImgIcon = itemView.findViewById(R.id.img_icon)
        }

        fun bind(data: ProfileMenuModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.title

                itemView.setOnClickListener {
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }

    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: ProfileMenuModel)
    }

}