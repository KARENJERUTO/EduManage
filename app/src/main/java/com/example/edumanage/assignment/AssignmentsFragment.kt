package com.example.edumanage.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edumanage.R
import com.example.edumanage.adapter.AssignmentAdapter
import com.example.edumanage.databinding.FragmentAssignmentsBinding
import com.example.edumanage.repository.AssignmentRepository
import com.example.edumanage.viewModel.AssignmentsViewModel
import com.example.edumanage.viewModel.AssignmentsViewModelFactory

class AssignmentsFragment : Fragment() {

    private var _binding: FragmentAssignmentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AssignmentsViewModel
    private lateinit var adapter: AssignmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssignmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AssignmentAdapter()
        binding.recyclerViewAssignments.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAssignments.adapter = adapter

        val repository = AssignmentRepository() // Replace with dependency injection if used
        val factory = AssignmentsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AssignmentsViewModel::class.java)

        viewModel.assignments.observe(viewLifecycleOwner) { assignments ->
            if (assignments.isEmpty()) {
                binding.recyclerViewAssignments.visibility = View.GONE
                binding.emptyStateTextView.visibility = View.VISIBLE
            } else {
                binding.recyclerViewAssignments.visibility = View.VISIBLE
                binding.emptyStateTextView.visibility = View.GONE
                adapter.submitList(assignments)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
