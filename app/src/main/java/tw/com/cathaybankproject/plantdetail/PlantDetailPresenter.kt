package tw.com.cathaybankproject.plantdetail

import tw.com.cathaybankproject.model.ZooPlant

class PlantDetailPresenter(var mView: PlantDetailContract.View): PlantDetailContract.Presenter {
    override fun onViewCreated() {
    }

    override fun onDestroy() {
    }

    fun updatePlantInfo(zooPlant: ZooPlant?) {
        mView.updateZooPlantDetailInfo(zooPlant)
    }
}