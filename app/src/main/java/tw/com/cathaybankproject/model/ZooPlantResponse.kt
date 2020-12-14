package tw.com.cathaybankproject.model

import com.google.gson.annotations.SerializedName

class ZooPlantResponse {
//    @SerializedName("limit")
//    var limit: Int = 0
//
//    @SerializedName("results")
//    var results = ArrayList<ZooPlant>()

    @SerializedName("result")
    var result: ZooPlantResult? = null

}

class ZooPlantResult {
    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("offset")
    var offset: Int = 0

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("sort")
    var sort: String? = null

    @SerializedName("results")
    var results = ArrayList<ZooPlant>()
}