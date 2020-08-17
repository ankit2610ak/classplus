package com.example.classplus.ui.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.classplus.R

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RepoDetailsFragment()
    }

    private lateinit var viewModel: RepoDetailsViewModel
    lateinit var login: String
    lateinit var name: String
    var description: TextView? = null
    var loginTextView: TextView? = null
    var forks: TextView? = null
    var watchers: TextView? = null
    var openIssues: TextView? = null
    var language: TextView? = null
    var photo: ImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepoDetailsViewModel::class.java)
        val view = inflater.inflate(R.layout.repo_details_fragment, container, false)
        login = arguments?.getString("login").toString()
        name = arguments?.getString("name").toString()

        description = view.findViewById(R.id.description)
        loginTextView = view.findViewById(R.id.login_repo)
        forks = view.findViewById(R.id.forks)
        watchers = view.findViewById(R.id.watchers)
        openIssues = view.findViewById(R.id.open_issues)
        language = view.findViewById(R.id.language)
        photo = view.findViewById(R.id.photo)

        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            description?.text = it.description
            loginTextView?.text = login
            forks?.text = it.forks.toString()
            watchers?.text = it.watchers.toString()
            openIssues?.text = it.open_issues.toString()
            language?.text = it.language
            Glide.with(this).load(it.owner.avatar_url).into(photo!!)
        })
        viewModel.getRepoDetails(login, name)



        return view
    }

}