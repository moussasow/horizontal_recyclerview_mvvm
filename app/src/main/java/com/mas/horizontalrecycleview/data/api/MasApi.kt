package com.mas.horizontalrecycleview.data.api

import com.mas.horizontalrecycleview.data.model.*

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface MasApi {

    @GET("/api/v1/{thePath}")
    fun contents(
        @Path("thePath") path: String,
        @Query("fields") fields: String
    ): Observable<ContentsModel>

}