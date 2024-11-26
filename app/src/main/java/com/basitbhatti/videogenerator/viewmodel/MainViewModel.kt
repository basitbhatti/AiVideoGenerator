package com.basitbhatti.videogenerator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basitbhatti.videogenerator.api.RetrofitInstance
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.model.TextRequestStatus
import com.basitbhatti.videogenerator.utils.NetworkResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val textApi = RetrofitInstance.textApi

    private var _response = MutableLiveData<NetworkResponse<TextRequestStatus>>()
    val response: LiveData<NetworkResponse<TextRequestStatus>> = _response

    fun sendTextRequest(body: TextRequestBody) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading

            try {
                val response = textApi.sendTextRequest(body)
                val initialResponse = response.body()
                if (initialResponse != null) {
                    Log.d("TAGRESPONSE", "initialResponse : ${initialResponse}")

                    val status = textApi.getStatus(initialResponse.uuid)
                    if (status.body() != null) {
                        _response.value = NetworkResponse.Success(status.body()!!)
                    } else {
                        Log.d("TAGRESPONSE", "status error : ${status}")
                        _response.value = NetworkResponse.Error("Something went wrong.")
                    }


                } else {
                    Log.d("TAGRESPONSE", "initial error : ${response}}")
                    _response.value = NetworkResponse.Error("Something went wrong.")
                }

            } catch (e: Exception) {
                Log.d("TAGRESPONSE", "catch error : ${e}}")
                _response.value = NetworkResponse.Error("Something went wrong.")
            }
        }
    }


}