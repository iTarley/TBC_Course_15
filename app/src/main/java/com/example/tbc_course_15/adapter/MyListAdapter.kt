package com.example.tbc_course_15.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_course_15.databinding.GridViewBinding
import com.example.tbc_course_15.models.Heroes

typealias onClick = (hero: Heroes) -> Unit

class MyListAdapter:ListAdapter<Heroes, MyListAdapter.ViewHolder>(DiffCallBack()){


    lateinit var onClick: onClick


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }


    class DiffCallBack: DiffUtil.ItemCallback<Heroes>() {
        override fun areItemsTheSame(oldItem: Heroes, newItem: Heroes): Boolean {
            return oldItem.header == newItem.header
        }

        override fun areContentsTheSame(oldItem: Heroes, newItem: Heroes): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: GridViewBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var currentHero: Heroes
        fun bind(){
            currentHero = getItem(adapterPosition)
            binding.apply {
                Glide.with(this.root).load(currentHero.src).into(imageView)
                root.setOnClickListener {
                    onClick(
                        currentHero
                    )
                }
            }
        }

    }

    fun filterList(filteredHeroes: List<Heroes>) {
        val x:List<Heroes> = filteredHeroes
        submitList(x)
    }



}