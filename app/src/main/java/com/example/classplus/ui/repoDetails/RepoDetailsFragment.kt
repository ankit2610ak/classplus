package com.example.classplus.ui.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.classplus.databinding.RepoDetailsFragmentBinding

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RepoDetailsFragment()
    }

    private lateinit var viewModel: RepoDetailsViewModel
    lateinit var login: String
    lateinit var name: String
    lateinit var binding: RepoDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)
        binding = RepoDetailsFragmentBinding.inflate(layoutInflater, container, false)
        binding.viewmodel = viewModel
        login = arguments?.getString("login").toString()
        name = arguments?.getString("name").toString()

        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            binding.description.text = it.description
            binding.loginRepo.text = login
            binding.forks.text = it.forks.toString()
            binding.watchersValue.text = it.watchers.toString()
            binding.openIssues.text = it.open_issues.toString()
            binding.language.text = it.language
            Glide.with(this).load(it.owner.avatar_url).into(binding.photo)
        })
        viewModel.getRepoDetails(login, name)

        return binding.root
    }

}