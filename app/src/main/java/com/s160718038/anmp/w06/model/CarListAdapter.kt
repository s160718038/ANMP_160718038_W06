package com.s160718038.anmp.w06.model

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s160718038.anmp.w06.R
import com.squareup.picasso.Picasso

class CarListAdapter(
    private val cars: MutableList<Car> = mutableListOf(),
) : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {
    private val numberFormat = NumberFormat.getNumberInstance()

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        val imageCar1: ImageView = view.findViewById(R.id.imageCar)
        val textCarType: TextView = view.findViewById(R.id.textCarType)
        val textCarName: TextView = view.findViewById(R.id.textCarName)
        val textCarOrigin: TextView = view.findViewById(R.id.textCarCountry)
        val textPerformanceTopSpeed: TextView = view.findViewById(R.id.textPerformanceTopSpeed)
        val textPerformanceCruiseSpeed: TextView = view.findViewById(R.id.textPerformanceCruiseSpeed)
        val textPerformanceRange: TextView = view.findViewById(R.id.textPerformanceRange)
        val textFeatures: TextView = view.findViewById(R.id.textCarFeatures)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_car, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]

        holder.apply {
            Picasso.get().load(car.imageUrl).into(imageCar1)
            textCarType.text = car.type
            textCarName.text = "(${car.manufacturer}) ${car.name}"
            textCarOrigin.text = car.country
            textPerformanceTopSpeed.text = "Top speed: ${numberFormat.format(car.performance?.topSpeed)} km/s"
            textPerformanceCruiseSpeed.text = "Cruise speed: ${numberFormat.format(car.performance?.cruiseSpeed)} km/s"
            textPerformanceRange.text = "Range: ${numberFormat.format(car.performance?.range)} km"
            textFeatures.text = car.features.joinToString(", ")
        }
    }

    fun updateData(data: List<Car>) {
        cars.clear()
        cars.addAll(data)
        notifyDataSetChanged()
    }
}
