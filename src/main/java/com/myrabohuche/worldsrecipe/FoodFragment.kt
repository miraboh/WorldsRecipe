package com.myrabohuche.worldsrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myrabohuche.worldsrecipe.Coroutines.launch
import com.myrabohuche.worldsrecipe.databinding.FoodFragmentBinding
import kotlinx.android.synthetic.main.food_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FoodFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: FoodViewmodelFactory by instance()

    private lateinit var viewModel: FoodViewModel
    private lateinit var adapter: FoodAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FoodFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)

        adapter = FoodAdapter()

        binding.setLifecycleOwner(this)

        binding.recyclerView.adapter = adapter

        val manager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = manager

        bindUI()

        return binding.root
    }
    fun bindUI() = launch {
        viewModel.foodlist.observe(viewLifecycleOwner, Observer<List<Food>> { newFoods ->
            if (newFoods == null){
                Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
                return@Observer
            }
            newFoods.apply {
                adapter.submitList(newFoods)

            }
        })
    }

}