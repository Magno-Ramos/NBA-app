package com.app.pagingexample.teams.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.pagingexample.R
import com.app.pagingexample.model.Team
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.adapter_item_team.view.*
import javax.inject.Inject

class TeamsAdapter @Inject constructor() :
    PagedListAdapter<Team, TeamsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = getItem(position)
        holder.bindView(team)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(team: Team?) {
            team?.run {
                itemView.tv_name?.text = team.teamName
                itemView.tv_simple_name?.text = team.simpleName
                itemView.tv_location?.text = team.location

                GlideToVectorYou.init()
                    .with(itemView.context)
                    .load(
                        Uri.parse(team.image),
                        itemView.image_view
                    )
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
                return oldItem == newItem
            }
        }
    }
}