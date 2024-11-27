package com.basitbhatti.videogenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basitbhatti.videogenerator.repository.RequestRepository

class MainVMFactory(val repo : RequestRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}