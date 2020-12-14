package tw.com.cathaybankproject.plantdetail

import tw.com.cathaybankproject.BasePresenter
import tw.com.cathaybankproject.BaseView
import tw.com.cathaybankproject.model.ZooPlant

interface PlantDetailContract {
    interface Presenter: BasePresenter {
        fun onViewCreated()
    }

    interface View: BaseView<Presenter> {
//        fun updateZooPlantList(plantList: ArrayList<ZooPlant>?)
        fun updateZooPlantDetailInfo(zooPlant: ZooPlant?)
    }
}