package tw.com.cathaybankproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ZooPlant: Serializable{
    @SerializedName("F_Name_Latin")
    var nameLatin: String? = null

    @SerializedName("F_Location")
    var location: String? = null

    @SerializedName("F_Pic01_URL")
    var picUrl: String? = null

    @SerializedName("﻿F_Name_Ch")
    var nameCh: String? = null

    @SerializedName("F_AlsoKnown")
    var alsoKnown: String? = null

    @SerializedName("F_Name_En")
    var nameEn: String? = null

    @SerializedName("F_Brief")
    var brief: String? = null

    @SerializedName("F_Feature")
    var feature: String? = null

    @SerializedName("F_Family")
    var family: String? = null

    @SerializedName("F_Pic01_ALT")
    var pic01Alt: String? = null

    @SerializedName("F_Genus")
    var genus: String? = null

    @SerializedName("F_Function＆Application")
    var functionAndApplication: String? = null

    @SerializedName("F_Update")
    var updateDate: String? = null
}