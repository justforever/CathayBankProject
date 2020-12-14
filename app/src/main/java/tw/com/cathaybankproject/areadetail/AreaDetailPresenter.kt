package tw.com.cathaybankproject.areadetail

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.com.cathaybankproject.model.ZooPlantResponse
import tw.com.cathaybankproject.network.ApiClient
import tw.com.cathaybankproject.plantdetail.PlantDetailPresenter

class AreaDetailPresenter(var mView: AreaDetailContract.View): AreaDetailContract.Presenter {

    override fun onViewCreated() {
    }

    override fun onDestroy() {
    }

    fun loadZooPlantData(areaName: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.taipei")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiClient::class.java)
        val call = service.getZooPlantData(areaName, 10, 0)
        call.enqueue(object : Callback<ZooPlantResponse> {
            override fun onFailure(call: Call<ZooPlantResponse>?, t: Throwable?) {
                Log.d("de", "loadPlantData.onFailure, ${t.toString()}")
            }

            override fun onResponse(call: Call<ZooPlantResponse>?, response: Response<ZooPlantResponse>?) {
                Log.d("de", "loadPlantData.onResponse, size: ${response?.body()?.result?.results?.size}")
                mView.updateZooPlantList(response?.body()?.result?.results)
            }

        })
    }
}