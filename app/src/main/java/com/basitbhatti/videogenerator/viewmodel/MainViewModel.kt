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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: RequestRepository) : ViewModel() {

    val textApi = RetrofitInstance.textApi

    private var _list = MutableLiveData<List<TextRequest>>()
    val listRequests: LiveData<List<TextRequest>> = _list

    fun sendTextRequest(body: TextRequestBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = createRequest(body)
            repository.insertRequest(request)
            updateRequestList()
        }
    }

    private suspend fun createRequest(body: TextRequestBody): TextRequest {
        return try {
            val response = textApi.sendTextRequest(body)
            val initialResponse = response.body()
            if (initialResponse != null) {
                Log.d("TAGRESPONSE", "initialResponse : ${initialResponse}")
                TextRequest(
                    0,
                    body.text_prompt,
                    STATUS_IN_QUEUE,
                    initialResponse.status,
                    initialResponse.uuid
                )

            } else {
                Log.d("TAGRESPONSE", "initial error : ${response}}")
                TextRequest(0, body.text_prompt, STATUS_FAILED, "", "")
            }
        } catch (e: Exception) {
            TextRequest(0, body.text_prompt, STATUS_FAILED, "", "")
        }

    }

    suspend fun updateRequestList() {
        _list.postValue(repository.getRequests())
    }

    suspend fun updateRequests(){
        viewModelScope.launch (Dispatchers.IO) {
            val requests = repository.getRequests().filter { it.requestStatus == STATUS_IN_QUEUE }

            for (req in requests){
                val response = textApi.getStatus(req.uuid)
                if (response.isSuccessful){
                    if (response.body().status.contains("queue"))
                }
            }

        }
    }

}