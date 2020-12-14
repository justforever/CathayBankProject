package tw.com.cathaybankproject.area

import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tw.com.cathaybankproject.model.ZooArea
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class AreaListPresenter(var mView: AreaListContract.View?) : AreaListContract.Presenter {

    companion object{
        const val KEY_TITLE: String = "KEY_TITLE"
//        var mFragmentCount = 0
        fun newInstance(args: Bundle? = null): AreaListFragment {
            val fragment = AreaListFragment()
            args?.let { fragment.arguments = it }
            return fragment
        }
    }

    override fun onViewCreated() {
    }

//    override fun addFragment() {
//    }

    override fun onDestroy() {
        mView = null
    }

    fun loadZooAreaData() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                Log.d("de", "GlobalScope.withContext")
                mView?.updateZooAreaList(fetchZooArea("https://data.taipei/api/getDatasetInfo/downloadResource?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a"))
            }.run {
                Log.d("de", "GlobalScope.run")
            }
        }
    }

    private fun fetchZooArea(targetUrl: String): MutableList<ZooArea> {
        val inputStream: InputStream
        val result: MutableList<ZooArea>

        val url: URL = URL(targetUrl)
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.connect()
        inputStream = conn.inputStream
        result = if (inputStream != null)
            convertInputStreamToString(inputStream)
        else
            mutableListOf()

        return result
    }

    private fun convertInputStreamToString(inputStream: InputStream): MutableList<ZooArea> {
        val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream, "BIG5"))
        var zooAreaList = mutableListOf<ZooArea>()
        var line:String? = bufferedReader?.readLine()
        var result = ""
        var lineCount = 0
        while (line != null) {
            if (lineCount > 0) {
                val csvContent = line.split(",")
                val zooArea = ZooArea(csvContent = csvContent)
                zooArea.show()
                zooAreaList.add(zooArea)
            }


            result += line
            line = bufferedReader?.readLine()
            lineCount += 1
        }
        Log.d("de", "size: ${zooAreaList.size}")
        inputStream.close()
        return zooAreaList
    }
}