package youmeee.co.jp.hatenarssreaderapp.net.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * HatebuChannel
 */
@Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
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