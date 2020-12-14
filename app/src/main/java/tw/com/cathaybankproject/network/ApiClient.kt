package tw.com.cathaybankproject.network

import retrofit2.Call
import retrofit2.http.*
import tw.com.cathaybankproject.model.ZooPlantResponse

interface ApiClient {
    @Headers("Content-Type: application/json")
    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getZooPlantData(@Query("q") q: String?, @Query("limit") limit: Int, @Query("offset") offset: Int): Call<ZooPlantResponse>
}