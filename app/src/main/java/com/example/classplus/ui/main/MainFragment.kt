package com.example.classplus.ui.main

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classplus.R
import com.example.classplus.adapter.UserDetailAdapter
import com.example.classplus.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var adapter: UserDetailAdapter
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        configureRecyclerView()
        observeLiveData()
        setAdapterInRecyclerView()

        return binding.root
    }

    private fun setAdapterInRecyclerView() {
        adapter = UserDetailAdapter(this.requireContext())
        binding.userListRecyclerView.adapter = adapter
    }

    private fun configureRecyclerView() {
        binding.userListRecyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observeLiveData() {
        viewModel.userPagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}