package youmeee.co.jp.hatenarssreaderapp.net.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * Created by yumitsuhori on 2018/11/25.
 */
@Root(name = "item", strict = false)
class HatebuEntry(
        @set:Element
        @get:Element
        var title: String = "",
        @set:Element
        @get:Element
        var link: String = "",
        @set:Element(required = false)
        @get:Element(required = false)
        var description: String = "",
        @set:Element(required = false)
        @get:Element(required = false)
        @Namespace(prefix = "dc")
        var date: String = "",
        @set:Element(required = false)
        @get:Element(required = false)
        @Namespace(prefix = "hatena")
        var imageurl: String? = "",
        @set:Element(required = false)
        @get:Element(required = false)
        @Namespace(prefix = "hatena")
        var bookmarkcount: String? = ""
) : Serializable