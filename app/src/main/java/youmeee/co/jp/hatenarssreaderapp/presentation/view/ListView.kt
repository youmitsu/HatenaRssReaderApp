package youmeee.co.jp.hatenarssreaderapp.presentation.view

import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuFeed

/**
 * Created by yumitsuhori on 2018/11/25.
 */
interface ListView {
    fun showData(itemList: HatebuFeed)
}