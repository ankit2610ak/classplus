package com.example.classplus.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.classplus.R
import com.example.classplus.adapter.ReposAdapter
import com.example.classplus.model.Repos

class RepoFragment : Fragment() {

    companion object {
        fun newInstance() = RepoFragment()
    }

    private lateinit var viewModel: RepoViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ReposAdapter
    var repoList: ArrayList<Repos> = ArrayList()
    lateinit var login: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        val view = inflater.inflate(R.layout.repo_fragment, container, false)
        login = arguments?.getString("login").toString()

        recyclerView = view.findViewById(R.id.repo_list_recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            repoList.clear()
            repoList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = ReposAdapter(repoList, this.requireContext())

        viewModel.getRepoDetails(login)
        recyclerView.adapter = adapter

        return view
    }

}