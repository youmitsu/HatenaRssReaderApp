package youmeee.co.jp.hatenarssreaderapp.net.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by yumitsuhori on 2018/11/26.
 */
@Root(name = "channel", strict = false)
class HatebuChannel {
    @set:Element
    @get:Element
    private var title: String = ""

    @set:Element
    @get:Element
    private var link: String = ""

    @set:Element(required = false)
    @get:Element(required = false)
    private var description: String = ""
}