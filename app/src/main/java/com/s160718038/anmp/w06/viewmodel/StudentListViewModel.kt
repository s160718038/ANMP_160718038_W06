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
import com.s160718038.anmp.w06.model.Student

class StudentListViewModel(app: Application) : AndroidViewModel(app) {
    val students = MutableLiveData<ArrayList<Student>>()
    val studentsLoading = MutableLiveData<Boolean>()
    val studentsLoadError = MutableLiveData<Boolean>()

    private val VOLLEY_TAG = "volley"
    private var queue: RequestQueue? = null

    fun refresh() {
        studentsLoading.value = true
        studentsLoadError.value = false


        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            "http://adv.jitusolution.com/student.php",
            { response ->
                Log.d("VolleyRequest", response)
                val students = Gson().fromJson<List<Student>>(
                    response,
                    object : TypeToken<List<Student>>() {}.type,
                )
                this.students.value = students as ArrayList<Student>
                studentsLoading.value = false
            },
            { error ->
                Log.d("VolleyRequest", error.message.toString())
                studentsLoading.value = false
                studentsLoadError.value = true
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
