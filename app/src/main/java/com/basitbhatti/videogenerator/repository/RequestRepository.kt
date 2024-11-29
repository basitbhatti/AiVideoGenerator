package com.basitbhatti.videogenerator.repository

import com.basitbhatti.videogenerator.db.RequestDao
import com.basitbhatti.videogenerator.db.TextRequest

class RequestRepository(val dao: RequestDao) {

    suspend fun insertRequest(request: TextRequest) {
        dao.insertRequest(request)
    }

    suspend fun deleteRequest(request: TextRequest) {
        dao.deleteRequest(request)
    }

    fun getRequests(): List<TextRequest> {
        return dao.getRequests()
    }


}