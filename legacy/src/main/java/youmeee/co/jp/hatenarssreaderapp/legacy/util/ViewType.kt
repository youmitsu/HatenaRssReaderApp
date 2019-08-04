package youmeee.co.jp.hatenarssreaderapp.legacy.util

/**
 * ViewType
 */
enum class ViewType(val value: Int, val typeName: String) {
    ALL(0, "all"),
    SOCIAL(1, "social"),
    ECONOMICS(2, "economics"),
    LIFE(3, "life");

    companion object {
        fun fromValue(value: Int): ViewType {
            return values().filter { it.value == value }.firstOrNull() ?: ALL
        }
    }
}