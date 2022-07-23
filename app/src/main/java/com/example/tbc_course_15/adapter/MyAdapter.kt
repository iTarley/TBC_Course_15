package com.example.tbc_course_15.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_course_15.databinding.GridViewBinding
import com.example.tbc_course_15.diffUtil.MyDiffUtil
import com.example.tbc_course_15.models.HeroList
import com.example.tbc_course_15.models.Heroes
import com.google.firebase.firestore.auth.User

typealias onClick = (hero: Heroes) -> Unit

class MyAdapter(private var heroes:List<Heroes>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    lateinit var onClick: onClick



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.bind()

    }

    override fun getItemCount(): Int = heroes.size




    fun filterList(filteredHeroes: List<Heroes>) {
        this.heroes = filteredHeroes
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding:GridViewBinding):RecyclerView.ViewHolder(binding.root){

        private lateinit var currentHero: Heroes
        fun bind(){
            currentHero = heroes[adapterPosition]
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


}