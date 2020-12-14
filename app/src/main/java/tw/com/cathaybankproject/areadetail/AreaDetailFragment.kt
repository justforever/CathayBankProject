package tw.com.cathaybankproject.areadetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import tw.com.cathaybankproject.MainActivity
import tw.com.cathaybankproject.R
import tw.com.cathaybankproject.model.ZooArea
import tw.com.cathaybankproject.model.ZooPlant

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AreaDetailFragment : Fragment(), AreaDetailContract.View {

    private var mPresenter: AreaDetailPresenter? = null
    private var zooArea: ZooArea? = null
    private var rvZooPlantList: RecyclerView?= null
    private var layoutManager: RecyclerView.LayoutManager ?= null
    private var zooPlantAdapter: ZooPlantListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        attachPresenter(AreaDetailPresenter(this))
        return inflater.inflate(R.layout.fragment_area_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zooArea = arguments?.get("zooArea") as ZooArea

        rvZooPlantList = view.findViewById(R.id.rv_plant)
        setupZooPlantRecyclerView()

        showBackButton()
        updateTitle()

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }

        view.findViewById<TextView>(R.id.tv_info).text = zooArea?.getInfo()
        view.findViewById<TextView>(R.id.tv_category).text = zooArea?.getCategory()

        Glide.with(context!!).load(zooArea?.getPicUrl()).into(view.findViewById<ImageView>(R.id.iv_top))

        loadPlantData()
    }

    private fun setupZooPlantRecyclerView() {
        zooPlantAdapter = ZooPlantListAdapter(activity!!.applicationContext)
        zooPlantAdapter?.onItemClick = {zooPlant ->
            var bundle = bundleOf()
            bundle.putSerializable("zooPlant", zooPlant)
            findNavController().navigate(R.id.action_areaDetailFragment_to_plantDetailFragment, bundle)
        }
        layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvZooPlantList?.layoutManager = layoutManager
        rvZooPlantList?.adapter = zooPlantAdapter
    }

    private fun loadPlantData() {
        mPresenter?.loadZooPlantData(zooArea?.getName())
    }

    override fun updateZooPlantList(plantList: ArrayList<ZooPlant>?) {
        zooPlantAdapter?.setPlantList(plantList)
        activity?.runOnUiThread {
            zooPlantAdapter?.notifyDataSetChanged()
        }
    }

    override fun attachPresenter(presenter: AreaDetailContract.Presenter) {
        this.mPresenter = presenter as AreaDetailPresenter
    }

    override fun detachPresenter() {
        this.mPresenter?.onDestroy()
        this.mPresenter = null
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun updateTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = zooArea?.getName()
    }

    override fun showBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        setHasOptionsMenu(true)
    }

    override fun hideBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowCustomEnabled(false)
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