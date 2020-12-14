package tw.com.cathaybankproject.areadetail

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.com.cathaybankproject.model.ZooPlantResponse
import tw.com.cathaybankproject.network.ApiClient

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
            }

            override fun onResponse(call: Call<ZooPlantResponse>?, response: Response<ZooPlantResponse>?) {
                mView.updateZooPlantList(response?.body()?.result?.results)
            }

        })
    }
}