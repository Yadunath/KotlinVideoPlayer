package com.example.kotlinvideoplayer.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinvideoplayer.data.repository.MovieItem
import com.example.kotlinvideoplayer.databinding.LayoutItemBinding
import com.example.kotlinvideoplayer.player.VideoActivity

class RecyclerviewAdapter (val movieList: List<MovieItem>) :
    RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewAdapter.ViewHolder {
            val binding=LayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(binding)
        }

    override fun getItemCount(): Int {
       return movieList.size
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.ViewHolder, position: Int) {
        var movieItem =movieList[position]
        holder.bind(movieItem)
        holder.itemView.setOnClickListener{view ->
            var intent= Intent (view.context,
                VideoActivity::class.java)
            intent.putExtra("PUT_EXTRA",movieItem.url)
            view.context.startActivity(intent)
        }
    }

    class ViewHolder(val binding: LayoutItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(item :MovieItem){
            binding.movieitem=item
//            binding.executePendingBindings()

        }
    }

}