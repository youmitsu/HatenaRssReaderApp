package youmeee.co.jp.hatenarssreaderapp.net

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Root(strict = false)
class RssListEntity {
    @set:ElementList(entry = "item", inline = true)
    @get:ElementList(entry = "item", inline = true)
    var RssEntityList: List<RssEntity>? = null
}

@Root(name = "item", strict = false)
class RssEntity {
    @set:Element(name = "title")
    @get:Element(name = "title")
    var title: String? = null
}