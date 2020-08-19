package com.example.classplus.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classplus.adapter.ReposAdapter
import com.example.classplus.databinding.RepoFragmentBinding
import com.example.classplus.model.Repos

class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
    }

    private lateinit var viewModel: RepoViewModel
    private lateinit var adapter: ReposAdapter
    var repoList: ArrayList<Repos> = ArrayList()
    lateinit var login: String
    lateinit var binding: RepoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        binding = RepoFragmentBinding.inflate(layoutInflater, container, false)

        setBundleValueFromAdapter()
        configureRecyclerView()
        observeLivedata()
        viewModel.getRepoDetails(login)
        setAdapterInRecyclerView()

        return binding.root
    }

    private fun setAdapterInRecyclerView() {
        adapter = ReposAdapter(repoList, this.requireContext())
        binding.repoListRecyclerView.adapter = adapter
    }

    private fun observeLivedata() {
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            repoList.clear()
            repoList.addAll(it)
            adapter.notifyDataSetChanged()
            showUI()

        })
    }

    private fun showUI() {
        // Stop Shimmer
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
        // Show data
        binding.repoListRecyclerView.visibility = View.VISIBLE

    }

    private fun configureRecyclerView() {
        binding.repoListRecyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setBundleValueFromAdapter() {
        login = arguments?.getString("login").toString()
    }

}