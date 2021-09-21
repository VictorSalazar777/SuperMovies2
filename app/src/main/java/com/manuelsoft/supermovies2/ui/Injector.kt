package com.manuelsoft.supermovies2.ui

import androidx.lifecycle.ViewModelProvider

class Injector {
    companion object {

        @JvmStatic
        fun getMainActivityViewModel(activity: MainActivity) : MainActivityViewModel {
            val viewModelFactory = ViewModelFactory(activity.application)
            return ViewModelProvider(activity, viewModelFactory)
                .get(MainActivityViewModel::class.java)
        }
    }
}