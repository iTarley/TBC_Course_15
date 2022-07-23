package com.example.tbc_course_15.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_course_15.MainActivity
import com.example.tbc_course_15.R
import com.example.tbc_course_15.adapter.MyAdapter
import com.example.tbc_course_15.databinding.FragmentFirstBinding
import com.example.tbc_course_15.models.HeroList
import com.example.tbc_course_15.models.Heroes
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy{
        MyAdapter(HeroList.heroList)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.champions)
        showHeroes()
        filterInit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filterInit(){
        binding.searchField.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })
    }


    private fun filter(text:String){

        val filteredHeroes = ArrayList<Heroes>()

        HeroList.heroList.filterTo(filteredHeroes){
            it.header.lowercase().contains(text.lowercase())
        }

        adapter.filterList(filteredHeroes)



    }

    private fun showHeroes() {
        binding.heroRecycler.adapter = adapter
        adapter.onClick = {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                it.src, it.header,it.description
            ))
        }
    }
}