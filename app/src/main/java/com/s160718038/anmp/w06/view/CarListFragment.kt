package com.s160718038.anmp.w06.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s160718038.anmp.w06.R
import com.s160718038.anmp.w06.model.CarListAdapter
import com.s160718038.anmp.w06.viewmodel.CarListViewModel

class CarListFragment : Fragment() {
    private val CarListViewModel: CarListViewModel by viewModels()
    private val carListAdapter = CarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerViewCar).apply {
            adapter = carListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        CarListViewModel.cars.observe(viewLifecycleOwner) { cars ->
            Log.d("ObserveViewModel.Car", "size: ${cars.size}")
            carListAdapter.updateData(cars)
        }

        CarListViewModel.refresh()
    }
}