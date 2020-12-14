package tw.com.cathaybankproject.model

import android.util.Log
import java.io.Serializable

class ZooArea(csvContent: List<String>): Serializable {
    private var no = csvContent[0]
    fun getNo() = no

    private val category = csvContent[1]
    fun getCategory() = category

    private val name = csvContent[2]
    fun getName() = name

    private val picUrl = csvContent[3]
    fun getPicUrl() = picUrl

    private val info = csvContent[4]
    fun getInfo() = info

    private val memo = csvContent[5]
    fun getMemo() = memo

    private val geo = csvContent[6]
    fun getGeo() = geo

    private val url = csvContent[7]
    fun getUrl() = url

    fun show() = Log.d("de", "$no, $category, $name, $picUrl, $info, $memo, $geo, $url")
}