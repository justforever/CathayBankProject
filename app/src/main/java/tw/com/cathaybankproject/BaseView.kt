package tw.com.cathaybankproject

interface BaseView<T> {
    fun attachPresenter(presenter: T)
    fun detachPresenter()
    fun showProgress()
    fun hideProgress()
    fun updateTitle()
    fun showBackButton()
    fun hideBackButton()
}