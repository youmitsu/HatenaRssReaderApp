package youmeee.co.jp.hatenarssreaderapp.legacy.net.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * HatebuChannel
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