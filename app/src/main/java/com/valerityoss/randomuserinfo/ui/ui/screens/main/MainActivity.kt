package com.valerityoss.randomuserinfo.ui.ui.screens.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.valerityoss.randomuserinfo.R
import com.valerityoss.randomuserinfo.databinding.ActivityMainBinding
import com.valerityoss.randomuserinfo.ui.ui.rv_adapters.RepositoryRecyclerViewAdapter
import com.valerityoss.randomuserinfo.ui.ui.ui_model.Repository
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener,
    LifecycleOwner {

    private lateinit var binding: ActivityMainBinding

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    @Inject
    lateinit var mainViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        val viewModel = ViewModelProviders.of(this,mainViewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this, Observer<ArrayList<Repository>> {
            it?.let { repositoryRecyclerViewAdapter.replaceData(it) }
        })

    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
