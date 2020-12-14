package tw.com.cathaybankproject.areadetail

import tw.com.cathaybankproject.BasePresenter
import tw.com.cathaybankproject.BaseView
import tw.com.cathaybankproject.model.ZooPlant

interface AreaDetailContract {
    interface Presenter: BasePresenter {
        fun onViewCreated()
    }

    interface View: BaseView<Presenter> {
//        fun updateZooAreaList(areaList: MutableList<ZooArea>)
        fun updateZooPlantList(plantList: ArrayList<ZooPlant>?)
    }
}