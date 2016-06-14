package de.kokocinski.ankopre.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {

    @GET("/sample.php")
    fun getData(@Query("id") id: String): Call<SampleData>
}

data class SampleData(val title: String, val text: String, val image: String)
