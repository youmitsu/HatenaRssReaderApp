package youmeee.co.jp.hatenarssreaderapp.legacy.net.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


/**
 * HatebuFeed
 */
@Namespace(reference = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@Root(name = "RDF", strict = false)
class HatebuFeed {
    @get:ElementList(name = "item", inline = true)
    @set:ElementList(name = "item", inline = true)
    var items: MutableList<HatebuEntry>? = null
}