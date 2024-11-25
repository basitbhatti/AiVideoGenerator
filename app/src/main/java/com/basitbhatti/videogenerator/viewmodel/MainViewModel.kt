package com.basitbhatti.videogenerator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basitbhatti.videogenerator.api.RetrofitInstance
import com.basitbhatti.videogenerator.model.InitialResponse
import com.basitbhatti.videogenerator.model.TextRequestBody
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    val textApi = RetrofitInstance.textApi

    private var _response = MutableLiveData<InitialResponse>()
    val textResponse = _response.value

    fun sendTextRequest(body: TextRequestBody){
        viewModelScope.launch {
            _response.value = textApi.sendTextRequest(body).body()
        }
    }



}