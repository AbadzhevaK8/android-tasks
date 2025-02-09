package com.abadzheva.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.abadzheva.tasks.R
import com.abadzheva.tasks.databinding.FragmentEditTaskBinding
import com.abadzheva.tasks.model.TaskDatabase
import com.abadzheva.tasks.viewmodel.EditTaskViewModel
import com.abadzheva.tasks.viewmodel.EditTaskViewModelFactory

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(EditTaskViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigateToList.observe(
            viewLifecycleOwner,
            Observer { navigate ->
                if (navigate) {
                    view
                        .findNavController()
                        .navigate(R.id.action_editTaskFragment_to_tasksFragment)
                    viewModel.onNavigatedToList()
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
