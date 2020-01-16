package com.app.pagingexample.di

import com.app.pagingexample.di.modules.NetworkModule
import com.app.pagingexample.teams.TeamsActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }

    fun inject(teamsActivity: TeamsActivity)
}