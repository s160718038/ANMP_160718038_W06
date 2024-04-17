package com.s160718038.anmp.w06.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.s160718038.anmp.w06.R
import com.s160718038.anmp.w06.model.StudentListAdapter
import com.s160718038.anmp.w06.viewmodel.StudentListViewModel

class StudentListFragment : Fragment() {
    private val studentListViewModel: StudentListViewModel by viewModels()
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerViewStudent).apply {
            adapter = studentListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshStudent).apply {
            setOnRefreshListener {
                studentListViewModel.refresh()
                this.isRefreshing = false
            }
        }

        observeViewModel()

        studentListViewModel.refresh()
    }

    private fun observeViewModel() {
        studentListViewModel.students.observe(viewLifecycleOwner) {
            studentListAdapter.updateData(it)
        }

        studentListViewModel.studentsLoading.observe(viewLifecycleOwner) { loading ->
            Log.d("ViewModelObserve", "loading: $loading")
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewStudent)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressIndicator)

            if (loading) {
                recyclerView?.visibility = View.GONE
                progressBar?.visibility = View.VISIBLE
            } else {
                recyclerView?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE
            }
        }

        studentListViewModel.studentsLoadError.observe(viewLifecycleOwner) { error ->
            Log.d("ViewModelObserve", "error: $error")
            view?.findViewById<TextView>(R.id.textError)?.visibility =
                if (error) View.VISIBLE else View.GONE
        }
    }
}
