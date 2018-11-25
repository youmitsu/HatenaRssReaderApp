package youmeee.co.jp.hatenarssreaderapp.presenter

import youmeee.co.jp.hatenarssreaderapp.presentation.ListFragment
import youmeee.co.jp.hatenarssreaderapp.repository.RssRepository
import javax.inject.Inject


/**
 * Created by yumitsuhori on 2018/11/25.
 */
class TopPresenter @Inject constructor(
        repository: RssRepository
) {
    private lateinit var view: ListFragment

    fun getData() {
        
    }

    fun setView(view: ListFragment) {
        this.view = view
    }
}