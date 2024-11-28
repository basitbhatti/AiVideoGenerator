package com.basitbhatti.videogenerator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basitbhatti.videogenerator.api.RetrofitInstance
import com.basitbhatti.videogenerator.db.TextRequest
import com.basitbhatti.videogenerator.model.TextRequestBody
import com.basitbhatti.videogenerator.repository.RequestRepository
import com.basitbhatti.videogenerator.utils.STATUS_FAILED
import com.basitbhatti.videogenerator.utils.STATUS_IN_QUEUE
import kotlinx.coroutines.launch

class MainViewModel(val repository : RequestRepository) : ViewModel() {

    val textApi = RetrofitInstance.textApi


    private var _list = MutableLiveData<List<TextRequest>>()
    val listRequests : LiveData<List<TextRequest>> = _list

    fun sendTextRequest(body: TextRequestBody) {
        viewModelScope.launch {

            try {
                val response = textApi.sendTextRequest(body)
                val initialResponse = response.body()
                if (initialResponse != null) {
                    Log.d("TAGRESPONSE", "initialResponse : ${initialResponse}")

                    val request = TextRequest(0, body.text_prompt, STATUS_IN_QUEUE, initialResponse.status, initialResponse.uuid)
                    repository.insertRequest(request)

                } else {
                    Log.d("TAGRESPONSE", "initial error : ${response}}")
                    val request = TextRequest(0, body.text_prompt, STATUS_FAILED, "", "")
                    repository.insertRequest(request)

                }

            } catch (e: Exception) {
                Log.d("TAGRESPONSE", "catch error : ${e}}")
                val request = TextRequest(0, body.text_prompt, STATUS_FAILED, "", "")
                repository.insertRequest(request)
            }
        }
    }


}