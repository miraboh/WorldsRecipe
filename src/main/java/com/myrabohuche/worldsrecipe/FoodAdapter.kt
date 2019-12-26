package com.myrabohuche.worldsrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    var data = listOf<Food>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.text_name)
//        val origin: TextView = itemView.findViewById(R.id.text_origin)
//        val preparation: TextView = itemView.findViewById(R.id.text_preparation)
//        val ingredients: TextView = itemView.findViewById(R.id.text_ingredients)
        val pics: ImageView = itemView.findViewById(R.id.img_pics)

        fun bind(item: Food) {
            name.text = item.name
//            origin.text = item.origin
//            preparation.text = item.preparation
//            ingredients.text = item.ingredients

            Glide.with(pics)
                .load(item.pics)
                .apply { RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)}
                .into(pics)


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}