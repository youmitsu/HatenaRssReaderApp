package youmeee.co.jp.hatenarssreaderapp.legacy.ui.view

import youmeee.co.jp.hatenarssreaderapp.legacy.net.entity.HatebuEntry

/**
 * ListView
 */
interface ListView {
    fun setData(items: MutableList<HatebuEntry>?)
    fun showErrorBar()
}