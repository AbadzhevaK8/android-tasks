package com.abadzheva.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abadzheva.tasks.databinding.FragmentTasksBinding
import com.abadzheva.tasks.model.TaskDatabase
import com.abadzheva.tasks.viewmodel.TasksViewModel
import com.abadzheva.tasks.viewmodel.TasksViewModelFactory

class TasksFragment : Fragment() {
    @Suppress("ktlint:standard:backing-property-naming")
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.Companion.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel =
            ViewModelProvider(
                this,
                viewModelFactory,
            )[TasksViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter()
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    adapter.submitList(it)
                }
            },
        )

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
