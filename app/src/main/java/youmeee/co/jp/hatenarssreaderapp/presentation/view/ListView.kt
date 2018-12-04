package youmeee.co.jp.hatenarssreaderapp.presentation.view

import youmeee.co.jp.hatenarssreaderapp.net.entity.HatebuEntry

/**
 * ListView
 */
interface ListView {
    fun setData(items: MutableList<HatebuEntry>?)
    fun showErrorBar()
}