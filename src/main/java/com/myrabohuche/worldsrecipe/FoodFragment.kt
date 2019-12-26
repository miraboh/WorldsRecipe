package com.myrabohuche.worldsrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myrabohuche.worldsrecipe.Coroutines.launch
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
                              savedInstanceState: Bundle?): View? { return inflater.inflate(R.layout.food_fragment, container, false) }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FoodAdapter()

        //recycler_view.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FoodViewModel::class.java)
        // TODO: Use the ViewModel


        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter

        bindUI()
    }

    fun bindUI() = launch {

        viewModel.foodlist.observe(viewLifecycleOwner, Observer<List<Food>> { newFoods ->
            if (newFoods == null){
                Toast.makeText(context, "No data", Toast.LENGTH_LONG).show()
                return@Observer
            }
            newFoods.apply {
                adapter.data = newFoods
                //Toast.makeText(context, "You have data = ${listOf(newFoods)}", Toast.LENGTH_LONG).show()
                //Toast.makeText(context, "You have data = $newFood", Toast.LENGTH_LONG).show()

            }
        })
    }

}