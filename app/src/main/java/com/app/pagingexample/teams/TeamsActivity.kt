package com.app.pagingexample.teams

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.pagingexample.AppApplication
import com.app.pagingexample.R
import com.app.pagingexample.model.NetworkState
import com.app.pagingexample.teams.adapter.TeamsAdapter
import kotlinx.android.synthetic.main.activity_teams.*
import javax.inject.Inject

class TeamsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TeamsViewModel

    @Inject
    lateinit var teamsAdapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        swipe_refresh_layout?.setOnRefreshListener { viewModel.invalidateDataSource() }

        recycler_view?.layoutManager = LinearLayoutManager(this)
        recycler_view?.adapter = teamsAdapter

        viewModel.networkStateLiveData().observe(this, Observer { renderProgress(it) })
        viewModel.getPagedTeams().observe(this, Observer { teamsAdapter.submitList(it) })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderProgress(networkState: NetworkState?) {
        if (networkState == null) {
            swipe_refresh_layout?.isRefreshing = false
            return
        }

        when (networkState.status) {
            NetworkState.LOADING -> swipe_refresh_layout?.isRefreshing = true
            NetworkState.SUCCESS -> swipe_refresh_layout?.isRefreshing = false
            NetworkState.ERROR -> {
                swipe_refresh_layout?.isRefreshing = false
                Toast.makeText(this, networkState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}