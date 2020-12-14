package tw.com.cathaybankproject.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tw.com.cathaybankproject.R
import tw.com.cathaybankproject.model.ZooArea

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AreaListFragment : Fragment(), AreaListContract.View {
    private var mPresenter: AreaListPresenter ?= null
    private var rvZooAreaList: RecyclerView ?= null
    private var layoutManager: RecyclerView.LayoutManager ?= null
    private var zooAreaAdapter: ZooAreaListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        attachPresenter(AreaListPresenter(this))
        return inflater.inflate(R.layout.fragment_area_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        hideBackButton()
        updateTitle()

        rvZooAreaList = view.findViewById(R.id.rv_area_list)
        setupZooAreaRecyclerView()

        loadZooAreaList()
    }

    private fun setupZooAreaRecyclerView() {
        zooAreaAdapter = ZooAreaListAdapter(activity!!.applicationContext)
        zooAreaAdapter?.onItemClick = {zooArea ->
            val bundle = bundleOf("areaName" to zooArea.getName(), "areaInfo" to zooArea.getInfo())
            bundle.putString("picUrl", zooArea.getPicUrl())
            bundle.putString("category", zooArea.getCategory())
            bundle.putSerializable("zooArea", zooArea)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvZooAreaList?.layoutManager = layoutManager
        rvZooAreaList?.adapter = zooAreaAdapter
    }

    private fun loadZooAreaList(): Unit {
        mPresenter?.loadZooAreaData()
    }

    override fun updateTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = "台北市立動物園"
    }

    override fun showBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun updateZooAreaList(zooAreaList: MutableList<ZooArea>) {
        zooAreaAdapter?.setZooAreaList(zooAreaList)
        activity?.runOnUiThread {
            zooAreaAdapter?.notifyDataSetChanged()
        }
    }

    override fun attachPresenter(presenter: AreaListContract.Presenter) {
        mPresenter = presenter as AreaListPresenter
    }

    override fun detachPresenter() {
        mPresenter?.onDestroy()
        mPresenter = null
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}