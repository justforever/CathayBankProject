package tw.com.cathaybankproject.plantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tw.com.cathaybankproject.MainActivity
import tw.com.cathaybankproject.R
import tw.com.cathaybankproject.model.ZooPlant

class PlantDetailFragment: Fragment(), PlantDetailContract.View {

    private var mPresenter: PlantDetailPresenter? = null
    private var targetZooPlant: ZooPlant? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        attachPresenter(PlantDetailPresenter(this))
        return inflater.inflate(R.layout.fragment_plant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        targetZooPlant = (arguments?.get("zooPlant") as ZooPlant)

        updateTitle()
        showBackButton()
        mPresenter?.updatePlantInfo(targetZooPlant)
    }

    override fun updateZooPlantDetailInfo(zooPlant: ZooPlant?) {
        updateTopImageInfo(zooPlant)
        updatePlantName(zooPlant)
        updatePlantAlsoKnown(zooPlant)
        updatePlantBrief(zooPlant)
        updatePlantFeature(zooPlant)
        updatePlantFunctionAndAppilcation(zooPlant)
        updatePlantUpdateDate(zooPlant)
    }

    private fun updateTopImageInfo(zooPlant: ZooPlant?) {
        Glide.with(context!!).load(zooPlant?.picUrl)
            .into((view?.findViewById(R.id.iv_top)!!))
        view?.findViewById<TextView>(R.id.tv_pic_alt)?.text = "(${zooPlant?.pic01Alt})"
    }

    private fun updatePlantUpdateDate(zooPlant: ZooPlant?) {
        val updateDate = "最後更新：${zooPlant?.updateDate}"
        view?.findViewById<TextView>(R.id.tv_update)?.text = updateDate
    }

    private fun updatePlantFunctionAndAppilcation(zooPlant: ZooPlant?) {
        val functionAndApplication = "功能性：\n${zooPlant?.functionAndApplication}"
        view?.findViewById<TextView>(R.id.tv_function)?.text = functionAndApplication
    }

    private fun updatePlantFeature(zooPlant: ZooPlant?) {
        val feature = "辨認方式：\n${zooPlant?.feature}"
        view?.findViewById<TextView>(R.id.tv_feature)?.text = feature
    }

    private fun updatePlantBrief(zooPlant: ZooPlant?) {
        val brief = "簡介：\n${zooPlant?.brief}"
        view?.findViewById<TextView>(R.id.tv_brief)?.text = brief
    }

    private fun updatePlantAlsoKnown(zooPlant: ZooPlant?) {
        val also = "別名：\n${zooPlant?.alsoKnown}"
        view?.findViewById<TextView>(R.id.tv_also)?.text = also
    }

    private fun updatePlantName(zooPlant: ZooPlant?) {
        val name = zooPlant?.nameCh + "\n" + zooPlant?.nameLatin
        view?.findViewById<TextView>(R.id.tv_name)?.text = name
    }

    override fun attachPresenter(presenter: PlantDetailContract.Presenter) {
        this.mPresenter = presenter as PlantDetailPresenter
    }

    override fun detachPresenter() {
        mPresenter?.onDestroy()
        mPresenter = null
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun updateTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = targetZooPlant?.nameCh
    }

    override fun showBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun hideBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = activity as? MainActivity
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else              -> super.onOptionsItemSelected(item)
        }
    }
}