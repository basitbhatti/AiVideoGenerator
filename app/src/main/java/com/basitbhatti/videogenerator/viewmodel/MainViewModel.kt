package com.basitbhatti.videogenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basitbhatti.videogenerator.api.RetrofitInstance
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.model.TextRequestStatusResponse
import com.basitbhatti.videogenerator.utils.NetworkResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val textApi = RetrofitInstance.textApi

    private var _response = MutableLiveData<NetworkResponse<TextRequestStatusResponse>>()
    val initialResponse: LiveData<NetworkResponse<TextRequestStatusResponse>> = _response

    fun sendTextRequest(body: TextRequestBody) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading

            try {

                val response = textApi.sendTextRequest(body)

                if (response.body() != null) {
                } else {
                    _response.value = NetworkResponse.Error("Something went wrong.")
                }

            } catch (e: Exception) {

            }
        }
    }


}