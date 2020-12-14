package tw.com.cathaybankproject.area

import tw.com.cathaybankproject.BasePresenter
import tw.com.cathaybankproject.BaseView
import tw.com.cathaybankproject.model.ZooArea

interface AreaListContract{
    interface Presenter: BasePresenter {
        fun onViewCreated()
//        fun addFragment()
    }

    interface View: BaseView<Presenter> {
        fun updateZooAreaList(areaList: MutableList<ZooArea>)
    }
}