package com.s160718038.anmp.w06.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.s160718038.anmp.w06.model.Car

class CarListViewModel(
    app: Application,
) : AndroidViewModel(app) {
    val cars = MutableLiveData<List<Car>>()

    private val VOLLEY_TAG = "volley.car"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://raw.githubusercontent.com/s160718038/json_cars/master/cars.json",
            { response ->
                Log.d("VolleyRequest.Car", response)
                val cleanedResponse = response.replace(" mph", "")
                this.cars.value = Gson().fromJson<List<Car>>(
                    cleanedResponse,
                    object : TypeToken<List<Car>>() {}.type,
                )
            },
            { error ->
                Log.d("VolleyRequest.Car", error.message.toString())
            },
        )

        stringRequest.tag = VOLLEY_TAG

        queue?.add(stringRequest)
    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(VOLLEY_TAG)
    }
}